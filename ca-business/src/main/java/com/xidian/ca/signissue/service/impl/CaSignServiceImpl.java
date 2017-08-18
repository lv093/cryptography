package com.xidian.ca.signissue.service.impl;

import com.xidian.ca.signissue.service.CaSignService;
import com.xidian.client.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import java.io.IOException;

/**
 * Created by LvLiuWei on 2017/8/18.
 */
@Service
@Slf4j
public class CaSignServiceImpl implements CaSignService {

    @Override
    public String addUser(User user) {
        if (user.getUserId() != null) {
            if (checkUserIdValid(user.getUserId())) {

            }
        } else {
            if (checkEmailValid(user.getEmail())) {

            }
        }

        return null;
    }

    private boolean checkEmailValid(String email) {

        if(!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            log.error("邮箱{}校验未通过，格式不正确！", email);
            return false;
        }

        String host = "";
        String hostName = email.split("@")[1];

        Record[] result = null;
        SMTPClient client = new SMTPClient();
        try {
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                log.error("邮箱{}校验未通过，未找到对应的MX记录！", email);
                return false;
            } else {
                result = lookup.getAnswers();
            }
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                log.info("SMTPClient try connect to host {}", host);

                client.connect(host);

                if(!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    client.disconnect();
                    continue;
                } else {
                    log.info("找到MX记录： {}", hostName);
                    log.info("建立连接成功：{}", hostName);
                    break;
                }
            }
            log.info("SMTPClient ReplyString: {}", client.getReplyString());

            String emailSuffix = "163.com";
            String emailPrefix = "ranguisheng";
            String fromEmail = emailPrefix + "@" + emailSuffix;

            client.login(emailPrefix);
            log.info("SMTPClient login: {}...", emailPrefix);
            log.info("SMTPClient ReplyString: {}", client.getReplyString());

            client.setSender(fromEmail);
            log.info("设置发送者：{}", fromEmail);
            log.info("SMTPClient ReplyString:{}", client.getReplyString());

            client.addRecipient(email);
            log.info("设置接收者：{}", email);
            log.info("SMTPClient ReplyString:{}", client.getReplyString());
            log.info("SMTPClient ReplyCode:{}(250表示正常)", client.getReplyCode());
            if(250 == client.getReplyCode()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 检查用户18位身份证的有效性
     * @param userId
     * @return
     */
    private boolean checkUserIdValid(String userId) {


        return false;
    }
}
