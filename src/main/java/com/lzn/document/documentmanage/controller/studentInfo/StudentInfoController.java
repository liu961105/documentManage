package com.lzn.document.documentmanage.controller.studentInfo;

import com.lzn.document.documentmanage.common.CommonResult;
import com.lzn.document.documentmanage.common.ResponseCode;
import com.lzn.document.documentmanage.mapper.studentInfo.StudentInfoMapper;
import com.lzn.document.documentmanage.mode.studentInfo.StudentInfo;
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
 * @Date 2021/1/28 16:39
 **/
@RestController
@RequestMapping("api/student")
@Api(description = "学生/班级 管理模块相关接口")
public class StudentInfoController {
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @GetMapping("/selectSingleThread")
    @ApiOperation("单线程测试接口")
    public CommonResult selectList(){
        List<StudentInfo> studentInfos = studentInfoMapper.selectList(null);
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),studentInfos);
    }

    @GetMapping("/selectMultithreading")
    @ApiOperation("数据库乐观锁测试接口")
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
