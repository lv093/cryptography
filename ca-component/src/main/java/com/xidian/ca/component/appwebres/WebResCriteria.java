package com.xidian.ca.component.appwebres;

import java.util.Map;

/**
 * Web响应标准类，处理业务层结果
 * Created by LvLiuWei on 2017/8/15.
 */
public class WebResCriteria {

    private Object single;

    private Map<String, Object> object;

    /**
     * 添加单值结果
     * @param value
     */
    public void addSingleResult(Object value) {
        if (null != object) {
            throw new WebResCriteriaException("WebResCriteria map result object already has value");
        }
        this.single = value;
    }

}
