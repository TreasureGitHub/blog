package com.ffl.blog.dal.dao;

import com.ffl.blog.dal.base.tk.BaseMapper;
import com.ffl.blog.dal.entity.UserDO;
import com.ffl.blog.pojo.param.UserParam;
import org.springframework.stereotype.Repository;

/**
 * @author lff
 * @datetime 2020/01/01 17:42
 */
@Repository
public interface UserDao extends BaseMapper<UserDO, UserParam> {
}