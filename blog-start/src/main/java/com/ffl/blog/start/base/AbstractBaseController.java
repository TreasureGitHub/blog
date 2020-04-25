package com.ffl.blog.start.base;

import com.ffl.blog.biz.base.IBaseService;
import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.pojo.common.CallResult;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.common.PaginationData;
import com.ffl.blog.pojo.param.base.BaseParam;
import com.ffl.blog.pojo.vo.base.BaseVO;
import com.google.common.base.Optional;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ffl.blog.pojo.constant.DateConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * @author lff
 * @datetime 2020/01/05 22:13
 */
public abstract class AbstractBaseController<V extends BaseVO,P extends BaseParam> implements IBaseController<V,P> {

    protected abstract IBaseService getService();

    @Override
    public CallResult<Long> insert(V vo) throws BlogException {
        return CallResult.success(getService().insert(vo));
    }

    @Override
    public CallResult update(Long id, V vo) throws BlogException {
        vo.setId(id);
        getService().update(vo);
        return CallResult.success();
    }

    @Override
    public CallResult delete(Long id) throws BlogException {
        getService().delete(id);
        return CallResult.success();
    }

    @Override
    public CallResult<PaginationData<V>> list(P param, Pagination page) throws BlogException {
        Long total = getService().count(param);
        PaginationData<V> paginationData = null;

        if(total.equals(0)){
            paginationData = PaginationData.ofEmpty(page);
        } else {
            List<V> list = getService().list(param, page);
            paginationData = PaginationData.of(page, total, list);
        }

        return CallResult.success(paginationData);
    }

    @Override
    public CallResult<V> find(Long id) throws BlogException {
        Optional<V> optional = getService().find(id);
        return CallResult.success(optional.orNull());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS),true);
        binder.registerCustomEditor(Date.class,dateEditor);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
