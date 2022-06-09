package com.webhook;

import com.webhook.config.MessageSenderProperties;
import com.webhook.service.MessageService;
import com.webhook.service.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;


/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
@Slf4j
@Configuration
@AutoConfigureOrder(value = Ordered.HIGHEST_PRECEDENCE)
public class MessageSenderAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(search = SearchStrategy.CURRENT)
    public MessageSenderProperties getProperties() {
        return new MessageSenderProperties();
    }


    @Bean
    public MessageService getMessageSenderService() {
        MessageSenderProperties properties = getProperties();
        if (ObjectUtils.isEmpty(properties.getWebHookList())) {
            log.error("加载webhook—api默认配置失败");
            throw new RuntimeException("webhook—api没有默认配置");
        }
        log.info("已成功加载[{}]个webhook—api默认配置", properties.getWebHookList().size());
        return new MessageServiceImpl(properties);
    }
}
