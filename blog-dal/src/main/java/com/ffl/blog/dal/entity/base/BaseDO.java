package com.ffl.blog.dal.entity.base;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lff
 * @datetime 2020/01/01 13:15
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -2446638477190604798L;

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 是否删除
     * 0：否 其他:删除
     */
    private String isDeleted;
}
