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
@PayCode(value = "jingdong",name = "京东支付")
public class JingDongPay implements IPay {
    @Override
    public String pay() {
        return "===发起京东支付===";
    }
}
