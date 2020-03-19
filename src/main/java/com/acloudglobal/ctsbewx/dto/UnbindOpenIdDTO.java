package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

@Data
public class UnbindOpenIdDTO {
    /**
     * 微信小程序用户唯一标识
     */
    private String openid;
    /**
     * 登录密码
     */
    private String password;
}
