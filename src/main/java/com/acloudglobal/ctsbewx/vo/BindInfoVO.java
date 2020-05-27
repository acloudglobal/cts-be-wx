package com.acloudglobal.ctsbewx.vo;

import lombok.Data;

@Data
public class BindInfoVO {
    /**
     * 接入秘钥
     */
    private String token;

    /**
     * 账户ID
     */
    private String acctId;
}
