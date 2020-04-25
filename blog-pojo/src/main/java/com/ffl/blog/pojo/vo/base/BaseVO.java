package com.ffl.blog.pojo.vo.base;

import com.ffl.blog.pojo.enums.DeleteType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lff
 * @datetime 2020/01/01 17:23
 */
@Data
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 2368582262825622878L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 删除标志
     */
    private DeleteType isDeleted;
}
