package com.ffl.blog.start.controller.innerapi;

import com.ffl.blog.biz.base.IBaseService;
import com.ffl.blog.biz.service.StationLogService;
import com.ffl.blog.pojo.param.StationLogParam;
import com.ffl.blog.pojo.vo.StationLogVO;
import com.ffl.blog.start.base.AbstractServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lff
 * @datetime 2020/05/07 17:17
 */
@RestController
@RequestMapping("/innerApi/v1/stationlog")
public class StationLogController extends AbstractServiceController<StationLogVO, StationLogParam> {

    @Autowired
    private StationLogService stationLogService;

    @Override
    protected IBaseService getService() {
        return stationLogService;
    }
}
