package com.webhook.service;

import com.webhook.entity.WebHookMessage;

/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
public interface MessageService {

    /**
     * 发送企业微信消息--系统配置发送者
     * @param webhookMessage 消息体
     * @return 发送消息
     */
    boolean send(WebHookMessage webhookMessage);

    /**
     * 发送企业微信消息--自定义发送者
     * @param webhookMessage
     * @param webhook
     * @return 发送消息
     *
     */
    boolean send(WebHookMessage webhookMessage, String webhook);


}
