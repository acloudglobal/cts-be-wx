package com.acloudglobal.ctsbewx.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {
    public static final MediaType CONTENT_TYPE = MediaType.parse("application/json");
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();


    public static Response postJsonBody(String url, Headers headers, RequestBody body) {

        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (null != headers) {
            builder.headers(headers);
        }
        builder.addHeader("Content-Type", "application/json");

        try {
            return okHttpClient.newCall(builder.build()).execute();
        } catch (IOException e) {
            log.error("HTTP-UTIL POST JSON MESSAGE FAIL,THE ERROR", e);
            return new Response.Builder().code(500).build();
        }
    }


    public static Response postFormBody(String url, Headers headers, FormBody body) {
        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (null != headers) {
            builder.headers(headers);
        }
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");

        try {
            return okHttpClient.newCall(builder.build()).execute();
        } catch (IOException e) {
            log.error("HTTP-UTIL POST FORM MESSAGE FAIL,THE ERROR", e);
            return new Response.Builder().code(500).build();
        }
    }


    public static Response get(String url, Headers headers) {
        Request.Builder builder = new Request.Builder().url(url).get();
        if (null != headers) {
            builder.headers(headers);
        }
        try {
            return okHttpClient.newCall(builder.build()).execute();
        } catch (IOException e) {
            log.error("HTTP-UTIL GET MESSAGE FAIL,THE ERROR", e);
            return new Response.Builder().code(500).build();
        }
    }

    public static <T> T parseResponseToObject(Response response, Class<T> clazz) {


        try {
            assert response.body() != null;
            String body = response.body().string();
            log.info("HTTP-UTIL RESPONSE BODY {}", body);
            if (!response.isSuccessful()) {
                return null;
            }
            return JSONObject.parseObject(body, clazz);
        } catch (IOException e) {
            log.error("HTTP-UTIL PARSE RESPONSE BODY FAIL,THE ERROR", e);
            return null;
        }
    }
}
