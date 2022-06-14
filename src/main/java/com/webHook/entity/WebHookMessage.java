package com.webHook.entity;


import com.webHook.utils.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
@Data
@Accessors(chain = true)
public class WebHookMessage {

    private String webHook;

    private String msgtype;

    private Text text;

    private Markdown markdown;

    private Image image;

    private News news;


    @Data
    @Accessors(chain = true)
    public static class Text {
        private String content;
        private List<String> mentionedList;
    }

    @Data
    @Accessors(chain = true)
    public static class Markdown {
        private String content;
    }

    @Data
    @Accessors(chain = true)
    public static class Image {
        private String base64;
        private String md5;
    }

    @Data
    @Accessors(chain = true)
    public static class News {
        private List<Article> articles;
    }


    /**
    *  @author mzx
    *  @type   发送网络图片或者本地图片都可以
    *  @desc
    */
    public static WebHookMessage buildImageMessage(String imagePath) {
        File file;
        try {
            WebHookMessage message = new WebHookMessage();
            message.setMsgtype("image");
            WebHookMessage.Image image = new WebHookMessage.Image();
            if (imagePath.startsWith("http")) {
                file = Fileutils.downloadFile(imagePath, "image", IDUtils.genRandom("image-", 15));
                image.setBase64(ImageToBase64.ImageToBase64(file));
                image.setMd5(MD5Utils.getFileMD5String(file));
                message.setImage(image);
                Fileutils.keepTop(file,1);
            } else {
                file = new File(imagePath);
                image.setBase64(ImageToBase64.ImageToBase64(file));
                image.setMd5(MD5Utils.getFileMD5String(file));
                message.setImage(image);
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
    *  @author mzx on 2021/9/16 10:46
    *  @type   构建markdown消息
    *  @desc
    */
    public static WebHookMessage buildMarkDownMessage(MarkdownBuffer content) {
        WebHookMessage message = new WebHookMessage();
        message.setMsgtype("markdown");
        WebHookMessage.Markdown markdown = new WebHookMessage.Markdown();
        markdown.setContent(content.toString());
        message.setMarkdown(markdown);
        return message;
    }


    /**
    *  @author mzx on 2021/9/16 10:46
    *  @type   批量构建图文卡片链接消息
    *  @desc
    */
    public static WebHookMessage buildNewsMessage(List<Article> articles) {
        WebHookMessage message = new WebHookMessage();
        message.setMsgtype("news");
        WebHookMessage.News news = new WebHookMessage.News();
        news.setArticles(articles);
        message.setNews(news);
        return message;
    }

    /**
     *  @author mzx
     *  @type   构建图文卡片链接消息
     *  @desc
     */
    public static WebHookMessage buildNewsMessage(Article article) {
        WebHookMessage message = new WebHookMessage();
        message.setMsgtype("news");
        WebHookMessage.News news = new WebHookMessage.News();
        List<Article> list = new ArrayList();
        list.add(article);
        news.setArticles(list);
        message.setNews(news);
        return message;
    }


    /**
    *  @author mzx
    *  @type   构建普通文本消息
    *  @desc
    */
    public static WebHookMessage buildText(String content) {
        return buildText(content, false);
    }

    /**
     *  @author mzx
     *  @type   构建普通文本消息（@ALL 指定webHookApi)
     *  @desc
     */
    public static WebHookMessage buildText(String content, boolean atAll) {
        WebHookMessage message = new WebHookMessage();
        message.setMsgtype("text");
        WebHookMessage.Text text = new WebHookMessage.Text();
        text.setContent(content);
        List<String> mentionedList = text.getMentionedList();
        if (atAll) {
            if (mentionedList == null) {
                mentionedList = new ArrayList<>();
            }
            mentionedList.add("@all");
            text.setMentionedList(mentionedList);
        }
        message.setText(text);
        return message;
    }

}
