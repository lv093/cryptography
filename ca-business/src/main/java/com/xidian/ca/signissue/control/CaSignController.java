package com.xidian.ca.signissue.control;

import com.xidian.ca.signissue.service.CaSignService;
import com.xidian.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LvLiuWei on 2017/8/18.
 */
@RestController
@RequestMapping(value = "ca/service/sign")
public class CaSignController {

    @Autowired
    private CaSignService caSignService;

    @PostMapping(value = "addUser")
    public String addUser(@RequestBody final User user) {
        String result = caSignService.addUser(user);

        return user.getPk();
    }
}
