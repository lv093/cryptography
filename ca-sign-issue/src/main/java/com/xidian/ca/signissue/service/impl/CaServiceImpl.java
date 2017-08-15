package com.xidian.ca.signissue.service.impl;

import com.xidian.ca.component.entity.User;
import com.xidian.ca.signissue.service.CaService;

/**
 * Created by LvLiuWei on 2017/8/15.
 */
public class CaServiceImpl implements CaService {
    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @Override
    public Object addUser(User user) {
        userValid(user);
        return null;
    }

    private boolean userValid(User user) {
        if (user == null) {
            return false;
        }
        boolean isValid = false;

        //检查用户身份证的有效性
        if (userIdValid(user.getUid())) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * 检查用户身份证的有效性
     * @param uid
     * @return
     */
    private boolean userIdValid(Integer uid) {
        boolean isValid = false;

        return isValid;
    }
}
