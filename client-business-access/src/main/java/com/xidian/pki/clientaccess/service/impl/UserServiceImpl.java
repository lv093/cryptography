package com.xidian.pki.clientaccess.service.impl;

import com.xidian.pki.clientaccess.process.Processor;
import com.xidian.pki.clientaccess.producer.MockUser;
import com.xidian.pki.clientaccess.producer.MockUserData;
import com.xidian.pki.clientaccess.service.UserService;
import com.xidian.pki.component.client.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Processor<User> userProcessor;

    @Override
    public User generateUser() {
        return new MockUser().getUser();
    }

    public void generateUserList(int count) {
        List<User> userList = new ArrayList<>(count);
        for (int i=0; i<count; i++) {
            userList.add(generateUser());
        }
        userProcessor.process(userList);
    }
}
