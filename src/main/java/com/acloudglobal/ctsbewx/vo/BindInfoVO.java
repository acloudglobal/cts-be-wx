package com.acloudglobal.ctsbewx.vo;

import lombok.Data;

@Data
public class BindInfoVO {
    /**
     * 接入秘钥
     */
    private String token;

    /**
     * 租户后台域名
     */
    private String acctUid;
}
