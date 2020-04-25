package com.ffl.blog.dal.entity;

import com.ffl.blog.dal.entity.base.BaseDO;
import lombok.Data;

import javax.persistence.Table;

/**
 * @author lff
 * @datetime 2020/01/29 20:01
 */
@Data
@Table(name ="article")
public class ArticleDO extends BaseDO {

    private static final long serialVersionUID = 2280776586400063287L;

    /**
     * 标题
     */
    private String title;

    /**
     * 总结
     */
    private String summary;

    /**
     * 文章内容
     */
    private String content;

    /**
     * tagId
     */
    private String tagId;
}
