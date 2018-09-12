package com.tfive.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
*   在实际业务中我们需要定义不同的异常
*       比如说 404 文件找不到异常
*       后台错误 500 的异常
*   所以说需要自定义不同的异常处理类
* */

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(Exception.class);

    //拦截所有异常
    @ExceptionHandler(value=Exception.class)
    public ModelAndView handlerException(Exception e, HttpServletRequest request) {
        //记录日志
        logger.error("URL: {},MSG: {}",request.getRequestURL(),e.getMessage());

        //判断异常 根据状态码交给Spring去处理
        /*if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }*/
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",e.getMessage());
        mv.addObject("url",request.getRequestURL());
        mv.setViewName("error/error");
        return mv;
    }

}
