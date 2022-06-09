package com.webhook.service;

import com.webhook.entity.WeWorkWebhookMessage;

public interface MessageService {

    /**
    *  @author mzx on 2021/9/16 10:52
    *  @type   发送企业微信消息--系统配置发送者
    *  @desc
    */
    boolean send(WeWorkWebhookMessage weWorkWebhookMessage);

    /**
     *  @author mzx on 2021/9/16 10:52
     *  @type   发送企业微信消息--自定义发送者
     *  @desc
     */
    boolean send(WeWorkWebhookMessage weWorkWebhookMessage, String webhook);


}
