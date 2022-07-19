package com.wind.template.config.exception;

import com.wind.template.config.response.BaseResult;
import com.wind.template.config.response.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * hsc
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常: {}", e.getMessage(), e);
        BindingResult result = e.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        allErrors.get(0).getDefaultMessage();
        String message = allErrors.get(0).getDefaultMessage();
        return ResponseResultUtil.fail(message);
    }

    /**
     * 405 - 不支持当前请求方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法: {}", e.getMethod(), e);
        return ResponseResultUtil.fail("不支持当前请求方法: " + e.getMethod());
    }

    /**
     * 415 - 不支持当前媒体类型
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型: {}", e.getContentType(), e);
        return ResponseResultUtil.fail("不支持当前媒体类型: " + e.getContentType());
    }

    /**
     * runException
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult handleRuntimeException(Exception e) {
        log.error("异常: {}", e.getMessage(), e);
        return ResponseResultUtil.fail();

    }

    /**
     * exception
     */
    @ExceptionHandler(Exception.class)
    public BaseResult handleException(Exception e) {
        log.error("异常: {}", e.getMessage(), e);
        return ResponseResultUtil.fail();
    }
}
