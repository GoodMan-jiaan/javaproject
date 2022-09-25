package com.example.demo.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      code： 状态码，
 *      message：状态信息，
 *      data：  响应数据
 *  }
 * </pre>
 *
 * 状态码：
 * 1，操作成功
 * -1, 其他失败
 * 401，参数错误
 * 402，参数类型错误
 * 501，为登陆
 * 502，系统内部错误，一般是出现bug了
 * 506，没有权限
 * 508，请求方法类型错误
 *
 */
public class ResponseUtil {

    private ResponseUtil() {}

    @Data
    private static class Response {
        // 响应实体数据
        int code;
        Object data;
        String message;
        public Response(int code, Object data, String message) {
            this.code = code;
            this.data = data;
            this.message = message;
        }
    }

    public static Object success() {
        return new Response(1, null, "操作成功");
    }

    public static Object success(Object data) {
        return new Response(1, data, "操作成功");
    }

    public static Object success(int code, Object data, String message) {
        return new Response(code, data, message);
    }

    public static Object fail() {
        return new Response(-1, null, "操作失败");
    }

    public static Object fail(int code, String message) {
        return new Response(code, null, message);
    }


    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object methodError() {
        return fail(507, "请求方法类型错误！");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }


}

