package com.lzn.document.documentmanage.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/26 16:21
 **/
@Service
public class PayService {
    @Autowired
    private AliaPay aliaPay;
    @Autowired
    private WeixinPay weixinPay;
    @Autowired
    private JingDongPay jingDongPay;

    public void toPay(String code) {
        if ("alia".equals(code)) {
            aliaPay.pay();
        }else if("weixin".equals(code)){
            weixinPay.pay();
        }
        else if("jingdong".equals(code)) {
            jingDongPay.pay();
        } else{
            System.out.println("找不到支付方式");
        }
    }
}
