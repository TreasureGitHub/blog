package com.ffl.blog.biz.service.impl;

import com.ffl.blog.biz.base.AbstractService;
import com.ffl.blog.biz.service.StationLogService;
import com.ffl.blog.dal.base.BaseDaoImpl;
import com.ffl.blog.dal.test.dao.StationLogDao;
import com.ffl.blog.dal.test.entity.StationLogDO;
import com.ffl.blog.pojo.param.StationLogParam;
import com.ffl.blog.pojo.vo.StationLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lff
 * @datetime 2020/05/07 17:14
 */
@Service
public class StationLogServiceImpl extends AbstractService<StationLogDO, StationLogVO, StationLogParam> implements StationLogService {

    @Autowired
    private StationLogDao stationLogDao;

    @Override
    public BaseDaoImpl getDao() {
        return new BaseDaoImpl(stationLogDao);
    }


    @Override
    protected void do2VO(StationLogDO data, StationLogVO vo) {
        super.do2VO(data, vo);

        if(data.getCallTime() != null){
            vo.setCallTime(new Date(data.getCallTime()));
        }
    }

    @Override
    protected void vo2DO(StationLogVO vo, StationLogDO data) {
        super.vo2DO(vo, data);

        if(vo.getCallTime() != null){
            data.setCallTime(vo.getCallTime().getTime());
        }
    }
}
