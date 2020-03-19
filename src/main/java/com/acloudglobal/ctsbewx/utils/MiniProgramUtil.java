package com.acloudglobal.ctsbewx.utils;

import com.acloudglobal.ctsbewx.dto.OpenIdJson;
import com.acloudglobal.ctsbewx.dto.WxMessageJson;
import com.acloudglobal.ctsbewx.exception.AppException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

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
        return get(String.format(WEI_XIN_API_URL, appId, appSecret, code));
    }

    /**
     * 微信小程序推送单个用户
     *
     * @param accessToken 令牌
     * @param messageJson 消息
     */
    public static void pushTicketMsgToUser(String accessToken, WxMessageJson messageJson) {
        try {
            OpenIdJson json = get(String.format(WEI_XIN_MESSAGE_URL, accessToken));
            if (json.isSuccess()) {
                log.info("MINI PROGRAM PUSH MESSAGE SUCCESSFULLY");
            } else {
                log.info("MINI PROGRAM PUSH MESSAGE FAILED");
            }
        } catch (Exception e) {
            log.info("MINI PROGRAM PUSH MESSAGE FAILED");
        }
    }

    private static OpenIdJson get(String urlPath) throws AppException {
        BufferedReader in = null;
        try {
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }

            return JSONObject.parseObject(stringBuffer.toString(), OpenIdJson.class);
        } catch (Exception e) {
            throw new AppException("获取微信用户唯一标识失败");
        } finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }
}
