package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

@Data
public class BindInfoDTO {
    /**
     * 接入秘钥
     */
    private String token;

    /**
     * 账户ID
     */
    private String acctId;
}
