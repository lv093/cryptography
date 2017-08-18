package com.xidian.ca.signissue.service;

import com.xidian.client.entity.User;

/**
 * Created by LvLiuWei on 2017/8/18.
 */
public interface CaSignService {

    /**
     * 增加用户，如果用户身份ID不为空，选择基于ID添加；否则，选择邮箱添加
     * 首先，验证用户的身份证有效性
     * 然后，验证用户邮箱格式正确，合法可用
     * 如果相应校验成功，则利用ID或email作为公钥，生成相应的私钥，以及证书，并存储备份证书
     * @return
     */
    String addUser(User user);
}
