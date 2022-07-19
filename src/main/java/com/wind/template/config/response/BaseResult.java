package com.wind.template.config.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author hsc
 */
@Data
@NoArgsConstructor
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    protected boolean success;

    /**
     * code
     */
    protected Integer code;

    /**
     * 返回信息
     */
    protected String msg;

    /**
     * 时间戳
     */
    protected Long timestamp;


    public BaseResult(boolean success, Integer code, String msg) {
        this(success, code, msg, Timestamp.valueOf(LocalDateTime.now()).getTime());
    }

    public BaseResult(boolean success, Integer code, String msg, Long timestamp) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
    }
}
