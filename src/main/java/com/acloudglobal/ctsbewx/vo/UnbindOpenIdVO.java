package com.acloudglobal.ctsbewx.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
public class UnbindOpenIdVO {
    /**
     * 微信小程序用户唯一标识
     */
    @NotEmpty(message = "用户唯一标识不能为空")
    private String openid;

    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,20}$",
             message = "密码由8~20位数字、英文字母或者特殊字符组成")
    private String password;
}
