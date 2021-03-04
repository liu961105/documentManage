package com.lzn.document.documentmanage.mode.studentInfo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (StudentInfo)实体类
 *
 * @author makejava
 * @since 2021-01-27 10:45:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo{
    /**
    * id
    */
    @TableId
    private Long userId;
    /**
    * 学号
    */
    private Long userNumber;
    /**
    * 学生姓名
    */
    private String userName;
    /**
    * 学生年龄
    */
    private Long userAge;
    /**
    * 学生年级
    */
    private String userGrade;
    /**
    * 学生班级
    */
    private String userClass;
    /**
    * 学生性别
    */
    private String userGender;
    /**
    * 籍贯
    */
    private String userHometown;
    /**
    * 电话
    */
    private String userPhone;
    /**
    * 地址
    */
    private String userAddress;
    /**
    * 备注
    */
    private String userRemark;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
    * 创建人
    */
    private String createUser;
    /**
    * 修改时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
    * 修改人
    */
    private String updateUser;
    @Version
    private Integer version;

}