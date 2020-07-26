package com.ffl.blog.start.controller.innerapi;

import com.ffl.blog.biz.base.IBaseService;
import com.ffl.blog.biz.service.UserService;
import com.ffl.blog.pojo.param.UserParam;
import com.ffl.blog.pojo.vo.UserVO;
import com.ffl.blog.start.base.AbstractServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lff
 * @datetime 2020/01/01 19:42
 */
@RestController
@RequestMapping("/innerApi/v1/user")
public class UserController extends AbstractServiceController<UserVO,UserParam> {

    @Autowired
    private UserService userService;

    @Override
    protected IBaseService getService() {
        return userService;
    }
}
