package com.njwd.costprocess.aop;

import com.njwd.costprocess.constant.ErrorCode;
import com.njwd.costprocess.entity.ApiMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ControllerAdvice("com.njwd.costprocess.controller")
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiMessage errorMsg(HttpServletRequest request, Exception ex){
        logger.error("错误链接"+request.getRequestURL().toString());

        //将异常信息写入stream流，然后存入log
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        logger.info(baos.toString());

        return ApiMessage.error(ErrorCode.SYS_FORMAT_ERROR);
    }
}