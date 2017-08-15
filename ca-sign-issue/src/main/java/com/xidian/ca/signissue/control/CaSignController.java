package com.xidian.ca.signissue.control;

import com.xidian.ca.component.appwebres.WebResCriteria;
import com.xidian.ca.component.entity.User;
import com.xidian.ca.signissue.service.CaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xidian.ca.component.WebResInfo;

import javax.servlet.http.HttpServletRequest;
import com.xidian.ca.component.appwebres.WebResCallback;
/**
 * Created by LvLiuWei on 2017/8/15.
 */
@RestController
@RequestMapping(value = "ca/management")
public class CaSignController {

    @Autowired
    private CaService caService;

    public WebResInfo addUser(@RequestBody final User user, final HttpServletRequest request) {
        return new WebResCallback() {
            @Override
            public void execute(WebResCriteria criteria, Object... params) {
                criteria.addSingleResult(caService.addUser(user));
            }
        }.sendRequest();
    }

}
