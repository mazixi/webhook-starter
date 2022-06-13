package com.test;

import com.webHook.BaseApplication;
import com.webHook.entity.WebHookMessage;
import com.webHook.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: mzx
 * @Date: 2022/06/13/16:51
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class MessageTest {

    @Autowired
    MessageService messageService;

    @Test
    public void getMessage(){
        WebHookMessage webHookMessage = WebHookMessage.buildText("hello");
        String webHook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=b4a30fc5-7963-42d3-87f4-9905c8e17584";
        messageService.send(webHookMessage,webHook);
    }
}
