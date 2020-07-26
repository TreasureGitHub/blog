package com.ffl.blog.dal.test.entity;

import com.ffl.blog.dal.blog.entity.base.BaseDO;
import lombok.Data;

/**
 * @author lff
 * @datetime 2020/05/07 16:38
 */
@Data
public class StationLogDO extends BaseDO {

    private static final long serialVersionUID = 8080957733874602625L;

    private String sid;

    /**
     * 呼入号码
     */
    private String callIn;

    /**
     * 呼出号码
     */
    private String callOut;

    /**
     * 呼叫类型
     */
    private String callType;

    /**
     * 呼叫时间
     */
    private Long callTime;

    /**
     * 时长
     */
    private Long duration;
}
