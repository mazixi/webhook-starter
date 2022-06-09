package com.webhook.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author smallAttr
 * @since 2019-11-13 15:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.message")
public class MessagesenderProperties {

    /**
     * message开关
     */
    private boolean enable = true;

    /**
     * wechet-webhookList
     */
    private List<String> webHookList;


}
