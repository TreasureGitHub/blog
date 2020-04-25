package com.ffl.blog.biz.service.impl;


import com.ffl.blog.biz.service.UserService;
import com.ffl.blog.biz.service.base.BaseTest;
import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.pojo.enums.RoleEnum;
import com.ffl.blog.pojo.vo.UserVO;
import com.google.common.base.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author lff
 * @datetime 2020/01/01 19:01
 */
public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() throws BlogException {
        UserVO vo = new UserVO();
        vo.setEmail("mytest");
        vo.setRoleEnum(RoleEnum.NORMAL);
        vo.setName("test");
        vo.setUrl("http:/test123");
        vo.setPassword("test");
        Long insert = userService.insert(vo);
        System.out.println(insert);
    }

    @Test
    public void find() throws BlogException {
        Optional<UserVO> optional = userService.find(1L);
        System.out.println(optional.get());

    }

    @Test
    @Rollback
    public void update() throws BlogException {
        Optional<UserVO> optional = userService.find(2L);
        UserVO userVO = optional.get();
        userVO.setRoleEnum(RoleEnum.NORMAL);
        userService.update(userVO);
    }

    @Test
    @Rollback
    public void delete() throws BlogException {
        userService.delete(2L);
    }

}
