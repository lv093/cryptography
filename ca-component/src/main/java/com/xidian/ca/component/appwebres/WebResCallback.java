package com.xidian.ca.component.appwebres;


import com.xidian.ca.component.WebResInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web请求响应处理逻辑模块类
 * Created by LvLiuWei on 2017/8/15.
 */
public abstract class WebResCallback {

    private final Logger logger = LoggerFactory.getLogger(WebResCallback.class);

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
