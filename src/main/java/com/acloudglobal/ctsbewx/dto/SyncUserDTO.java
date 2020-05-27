package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

@Data
public class SyncUserDTO {

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

    /**
     * 接入秘钥
     */
    private String token;

}