package com.wpy.blog.framework.model;




/**
 * 架构层包装类
 * @param <T>
 */
public class Response<T> {
    /** 错误或者成功代码 */
    private int code;
    /** 错误描述 */
    private String msg;
    /** 响应结果 */
    private T data;
    /** 成功状态 */
    private  boolean success = false ;


    public Response() {
    }

    public Response(int code) {
        this.code = code;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(int code, String msg, T data, boolean success) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
