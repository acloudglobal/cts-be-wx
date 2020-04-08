package com.acloudglobal.ctsbewx.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
public class SyncUserVO {


    /**
     * 账户编码
     */
    @NotEmpty(message = "账户编号不能为空")
    private String acctId;

    /**
     * 用户名，新增时输入
     */
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^\\w{3,16}$", message = "用户名由3~16位数字、英文字母或下划线组成")
    private String userName;

    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 接入秘钥
     */
    @NotEmpty(message = "接入秘钥不能为空")
    private String token;

    /**
     * 租户后台域名
     */
    @NotEmpty(message = "租户后台域名不能为空")
    @Pattern(regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$",
             message = "租户后台IP格式不正确")
    private String host;

}