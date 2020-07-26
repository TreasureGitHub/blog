package com.ffl.blog.dal.test.dao;

import com.ffl.blog.dal.base.tk.BaseMapper;
import com.ffl.blog.dal.test.entity.StationLogDO;
import com.ffl.blog.pojo.param.StationLogParam;
import org.springframework.stereotype.Repository;

/**
 * @author lff
 * @datetime 2020/05/07 16:59
 */
@Repository
public interface StationLogDao extends BaseMapper<StationLogDO, StationLogParam> {

}
