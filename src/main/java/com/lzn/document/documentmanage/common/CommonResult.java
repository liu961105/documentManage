package com.lzn.document.documentmanage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/22 9:54
 * 通用返回体
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private int code;
    private String message;
    private Object data;

}
