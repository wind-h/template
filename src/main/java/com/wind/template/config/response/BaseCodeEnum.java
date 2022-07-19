package com.wind.template.config.response;

import lombok.Getter;

/**
 * @author hsc
 */
@Getter
public enum BaseCodeEnum {

    /**
     * 基本code
     */
    SUCCESS(200, "请求成功"),
    FAIL(500, "请求失败");

    private final Integer code;

    private final String msg;

    BaseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
