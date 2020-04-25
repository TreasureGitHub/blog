package com.ffl.blog.start.controller.innerapi;

import com.ffl.blog.biz.base.IBaseService;
import com.ffl.blog.biz.service.ArticleService;
import com.ffl.blog.pojo.param.ArticleParam;
import com.ffl.blog.pojo.vo.ArticleVO;
import com.ffl.blog.start.base.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lff
 * @datetime 2020/01/29 20:11
 */
@RestController
@RequestMapping("/innerApi/v1/article")
public class ArticleController extends AbstractBaseController<ArticleVO, ArticleParam> {

    @Autowired
    private ArticleService articleService;

    @Override
    protected IBaseService getService() {
        return articleService;
    }
}
