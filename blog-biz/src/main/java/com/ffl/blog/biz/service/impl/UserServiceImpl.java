package com.ffl.blog.biz.service.impl;

import com.ffl.blog.biz.base.AbstractBaseService;
import com.ffl.blog.biz.service.UserService;
import com.ffl.blog.dal.base.BaseDaoImpl;
import com.ffl.blog.dal.dao.UserDao;
import com.ffl.blog.dal.entity.UserDO;
import com.ffl.blog.pojo.enums.RoleEnum;
import com.ffl.blog.pojo.param.UserParam;
import com.ffl.blog.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lff
 * @datetime 2020/01/01 18:47
 */
@Service
public class UserServiceImpl extends AbstractBaseService<UserDO, UserVO, UserParam> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public BaseDaoImpl getDao() {
        return new BaseDaoImpl(userDao);
    }

    @Override
    protected void do2VO(UserDO data, UserVO vo) {
        super.do2VO(data, vo);

        if(data.getLastLoginTime() != null){
            vo.setLastLoginTime(new Date(data.getLastLoginTime()));
        }

        if(data.getRole() != null){
            vo.setRoleEnum(RoleEnum.of(data.getRole()));
        }
    }

    @Override
    protected void vo2DO(UserVO vo, UserDO data) {
        super.vo2DO(vo, data);

        if(vo.getRoleEnum() != null){
            data.setRole(vo.getRoleEnum().getCode());
        }

        if(vo.getLastLoginTime() != null){
            data.setLastLoginTime(vo.getLastLoginTime().getTime());
        }
    }
}
