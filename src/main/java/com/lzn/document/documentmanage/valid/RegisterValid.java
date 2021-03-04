package com.lzn.document.documentmanage.valid;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Description TODO
 * @Author LZN
 * @Date 2021/1/22 10:15
 **/
@Data
public class RegisterValid {
    @Length(max = 20,min = 4,message = "用户名长度需要在4到20个字符之间")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$",message = "电话号码格式不正确")
    private String phone;
    @Length(max = 20,min = 4,message = "密码长度需要在4到20个字符之间")
    @NotBlank(message = "密码不能为空")
    private String password;
}
