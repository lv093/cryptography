package com.xidian.ca.signissue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by LvLiuWei on 2017/8/18.
 */
@SpringBootApplication
@Slf4j
public class CaSignIssueApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaSignIssueApplication.class);
        log.info("----------CaSignIssueApplication启动！！！-------");
    }
}
