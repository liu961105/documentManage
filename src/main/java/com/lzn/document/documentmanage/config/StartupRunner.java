package com.lzn.document.documentmanage.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/6 16:03
 **/
@Component
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>服务已成功启动，开始执行加载数据等操作<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
