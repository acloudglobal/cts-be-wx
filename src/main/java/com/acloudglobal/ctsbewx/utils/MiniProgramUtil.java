package com.acloudglobal.ctsbewx.utils;

import com.acloudglobal.ctsbewx.dto.OpenIdJson;
import com.acloudglobal.ctsbewx.dto.WxMessageJson;
import com.acloudglobal.ctsbewx.exception.AppException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class MiniProgramUtil {
    private static final String WEI_XIN_API_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s" +
            "&js_code=%s&grant_type=authorization_code";

    private static final String WEI_XIN_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
            "?access_token=%s";


    public static OpenIdJson getOpenid(String appId, String appSecret, String code) throws Exception {
        Response response = HttpUtil.get(String.format(WEI_XIN_API_URL, appId, appSecret, code), null);
        return HttpUtil.parseResponseToObject(response, OpenIdJson.class);
    }

    /**
     * 微信小程序推送单个用户
     *
     * @param accessToken 令牌
     * @param messageJson 消息
     */
    public static void pushTicketMsgToUser(String accessToken, WxMessageJson messageJson) {
        Response response = HttpUtil.get(String.format(WEI_XIN_MESSAGE_URL, accessToken), null);
        OpenIdJson json = HttpUtil.parseResponseToObject(response, OpenIdJson.class);
        if (null != json && json.isSuccess()) {
            log.info("MINI PROGRAM PUSH MESSAGE SUCCESSFULLY");
        } else {
            log.info("MINI PROGRAM PUSH MESSAGE FAILED");
        }
    }
}
