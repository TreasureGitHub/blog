package com.ffl.blog.pojo.vo;

import com.ffl.blog.pojo.vo.base.BaseVO;
import lombok.Data;

/**
 * @author lff
 * @datetime 2020/01/29 19:59
 */
@Data
public class ArticleVO extends BaseVO {

    private static final long serialVersionUID = 3912151910258846677L;

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
     * 标签id
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;
}
