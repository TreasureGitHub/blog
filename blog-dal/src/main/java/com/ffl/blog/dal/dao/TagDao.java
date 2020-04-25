package com.ffl.blog.dal.dao;

import com.ffl.blog.dal.base.tk.BaseMapper;
import com.ffl.blog.dal.entity.TagDO;
import com.ffl.blog.pojo.param.TagParam;
import org.springframework.stereotype.Repository;

/**
 * @author lff
 * @datetime 2020/01/28 17:14
 */
@Repository
public interface TagDao extends BaseMapper<TagDO, TagParam> {
}
