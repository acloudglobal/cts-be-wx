package com.acloudglobal.ctsbewx.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WxMessageJson {
    /**
     * （必填） 接收者（用户）的 openid
     */
    private String touser;
    /**
     * 必填） 所需下发的模板消息的id
     */
    @JSONField(name = "template_id")
    private String templateId;
    /**
     * 可选） 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     */
    private String page;
    /**
     * 必填） 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     */
    @JSONField(name = "form_id")
    private String formId;
    private WxMessageData data;
}
