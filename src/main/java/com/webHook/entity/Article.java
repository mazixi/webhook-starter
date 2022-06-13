package com.webHook.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mzx
 * @since 2022-06-09 19:58
 */
@Data
@Accessors(chain = true)
public class Article {

    private String title;

    private String description;

    private String url;

    private String picUrl;

}
