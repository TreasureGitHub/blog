package com.ffl.blog.pojo.common;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.ffl.blog.pojo.constant.AppPojoConstants.MAX_PAGE_LIMIT;

/**
 * 分页对象
 *
 * @author lff
 * @datetime 2020/01/01 17:49
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = 5298220480869593523L;

    public static Pagination ONE = Pagination.of(1,1);

    public static Pagination MAX_PAGE = Pagination.of(1,MAX_PAGE_LIMIT);

    @Getter
    @Setter
    private Integer page = 1;


    @Getter
    @Setter
    private Integer limit = 10;

    public static Pagination of(int page,int limit){
        Pagination pagination = new Pagination();
        pagination.page = page;
        pagination.limit = limit;

        return pagination;
    }

    public int getStartRow(){
        return (page - 1) * limit;
    }

    public int getPerPage(){
        return limit;
    }

}
