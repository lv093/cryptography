package com.xidian.ca.signissue.service.impl;

import com.xidian.ca.signissue.service.VerifierService;

import java.util.regex.Pattern;
import java.io.IOException;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

/**
 * Created by LvLiuWei on 2017/8/19.
 */
public class VerifierServiceImpl implements VerifierService {

    //身份证第i位上的加权因子
    private final int[] iWeight = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

    //通过模得到对应的校验码
    private final int[] iVerifier = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean checkUserIdValid(String userId) {
        userId = userId.trim();

        //2000年前出生
        String str = "[1-9]{6}19[0-9]{2}"
                + "((0[1-9]{1})|(1[1-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))"
                + "[0-9]{3}[0-9x]{1}";
        Pattern pattern = Pattern.compile(str);
        if (!pattern.matcher(userId).matches()) {
            return false;
        }

        if (userId.length() == 15) {
            userId = transId15To18(userId);
            return true;
        }
        if (userId.length() == 18) {
            String temp = userId.substring(0, 16);
            int verify = 0;
            for (int i = 0; i < temp.length(); i++) {
                verify += (temp.charAt(i) - '0') * iWeight[i];
            }
            if (verify == userId.charAt(17)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新15位
     * @param userId
     * @return
     */
    private String transId15To18(String userId) {
        String newId = null;
        if (null != userId && userId.length() == 15) {
            userId = userId.trim();
            StringBuilder sb = new StringBuilder(userId);
            sb.insert(6, "19");

            int lastVerifier = 0;
            for (int i = 0; i < sb.length(); i++) {
                lastVerifier += (sb.charAt(i) - '0') * iWeight[i];
            }
            lastVerifier = lastVerifier % 11;
            sb.append(lastVerifier);
            newId = sb.toString();
        }
        return newId;
    }

    public boolean checkEmailValid(String email) {
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
}
