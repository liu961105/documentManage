package com.lzn.document.documentmanage.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.mapper.StudentInfoMapper;
import com.lzn.document.documentmanage.mode.StudentInfo;
import com.lzn.document.documentmanage.pay.service.impl.PayService2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/26 16:41
 **/
@RestController
@RequestMapping("/pay")
@Api(description ="支付")
public class PayController {

    @Autowired
    private PayService2 payService;
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @GetMapping("/toPay")
    @ApiOperation("去支付接口")
    public CommonResult toPay(String code){
        String pay = payService.pay(code);
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),pay);
    }
    @GetMapping("/selectList")
    @ApiOperation("测试接口")
    public CommonResult selectList(){
        List<StudentInfo> studentInfos = studentInfoMapper.selectList(null);
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),studentInfos);
    }

    @GetMapping("/selectOne")
    @ApiOperation("测试接口")
    public CommonResult selectOne(){
        StudentInfo studentInfo = studentInfoMapper.selectById(1);
        studentInfo.setUserAge(23L);
        // 模拟另外一个线程执行了插队操作
        StudentInfo studentInfo2 = studentInfoMapper.selectById(1);
        studentInfo2.setUserAge(20L);
        studentInfoMapper.updateById(studentInfo);
        // 自旋锁来多次尝试提交！
        studentInfoMapper.updateById(studentInfo);
        // 如果没有乐观锁就会覆盖插队线程的值！
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),"");
    }

}
