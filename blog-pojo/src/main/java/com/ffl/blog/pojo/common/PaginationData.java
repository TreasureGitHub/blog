package com.ffl.blog.pojo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author lff
 * @datetime 2020/01/05 22:01
 */
@Data
public class PaginationData<T> implements Serializable {

    private static final long serialVersionUID = 424927593530813114L;

    private Long total;

    private List<T> data;

    private Pagination pagination;

    private PaginationData(){
    }

    public static <T> PaginationData<T> of(Pagination pagination,Long total,List<T> data){
        PaginationData<T> paginationData = new PaginationData();
        paginationData.setData(data);
        paginationData.setTotal(total);
        paginationData.setPagination(pagination);
        return paginationData;
    }

    public static <T> PaginationData<T> ofEmpty(Pagination pagination){
        PaginationData<T> paginationData = new PaginationData();
        paginationData.setData(Collections.emptyList());
        paginationData.setTotal(0L);
        paginationData.setPagination(pagination);
        return paginationData;
    }
}
