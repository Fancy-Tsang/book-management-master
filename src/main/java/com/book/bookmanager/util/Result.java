package com.book.bookmanager.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    static final long serialVersionUID = 781837582814609011L;
    private boolean success = true;
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean success, Integer code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        return new Result(200, "成功");
    }

    public static Result success(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success(Object data) {
        return new Result(200, "成功", data);
    }

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    public static Result fail() {
        return new Result(false, 500, "失败");
    }

    public static Result fail(String msg) {
        return new Result(false, 500, msg);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(false, code, msg);
    }

    public static Result unLogin(String msg) {
        return new Result(false, 401, msg);
    }

}
