package cn.xwplay.validator.common;

import java.util.HashMap;

public final class Response extends HashMap<String,Object> {

    private static final long serialVersionUID = -6676367145436867669L;

    public static Response ok() {
        return ok("requestSuccess");
    }

    public static <T> Response ok(String msg, T data) {
        Response r = ok(msg);
        r.put("data", data);
        return r;
    }

    public static Response ok(String msg) {
        return putData(200, msg);
    }


    public static <T> Response ok(T data) {
        Response r = ok();
        r.put("data", data);
        return r;
    }

    public static Response error() {
        return error("服务器发生错误");
    }

    public static Response error(String msg) {
        return error(500, msg);
    }

    public static Response error(int code, String msg) {
        return putData(code, msg);
    }

    public static <T> Response error(int code, String msg,T data) {
        Response r = putData(code, msg);
        r.put("data",data);
        return r;
    }

    public static <T> Response error(int code,T data) {
        Response r = error(code,"服务器发生错误");
        r.put("data",data);
        return r;
    }

    public static <T> Response error(String msg,T data) {
        Response r = putData(500, msg);
        r.put("data",data);
        return r;
    }

    private static Response putData(int code, String msg) {
        Response r = new Response();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
