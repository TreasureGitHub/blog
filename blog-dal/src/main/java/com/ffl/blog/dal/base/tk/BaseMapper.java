package com.ffl.blog.dal.base.tk;

import com.ffl.blog.dal.blog.entity.base.BaseDO;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.param.base.BaseParam;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lff
 * @datetime 2020/01/01 17:19
 */
public interface BaseMapper<D extends BaseDO,P extends BaseParam> extends Mapper<D> {

    /**
     * 查询
     *
     * @param param
     * @param page
     * @return
     */
    List<D> list(@Param("param") P param, @Param("page") Pagination page);

    /**
     * 计数
     *
     * @param param
     * @return
     */
    Long count(@Param("param") P  param);
}
