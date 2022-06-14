package com.webHook.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webHook.config.MessageSenderProperties;
import com.webHook.entity.WebHookMessage;
import com.webHook.enums.ErrorMessageEnum;
import com.webHook.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
@Slf4j
@Component
public class MessageServiceImpl implements MessageService {

    final Integer ZERO = 0;

    private List<String> webHookList;


    public MessageServiceImpl(@Qualifier("messageSenderProperties") MessageSenderProperties messagesenderProperties) {
        this.webHookList = messagesenderProperties.getWebHookList();
        if (CollectionUtils.isEmpty(webHookList)) {
            throw new RuntimeException(ErrorMessageEnum.NO_GET_WEB_HOOK_LOCATION.getMsg());
        }
    }

    @Override
    public boolean send(WebHookMessage webHookMessage) {
        log.info("webHookList:{}", JSON.toJSONString(webHookList));
        return send(webHookMessage, webHookList.get(0));
    }

    @Override
    public boolean send(WebHookMessage webHookMessage, String webHook) {
        if (StringUtils.isEmpty(webHook)) {
            throw new RuntimeException(ErrorMessageEnum.ADDRESS_IS_NULL.getMsg());
        }
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", "application/json");
        headerParams.put("charset", "utf-8");
        String responseStr = HttpClientUtil.doPostJson(webHook, headerParams, JSONObject.toJSONString(webHookMessage));
        if (ZERO.equals(JSONObject.parseObject(responseStr).getInteger("errcode"))) {
            log.info("企业微信webHook消息发送成功");
            log.debug("webHook地址为：[{}]", webHook);
            log.debug("消息内容为：[{}]", JSONObject.toJSONString(webHookMessage, true));
            log.debug("响应为：[{}]", responseStr);
            return true;
        } else {
            log.error("企业微信webHook消息发送失败");
            log.error("webHook地址为：[{}]", webHook);
            log.error("消息内容为：[{}]", JSONObject.toJSONString(webHookMessage, true));
            log.error("响应为：[{}]", responseStr);
            return false;
        }
    }
}
