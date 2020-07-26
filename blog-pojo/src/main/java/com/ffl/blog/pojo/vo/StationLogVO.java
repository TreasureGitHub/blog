package com.ffl.blog.pojo.vo;

import com.ffl.blog.pojo.vo.base.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 * @author lff
 * @datetime 2020/05/07 17:12
 */
@Data
public class StationLogVO extends BaseVO {

    private static final long serialVersionUID = -6201530177493651569L;

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
    private Date callTime;

    /**
     * 时长
     */
    private Long duration;
}
