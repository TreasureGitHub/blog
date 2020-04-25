package com.ffl.blog.biz.base;

import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.param.base.BaseParam;
import com.ffl.blog.pojo.vo.base.BaseVO;
import com.google.common.base.Optional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lff
 * @datetime 2020/01/01 18:46
 */
public interface IBaseService<V extends BaseVO,P extends BaseParam> {

    /**
     * 写入
     *
     * @param vo
     * @return Long 0 为失败，否则返回数据库主键
     */
    Long insert(V vo) throws BlogException;

    /**
     *
     * 更新非空字段
     *
     * @param vo
     * @return
     */
    void update(V vo) throws BlogException;

    /**
     * 更新所有字段
     *
     * @param vo
     * @return
     */
    void updateAllField(V vo) throws BlogException;

    /**
     *
     * @param id
     * @return
     */
    void delete(Long id) throws BlogException;

    /**
     * 计数
     *
     * @param param
     * @return
     */
    Long count(@Param("param") P  param);

    /**
     * 根据 id 进行查找
     *
     * @param id
     * @return
     */
    Optional<V> find(Long id) throws BlogException;

    /**
     * 查询
     *
     * @param param
     * @param page
     * @return
     */
    List<V> list(@Param("param") P param, @Param("page") Pagination page) throws BlogException;
}
