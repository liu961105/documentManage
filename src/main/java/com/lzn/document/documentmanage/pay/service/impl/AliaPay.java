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
@PayCode(value = "alia",name = "支付宝支付")
public class AliaPay implements IPay {
    @Override
    public String pay() {
        return "===发起支付宝支付===";
    }
}
