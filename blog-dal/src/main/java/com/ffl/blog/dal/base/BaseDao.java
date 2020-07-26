package com.ffl.blog.dal.base;

import com.ffl.blog.dal.blog.entity.base.BaseDO;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.param.base.BaseParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lff
 * @datetime 2020/01/01 18:04
 */
public interface BaseDao<D extends BaseDO,P extends BaseParam> {

    /**
     * 写入 对象，返回数据库主键
     *
     * @param data
     * @return Long 0 为失败，否则返回数据库主键
     */
    Long insert(D data);

    /**
     * 更新非空字段，成功为1，否则为0
     *
     * @param data
     * @return
     */
    Long update(D data);

    /**
     * 更新所有字段，成功为1，否则为0
     *
     * @param data
     * @return
     */
    Long updateAllField(D data);

    /**
     * 删除 成功为1，否则为0
     *
     * @param data
     * @return
     */
    Long delete(D data);

    /**
     * 根据主键查找
     *
     * @param data
     * @return
     */
    D find(D data);

    /**
     * 计数
     *
     * @param param
     * @return
     */
    Long count(@Param("param") P  param);

    /**
     * 查询
     *
     * @param param
     * @param page
     * @return
     */
    List<D> list(@Param("param") P param, @Param("page") Pagination page);
}
