package com.ffl.blog.dal.base;

import com.ffl.blog.dal.base.tk.BaseMapper;
import com.ffl.blog.dal.entity.base.BaseDO;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.param.base.BaseParam;

import java.util.List;

/**
 * @author lff
 * @datetime 2020/01/01 18:03
 */
public class BaseDaoImpl<D extends BaseDO,P extends BaseParam> implements BaseDao<D,P>{

    private BaseMapper<D,P> tkDao;

    public BaseDaoImpl(BaseMapper<D, P> tkDao) {
        this.tkDao = tkDao;
    }

    @Override
    public Long insert(D data) {
        int result = tkDao.insert(data);
        return result > 0 ? data.getId():result;
    }

    @Override
    public Long update(D data) {
        int result = tkDao.updateByPrimaryKeySelective(data);
        return Long.valueOf(result);
    }

    @Override
    public Long updateAllField(D data) {
        int result = tkDao.updateByPrimaryKey(data);
        return Long.valueOf(result);
    }

    @Override
    public Long delete(D data) {
        int result = tkDao.updateByPrimaryKeySelective(data);
        return Long.valueOf(result);
    }

    @Override
    public Long count(P param) {
        return tkDao.count(param);
    }

    @Override
    public List<D> list(P param, Pagination page) {
        return tkDao.list(param,page);
    }

    public D find(D data) {
        return tkDao.selectByPrimaryKey(data);
    }
}
