/*
 * Copyright (c) 2019. AppleTree and/or its affiliates. All rights reserved. Use,Copy is subject to authorized license.
 */

package com.webhook.enums;

import lombok.Getter;

/**
 * 现券业务响应code
 *
 * @Author mzx
 * @since 2022-06-09 20:05
 */
@Getter
public enum ErrorMessageEnum {

    NO_GET_WEB_HOOK_LOCATION("没有获取到webhook配置!"),
    NO_DEFAULT_CONFIGURATION("webhook—api没有默认配置!"),
    ADDRESS_IS_NULL( "webhook地址为空!");



    private String msg;

    ErrorMessageEnum(String msg) {
        this.msg = msg;
    }


}
