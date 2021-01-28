package com.lzn.document.documentmanage.pay.service.impl;

import com.lzn.document.documentmanage.pay.service.IPay;
import com.lzn.document.documentmanage.utils.PayCode;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/26 16:18
 **/
@Service
@PayCode(value = "weixin", name = "微信支付")
public class WeixinPay implements IPay {
    @Override
    public String pay() {
        return "===发起微信支付===";
    }
}
