package com.ffl.blog.pojo.vo;

import com.ffl.blog.pojo.enums.RoleEnum;
import com.ffl.blog.pojo.vo.base.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 * @author lff
 * @datetime 2020/01/01 17:26
 */
@Data
public class UserVO extends BaseVO {

    private static final long serialVersionUID = -9188127466095393578L;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 链接
     */
    private String url;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 角色
     */
    private RoleEnum roleEnum;
}
