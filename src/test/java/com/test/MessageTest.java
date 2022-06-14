package com.test;

import com.webHook.BaseApplication;
import com.webHook.entity.Article;
import com.webHook.entity.WebHookMessage;
import com.webHook.service.MessageService;
import com.webHook.utils.MarkdownBuffer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("messageServiceImpl")
    @Autowired
    MessageService messageService;

    /**
     * 文本/图片
     */
    @Test
    public void sendMessageToWebHookText() {
        WebHookMessage webHookMessage = WebHookMessage.buildText("这是一个文本信息!");
        messageService.send(webHookMessage);

        String networkImageUrl = "https://bkimg.cdn.bcebos.com/pic/d043ad4bd11373f0f7e32234a50f4bfbfaed04b8?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto";
        WebHookMessage imageMessage =
                WebHookMessage.buildImageMessage(networkImageUrl);
        messageService.send(imageMessage);
    }

    /**
     * 图文卡片
     */
    @Test
    public void sendMessageToWebHookGraphicCard() {
        String networkImageUrl = "https://bkimg.cdn.bcebos.com/pic/d043ad4bd11373f0f7e32234a50f4bfbfaed04b8?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto";
        Article article = new Article()
                .setTitle("这是兰博基尼Reventon简介")
                .setUrl("https://baike.baidu.com/item/%E5%85%B0%E5%8D%9A%E5%9F%BA%E5%B0%BCReventon/8926452?fr=aladdin")
                .setPicurl(networkImageUrl)
                .setDescription("全球共21辆,参考售价1500W!");
        WebHookMessage articleMessage =
                WebHookMessage.buildNewsMessage(article);
        messageService.send(articleMessage);
    }

    /**
     * Markdown
     */
    @Test
    public void sendMessageMarkdown() {
        String networkImageUrl = "https://bkimg.cdn.bcebos.com/pic/d043ad4bd11373f0f7e32234a50f4bfbfaed04b8?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto";

        MarkdownBuffer markdownBuffer = new MarkdownBuffer();
        markdownBuffer
                .h2("这是H2标题").nextLine()
                .h3("这是H3").nextLine()
                .quote("这是quote").quoteEnd()
                .green("这是greenText").nextLine()
                .orange("orangeText").nextLine()
                .gray("grayText").nextLine()
                .code("single line code").nextLine()
                .link("这是兰博基尼Reventon简介", networkImageUrl).nextLine();

        WebHookMessage markDownMessage =
                WebHookMessage.buildMarkDownMessage(markdownBuffer);
        messageService.send(markDownMessage);
    }
}
