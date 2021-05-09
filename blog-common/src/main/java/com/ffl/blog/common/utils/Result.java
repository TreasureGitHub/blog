package com.ffl.blog.common.utils;

import lombok.Data;

/**
 * @author lff
 * @datetime 2021/05/09 17:38
 */
@Data
public class Result {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String message;


    public Result() {
        super();
    }

    public Result(int code) {
        super();
        this.code = code;
    }

    public Result(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", content=" + message + "]";
    }
}
