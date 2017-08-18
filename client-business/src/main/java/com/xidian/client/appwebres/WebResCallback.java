package com.xidian.client.appwebres;

import com.xidian.client.WebResInfo;

/**
 * Web请求响应处理逻辑模块类
 * Created by LvLiuWei on 2017/8/15.
 */
public abstract class WebResCallback {

    private final WebResCriteria criteria = new WebResCriteria();

    /**
     * 处理逻辑
     * @param criteria 处理结果
     * @param params 请求参数
     */
    public abstract void execute(final WebResCriteria criteria, Object... params);

    public final WebResInfo sendRequest(Object... params) {
        WebResInfo webResInfo = new WebResInfo();

        execute(criteria, params);

        return webResInfo;
    }
}
