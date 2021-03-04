package com.lzn.document.documentmanage.controller.exception;

import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.common.exception.CustomizeException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/22 10:07
 **/
@RestControllerAdvice
public class ExceptionController {
    public CommonResult exceptionHandler(Exception e) {
        //如果抛出的异常属于自定义异常，就以JSON格式返回
        if (e instanceof CustomizeException) {
            return new CommonResult(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), "自定义的错误为：" + e.getMessage());
        }
        //如果都不是就打印出异常的信息
        return new CommonResult(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), "错误的信息为：" + e.getMessage());
    }
}
