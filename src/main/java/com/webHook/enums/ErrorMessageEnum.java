package com.webHook.enums;

import lombok.Getter;

/**
 * 现券业务响应code
 *
 * @Author mzx
 * @since 2022-06-09 20:05
 */
@Getter
public enum ErrorMessageEnum {

    NO_GET_WEB_HOOK_LOCATION("没有获取到webHook配置!"),
    NO_DEFAULT_CONFIGURATION("webHook—api没有默认配置!"),
    ADDRESS_IS_NULL("webHook地址为空!");


    private String msg;

    ErrorMessageEnum(String msg) {
        this.msg = msg;
    }


}
