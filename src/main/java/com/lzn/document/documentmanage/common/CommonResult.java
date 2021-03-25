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
    private Integer status;
    private String msg;
    private Object obj;
    public static CommonResult build() {
        return new CommonResult();
    }
    public static CommonResult ok(String msg) {
        return new CommonResult(200, msg, null);
    }
    public static CommonResult ok(String msg, Object obj) {
        return new CommonResult(200, msg, obj);
    }
    public static CommonResult error(String msg) {
        return new CommonResult(500, msg, null);
    }
    public static CommonResult error(String msg, Object obj) {
        return new CommonResult(500, msg, obj);
    }


}
