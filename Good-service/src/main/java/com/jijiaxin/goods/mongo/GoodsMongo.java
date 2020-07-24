package com.jijiaxin.goods.mongo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class GoodsMongo {
    @Autowired
    MongoTemplate mongoTemplate;

    private Logger logger = LoggerFactory.getLogger(GoodsMongo.class);

    @Pointcut("execution(public * com.jijiaxin.goods.controller..*.*(..))")
    public void controllerLog() {

    }

    @Before("controllerLog()")
    public void loggerBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Map<String, Object> map = new HashMap<>();

        map.put("time", new Date());

        map.put("class", joinPoint.getSignature().getDeclaringTypeName());

        map.put("metchod", joinPoint.getSignature().getName());

        map.put("name", "jijiaxin");

        System.err.println("1111111111-----------"+map);

        mongoTemplate.save(map, "loggor");
    }
}
