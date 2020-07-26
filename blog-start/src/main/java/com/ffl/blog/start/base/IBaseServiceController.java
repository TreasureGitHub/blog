package com.ffl.blog.start.base;

import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.pojo.common.CallResult;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.common.PaginationData;
import com.ffl.blog.pojo.param.base.BaseParam;
import com.ffl.blog.pojo.vo.base.BaseVO;
import org.springframework.web.bind.annotation.*;

/**
 * @author lff
 * @datetime 2020/01/05 21:53
 */
@ResponseBody
public interface IBaseServiceController<V extends BaseVO,P extends BaseParam> {

    /**
     * 写入数据 ，返回ID
     *
     * @param vo
     * @return
     */
    @PostMapping
    CallResult<Long> insert(@RequestBody V vo) throws BlogException;

    /**
     * 更新数据
     *
     * @param id
     * @param vo
     * @return
     */
    @PutMapping(path = "/{id}")
    CallResult update(@PathVariable("id") Long id,@RequestBody V vo) throws BlogException;

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    CallResult delete(@PathVariable("id") Long id) throws BlogException;

    /**
     * 查询数据
     *
     * @param param
     * @param page
     * @return
     */
    @GetMapping
    CallResult<PaginationData<V>> list(P param, Pagination page) throws BlogException;

    /**
     * 根据ID 查找
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    CallResult<V> find(@PathVariable("id") Long id) throws BlogException;
}
