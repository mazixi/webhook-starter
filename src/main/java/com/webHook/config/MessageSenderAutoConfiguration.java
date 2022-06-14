package com.webHook.config;

import com.webHook.enums.ErrorMessageEnum;
import com.webHook.service.MessageService;
import com.webHook.service.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    public MessageService getMessageSenderService() {
        MessageSenderProperties properties = getProperties();
        if (ObjectUtils.isEmpty(properties.getWebHookList())) {
            log.error("加载webHook—api默认配置失败");
            throw new RuntimeException(ErrorMessageEnum.NO_DEFAULT_CONFIGURATION.getMsg());
        }
        log.info("已成功加载[{}]个webHook—api默认配置", properties.getWebHookList().size());
        return new MessageServiceImpl(properties);
    }
}
