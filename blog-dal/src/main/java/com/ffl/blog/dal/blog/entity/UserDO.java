package com.ffl.blog.dal.blog.entity;

import com.ffl.blog.dal.blog.entity.base.BaseDO;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author lff
 * @datetime 2020/01/01 13:24
 */
@Data
@Table(name="user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -2399278589814319439L;

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
    private Long lastLoginTime;

    /**
     * 角色
     */
    private Integer role;
}
