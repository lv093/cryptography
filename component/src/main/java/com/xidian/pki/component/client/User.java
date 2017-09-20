package com.xidian.pki.component.client;

import lombok.Data;

/**
 * Created by LvLiuWei on 2017/9/18.
 */
@Data
public class User {

    /**
     * 记录编号
     */
    private String recordId;

    /**
     * 登录账号
     */
    private String userEmail;

    /**
     * 登录密码
     */
    private String password;

    private String userName;

    private String userTel;

    private String companyName;

    private String companyTel;

    private String companyAddress;

    private Integer postCode;

    private String companyId;

    private String pcCode;

    private String otherIdentityName;

    private String ortherIdentityNum;
}
