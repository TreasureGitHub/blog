package com.ffl.blog.biz.base;

import com.ffl.blog.common.exceptions.common.BlogException;
import com.ffl.blog.common.i18n.I18nArea;
import com.ffl.blog.common.i18n.I18nKey;
import com.ffl.blog.common.utils.Assert;
import com.ffl.blog.common.utils.ReflectUtils;
import com.ffl.blog.dal.base.BaseDaoImpl;
import com.ffl.blog.dal.entity.base.BaseDO;
import com.ffl.blog.pojo.common.Pagination;
import com.ffl.blog.pojo.enums.DeleteType;
import com.ffl.blog.pojo.param.base.BaseParam;
import com.ffl.blog.pojo.vo.base.BaseVO;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.ffl.blog.common.constants.AppConstant.I18N_PAGE_SERVICE;

/**
 * @author lff
 * @datetime 2020/01/01 18:46
 */
public abstract class AbstractBaseService<D extends BaseDO,V extends BaseVO,P extends BaseParam> implements IBaseService<V,P> {

    private static final I18nArea AREA = I18N_PAGE_SERVICE.of(AbstractBaseService.class.getSimpleName());

    private static final I18nKey GEN_DO_ERROR = AREA.of("GEN_DO_ERROR","生成 DO对象错误");

    private static final I18nKey GEN_VO_ERROR = AREA.of("GEN_VO_ERROR","生成 VO对象错误");

    public abstract BaseDaoImpl getDao();

    @Override
    public Long insert(V vo) throws BlogException {
        validateBeforeInsert(vo);
        D data = generateDO();
        dealBeforeInsert(vo);
        vo2DO(vo,data);
        extendVo2DO(vo,data);
        return getDao().insert(data);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public void update(V vo) throws BlogException {
        validateBeforeUpdate(vo);

        Optional<V> vOptional = find(vo.getId());
        if(!vOptional.isPresent()){
            throw new BlogException(AREA.of("OLD_DO_NOT_EXISTS","update failed,there is no vo in database id:" + vo.getId()));
        }

        D data = generateDO();
        dealBeforeUpdate(vo);
        vo2DO(vo,data);
        extendVo2DO(vo,data);

        if(getDao().update(data) <= 0){
            throw new BlogException(AREA.of("FAILED_TO_UPDATE",String.format("Failed to update vo:%s",vo)));
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public void updateAllField(V vo) throws BlogException {
        validateBeforeUpdate(vo);

        Optional<V> vOptional = find(vo.getId());
        if(!vOptional.isPresent()){
            throw new BlogException(AREA.of("OLD_DO_NOT_EXISTS","updateAllField failed,there is no vo in database id:" + vo.getId()));
        }

        D data = generateDO();
        dealBeforeUpdate(vo);
        vo2DO(vo,data);
        extendVo2DO(vo,data);

        if(getDao().updateAllField(data) <= 0){
            throw new BlogException(AREA.of("FAILED_TO_UPDATE",String.format("Failed to update all field vo:%s",vo)));
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws BlogException {
        Assert.notNull(id);
        V vo = generateVO();
        vo.setId(id);
        D data = generateDO();
        dealBeforeDelete(vo);
        vo2DO(vo,data);
        extendVo2DO(vo,data);

        if(getDao().delete(data) <= 0){
            throw new BlogException(AREA.of("FAILED_TO_DELETE",String.format("Failed to delete vo:%s",vo)));
        }
    }

    @Override
    public Long count(P param) {
        param.supply();
        return getDao().count(param);
    }

    @Override
    public Optional<V> find(Long id) throws BlogException {
        Assert.notNull(id);
        D data = generateDO();
        data.setId(id);
        D oldData = (D)getDao().find(data);
        if(oldData == null){
            return Optional.absent();
        }

        V vo = generateVO();
        do2VO(oldData,vo);
        extendDo2VO(oldData,vo);

        if(vo.getIsDeleted() == DeleteType.DELETE){
            return Optional.absent();
        }

        return Optional.of(vo);
    }

    @Override
    public List<V> list(P param, Pagination page) throws BlogException {
        param.supply();
        List<D> doList = getDao().list(param, page);
        List<V> voList = Lists.newArrayList();
        for(D data:doList){
            V vo = generateVO();
            do2VO(data,vo);
            extendDo2VO(data,vo);
            voList.add(vo);
        }

        return voList;
    }

    /**
     * do 转 vo
     *
     * @param data
     * @param vo
     */
    protected void do2VO(D data,V vo){
        BeanUtils.copyProperties(data,vo);
    }

    private void extendVo2DO(V vo,D data){
        if(vo.getGmtCreate() != null) {
            data.setGmtCreate(vo.getGmtCreate().getTime());
        }

        if(vo.getGmtModified() != null) {
            data.setGmtModified(vo.getGmtModified().getTime());
        }

        if(vo.getIsDeleted() != null) {
            data.setIsDeleted(vo.getIsDeleted().getDataMark());
        }
    }

    /**
     * vo 转 do
     *
     * @param vo
     * @param data
     */
    protected void vo2DO(V vo,D data){
        BeanUtils.copyProperties(vo,data);
    }

    private void extendDo2VO(D data,V vo){
        if(data.getGmtCreate() != null) {
            vo.setGmtCreate(new Date(data.getGmtCreate()));
        }

        if(data.getGmtModified() != null) {
            vo.setGmtModified(new Date(data.getGmtModified()));
        }

        if(data.getIsDeleted() != null) {
            vo.setIsDeleted(DeleteType.dataMarkToType(data.getIsDeleted()));
        }
    }

    /**
     * 插入数据之前执行
     */
    protected void dealBeforeInsert(V vo){
        Date now = new Date();
        vo.setGmtCreate(now);
        vo.setGmtModified(now);
        vo.setIsDeleted(DeleteType.EXISTS);
    }

    /**
     * 插入数据之前执行
     */
    protected void dealBeforeUpdate(V vo){
        Date now = new Date();
        vo.setGmtModified(now);
        vo.setIsDeleted(DeleteType.EXISTS);
    }

    /**
     * 删除数据之前执行
     */
    protected void dealBeforeDelete(V vo){
        Date now = new Date();
        vo.setGmtModified(now);
        vo.setIsDeleted(DeleteType.DELETE);
    }

    protected void validateBeforeInsert(V vo){
        Assert.notNull(vo);
    }

    protected void validateBeforeUpdate(V vo){
        Assert.notNull(vo);
        Assert.notNull(vo.getId());
    }

    /**
     * 通过反射得到 VO 对象
     *
     * @return
     * @throws BlogException
     */
    private V generateVO() throws BlogException {
        try {
            Class<? extends BaseVO> doClass = ReflectUtils.getGenericClass(this.getClass(),BaseVO.class);
            return (V) ReflectUtils.generateObject(doClass);
        } catch (Exception e) {
            throw new BlogException(GEN_VO_ERROR);
        }
    }

    /**
     * 通过反射得到 DO 对象
     *
     * @return
     * @throws BlogException
     */
    private D generateDO() throws BlogException {
        try {
            Class<? extends BaseDO> doClass = ReflectUtils.getGenericClass(this.getClass(),BaseDO.class);
            return (D) ReflectUtils.generateObject(doClass);
        } catch (Exception e) {
            throw new BlogException(GEN_DO_ERROR);
        }
    }
}
