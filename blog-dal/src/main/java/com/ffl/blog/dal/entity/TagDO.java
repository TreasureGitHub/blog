package com.ffl.blog.dal.entity;

import com.ffl.blog.dal.entity.base.BaseDO;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author lff
 * @datetime 2020/01/28 17:12
 */
@Data
@Table(name ="tag")
public class TagDO extends BaseDO {

    private static final long serialVersionUID = -3100386308429499598L;

    /**
     * 标签名称
     */
    private String name;

}
