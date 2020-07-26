package com.ffl.blog.dal.blog.dao;

import com.ffl.blog.dal.base.tk.BaseMapper;
import com.ffl.blog.dal.blog.entity.ArticleDO;
import com.ffl.blog.pojo.param.ArticleParam;
import org.springframework.stereotype.Repository;

/**
 * @author lff
 * @datetime 2020/01/29 20:04
 */
@Repository
public interface ArticleDao extends BaseMapper<ArticleDO, ArticleParam> {
}
