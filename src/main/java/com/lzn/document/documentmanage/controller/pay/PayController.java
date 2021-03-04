package com.lzn.document.documentmanage.controller.pay;

import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.mapper.studentInfo.StudentInfoMapper;
import com.lzn.document.documentmanage.service.pay.impl.PayService2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/26 16:41
 **/
@RestController
@RequestMapping("api/pay")
@Api(description ="支付管理模块相关接口")
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

}
