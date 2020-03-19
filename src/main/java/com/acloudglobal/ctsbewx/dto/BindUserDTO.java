package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

@Data
public class BindUserDTO {
    /**
     * 微信小程序用户唯一标识
     */
    private String openid;

    /**
     * 账户编码
     */
    private String acctId;

    /**
     * 用户名，新增时输入
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;
}
