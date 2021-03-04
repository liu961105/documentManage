package com.lzn.document.documentmanage.handler;

import com.lzn.document.documentmanage.rabbitmq.Send;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/3/3 15:02
 **/
@Configuration
@EnableScheduling
public class RabbitMqJob {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqJob.class);
    @Autowired
    private Send send;

    //每30s发送一次
    @Scheduled(cron = "0/30 * * * * ?")
    public void sendMessage() {
        log.info("开始发送消息：" + new Date());
        try {
            send.sendMsg("hello world");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
