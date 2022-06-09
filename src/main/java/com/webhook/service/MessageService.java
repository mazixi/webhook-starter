package com.webhook.service;

import com.webhook.entity.WeWorkWebhookMessage;

/**
 * @author mazixi
 * @since 2022-06-09 19:58
 */
public interface MessageService {

    /**
    *  @author mzx
    *  @type   发送企业微信消息--系统配置发送者
    *  @desc
    */
    boolean send(WeWorkWebhookMessage weWorkWebhookMessage);

    /**
     *  @author mzx
     *  @type   发送企业微信消息--自定义发送者
     *  @desc
     */
    boolean send(WeWorkWebhookMessage weWorkWebhookMessage, String webhook);


}
