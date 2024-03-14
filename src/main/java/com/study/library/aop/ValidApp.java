package com.study.library.aop;

import com.study.library.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class ValidApp {

    @Pointcut("@annotation(com.study.library.aop.annotation.ValidAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;
        for(Object arg : args) {
            if(BeanPropertyBindingResult.class == arg.getClass()) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }

        }

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }


        return proceedingJoinPoint.proceed();
    }
}
