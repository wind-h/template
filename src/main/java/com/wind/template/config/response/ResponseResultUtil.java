package com.wind.template.config.response;

/**
 * @author hsc
 */
public class ResponseResultUtil {

    public static BaseResult fail() {
        return fail(BaseCodeEnum.FAIL.getMsg());
    }

    public static BaseResult fail(String msg) {
        return fail(BaseCodeEnum.FAIL.getCode(), msg);
    }

    public static BaseResult fail(Integer code, String msg) {
        return baseResult(false, code, msg);
    }

    public static <T> Result<T> success(T data) {
        return success(BaseCodeEnum.SUCCESS.getCode(), BaseCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(Integer code, String msg, T data) {
        return result(true, code, msg, data);
    }

    public static <T> Result<T> result(Boolean success, Integer code, String msg, T data) {
        return new Result<T>(success, code, msg, data);
    }

    public static BaseResult baseResult(Boolean success, Integer code, String msg) {
        return new BaseResult(success, code, msg);
    }

}
