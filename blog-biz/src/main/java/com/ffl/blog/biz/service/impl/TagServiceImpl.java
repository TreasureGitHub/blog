package com.ffl.blog.biz.service.impl;

import com.ffl.blog.biz.base.AbstractService;
import com.ffl.blog.biz.service.TagService;
import com.ffl.blog.dal.base.BaseDaoImpl;
import com.ffl.blog.dal.blog.dao.TagDao;
import com.ffl.blog.dal.blog.entity.TagDO;
import com.ffl.blog.pojo.param.TagParam;
import com.ffl.blog.pojo.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lff
 * @datetime 2020/01/28 17:16
 */
@Service
public class TagServiceImpl extends AbstractService<TagDO, TagVO, TagParam> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public BaseDaoImpl getDao() {
        return new BaseDaoImpl(tagDao);
    }
}
