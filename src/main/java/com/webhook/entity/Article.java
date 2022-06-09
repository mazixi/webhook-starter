package com.webhook.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazixi
 *
 */
@Data
@Accessors(chain = true)
public class Article {

    private String title;

    private String description;

    private String url;

    private String picurl;

}
