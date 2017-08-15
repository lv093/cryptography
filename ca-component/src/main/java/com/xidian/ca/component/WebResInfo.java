package com.xidian.ca.component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Web接口相应信息
 * Created by LvLiuWei on 2017/8/15.
 */
@Setter
@Getter
@ToString
public class WebResInfo {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应结果即数据
     */
    private Object data;
}
