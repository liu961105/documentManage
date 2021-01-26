package com.lzn.document.documentmanage.common.exception;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/22 10:05
 **/
public class CustomizeException extends RuntimeException{
    private String msg;

    public CustomizeException(String msg) {
        super(msg);
    }
}
