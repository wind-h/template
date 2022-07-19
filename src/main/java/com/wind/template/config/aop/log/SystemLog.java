package com.wind.template.config.aop.log;

import java.lang.annotation.*;

/**
 * @author hsc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 方法描述
     *
     * @return 方法描述
     */
    String msg();
}
