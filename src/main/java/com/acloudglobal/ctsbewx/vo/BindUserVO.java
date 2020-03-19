package com.acloudglobal.ctsbewx.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
public class BindUserVO {
    /**
     * 微信code
     */
    private String code;

    /**
     * 账户编码
     */
    @NotEmpty(message = "账户编号不能为空")
    private String acctId;

    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^\\w{3,16}$", message = "用户名由3~16位数字、英文字母或下划线组成")
    private String userName;

    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,20}$",
             message = "密码由8~20位数字、英文字母或者特殊字符组成")
    private String password;
}
