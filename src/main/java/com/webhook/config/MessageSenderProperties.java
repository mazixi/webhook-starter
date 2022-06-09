package com.webhook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.message")
public class MessageSenderProperties {

    /**
     * message开关
     */
    private boolean enable = true;

    /**
     * wechet-webhookList
     */
    private List<String> webHookList;


}
