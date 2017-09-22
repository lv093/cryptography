package com.xidian.pki.clientaccess.service;


import com.xidian.pki.component.client.User;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20.
 */
public interface UserService {

    void generateUserList(int count);

    /**
     * 模拟用户数据
     * @return
     */
    User generateUser();
}
