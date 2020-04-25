package com.ffl.blog.pojo.vo;

import com.ffl.blog.pojo.vo.base.BaseVO;
import lombok.Data;

/**
 * @author lff
 * @datetime 2020/01/28 17:02
 */
@Data
public class TagVO extends BaseVO {

    private static final long serialVersionUID = 2689308261321201949L;

    /**
     * 标签名称
     */
    private String name;

}
