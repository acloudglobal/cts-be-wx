package com.acloudglobal.ctsbewx.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class OpenIdJson {
    /**
     * 错误码
     */
    private int errcode;
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * session
     */
    @JSONField(name = "session_key")
    private String sessionKey;

    public boolean isSuccess() {
        return 0 == errcode || StringUtils.isNotEmpty(openid);
    }
}
