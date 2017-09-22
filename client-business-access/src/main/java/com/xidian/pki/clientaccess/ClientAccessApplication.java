package com.xidian.pki.clientaccess;

import com.xidian.pki.clientaccess.service.UserService;
import com.xidian.pki.clientaccess.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationPid;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.ws.Service;

/**
 * Created by LvLiuWei on 2017/9/18.
 */
@SpringBootApplication
@Slf4j
public class ClientAccessApplication {

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(ClientAccessApplication.class)
                .web(false)
                .run(args);
        log.info("----FaceAccessApplication Start PID={}----", new ApplicationPid().toString());

        userService.generateUserList(10);
        context.registerShutdownHook();
    }
}
