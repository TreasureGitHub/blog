package com.ffl.blog.start.controller.innerapi;

import com.ffl.blog.biz.base.IBaseService;
import com.ffl.blog.biz.service.TagService;
import com.ffl.blog.pojo.param.TagParam;
import com.ffl.blog.pojo.vo.TagVO;
import com.ffl.blog.start.base.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lff
 * @datetime 2020/01/28 17:23
 */
@RestController
@RequestMapping("/innerApi/v1/tag")
public class TagController extends AbstractBaseController<TagVO, TagParam> {

    @Autowired
    private TagService tagService;

    @Override
    protected IBaseService getService() {
        return tagService;
    }
}
