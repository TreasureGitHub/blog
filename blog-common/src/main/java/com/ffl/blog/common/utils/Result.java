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

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(int code) {
        this.code = code;
    }

    public static Result of(int code, String message) {
        return new Result(code, message);
    }

    public static Result error(int code) {
        return new Result(code, null);
    }

    public static Result success() {
        return new Result(200, "success");
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + "]";
    }
}
