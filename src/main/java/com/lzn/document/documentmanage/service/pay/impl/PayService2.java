package com.lzn.document.documentmanage.service.pay.impl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lzn.document.documentmanage.service.pay.IPay;
import com.lzn.document.documentmanage.utils.PayCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/26 16:30
 **/
@Service
public class PayService2 implements ApplicationListener<ContextRefreshedEvent> {
    private static Map<String, IPay> payMap = null;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);
        if (beansWithAnnotation != null) {
            payMap = new HashMap<>();
            beansWithAnnotation.forEach((key, value) ->{
                String bizType = value.getClass().getAnnotation(PayCode.class).value();
                payMap.put(bizType, (IPay) value);
            });
        }
    }
    public String pay(String code) {
        return payMap.get(code).pay();
    }

    public static void main(String[] args) throws ParseException {
        String startTime = "2018020100000";
        String endTime = "2018022800000";
        for (int i=0;i<24;i++) {
            Date date = new Date();
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(startTime);
            Calendar begin=Calendar.getInstance();
            begin.setTime(date);
            begin.add(Calendar.MONTH, 1);
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dateOne = begin.getTime();
            String format = df.format(dateOne);
            startTime = format;
            System.out.println("开始时间》》》》》》》》》》》"+startTime);
            /**
             *
             *
             *
             */
            Date date2 = new Date();
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(endTime);
            Calendar begin2=Calendar.getInstance();
            begin.setTime(date);
            begin.add(Calendar.MONTH, 1);
            DateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dateTwo = begin.getTime();
            String formatTwo = df.format(dateTwo);
            endTime = formatTwo;
            System.out.println("结束时间》》》》》》》》》》》"+endTime);

        }
    }
}
