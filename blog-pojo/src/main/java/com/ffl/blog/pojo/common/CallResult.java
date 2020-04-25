package com.ffl.blog.pojo.common;

import com.ffl.blog.common.i18n.I18nKey;
import com.ffl.blog.common.utils.ExceptionUtils;
import lombok.Data;

import java.io.Serializable;

import static com.ffl.blog.pojo.common.StatusCode.SUCCESS;

/**
 * @author lff
 * @datetime 2020/01/05 21:02
 */
@Data
public class CallResult<T> implements Serializable {

    private static final long serialVersionUID = 4902217997386975206L;

    private T data;

    private Integer code;

    private I18nKey message;

    private String exception;

    public static CallResult success(){
        return success(null);
    }

    public static <T> CallResult<T> success(T data){
        return new CallResult<T>(SUCCESS,SUCCESS.getDesc(),data,null);
    }

    public static CallResult error(StatusCode code,I18nKey msg,Throwable throwable){
        return new CallResult(code,msg, ExceptionUtils.parseData(throwable),throwable);
    }

    public CallResult(StatusCode statusCode, I18nKey message, T data, Throwable exception) {
        this.data = data;
        this.code = statusCode.getCode();
        this.message = message;
        this.exception = ExceptionUtils.toString(exception);
    }
}