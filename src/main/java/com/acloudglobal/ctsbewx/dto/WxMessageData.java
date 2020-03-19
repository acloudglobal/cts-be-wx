package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

@Data
public class WxMessageData {
    private KeywordValue keyword1;
    private KeywordValue keyword2;
    private KeywordValue keyword3;
    private KeywordValue keyword4;


    @Data
    public static class KeywordValue{
        private String value;
    }
}
