package com.wind.template.config.aop.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hsc
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(systemLog)")
    public Object doAround(ProceedingJoinPoint pjp, SystemLog systemLog) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();
            // ip
            log.info("ip地址：" + request.getRemoteAddr());
            // 方法url
            log.info("url：" + request.getRequestURI());
        }
        // 方法签名
        Signature signature = pjp.getSignature();
        log.info("方法名称：" + signature.getDeclaringType() + "." + signature.getName());
        // 方法描述
        String msg = systemLog.msg();
        log.info("方法描述：" + msg);
        // 方法参数
        String[] parameterNames = ((CodeSignature) signature).getParameterNames();
        Object[] parameterValues = pjp.getArgs();
        Map<String, Object> param = new HashMap<>(16);
        for (int i = 0; i < parameterNames.length; i++) {
            param.put(parameterNames[i], parameterValues[i]);
        }
        log.info("方法参数：" + JSON.toJSONString(param));
        Object proceed = pjp.proceed();
        // 方法返回参数
        log.info("方法返回参数：" + JSON.toJSONString(proceed));
        return proceed;
    }
}
