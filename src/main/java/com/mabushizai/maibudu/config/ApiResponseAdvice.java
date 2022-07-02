package com.mabushizai.maibudu.config;

import com.mabushizai.maibudu.annotations.RequireRegister;
import com.mabushizai.maibudu.domain.User;
import com.mabushizai.maibudu.service.UserService;
import com.mabushizai.maibudu.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
@Slf4j
@Aspect
@Component
public class ApiResponseAdvice {

    @Resource
    private UserService userService;

    @Pointcut("execution(public com.mabushizai.maibudu.config.ApiResponse *(..))")
    public void execute() {

    }

    @Around("execute()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        ApiResponse<?> result;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String uid = request.getHeader("x-wx-openid");
            if (!StringUtils.hasLength(uid)) {
                uid = request.getHeader("X-WX-OPENID");
                if (!StringUtils.hasLength(uid)) {
                    return ApiResponse.error("非法用户访问");
                }
            }
            UserContext.setUid(uid);
            User user = userService.findByUid();
            if (null == user && isRequireRegister(pjp)) {
                return ApiResponse.error("非法用户访问");
            }
            UserContext.setUser(user);
            result = (ApiResponse<?>) pjp.proceed();
            log.info("{} request {} success.", uid, pjp.getSignature());
        } catch (Throwable e) {
            result = ApiResponse.error(e.getMessage());
            log.error("{} request failed.", pjp.getSignature(), e);
        } finally {
            UserContext.remove();
        }
        return result;
    }

    public boolean isRequireRegister(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequireRegister requireRegister = method.getAnnotation(RequireRegister.class);
        if (null != requireRegister) {
            return requireRegister.require();
        }
        return false;
    }

}
