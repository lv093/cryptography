package com.xidian.pki.clientaccess;

import com.xidian.pki.clientaccess.service.UserService;
import com.xidian.pki.clientaccess.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationPid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by LvLiuWei on 2017/9/18.
 */
@SpringBootApplication
@Slf4j
public class ClientAccessApplication {

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ClientAccessApplication.class, args);
        log.info("----FaceAccessApplication Start PID={}----", new ApplicationPid().toString());

        userService.generateUser();


        context.registerShutdownHook();
    }
}
