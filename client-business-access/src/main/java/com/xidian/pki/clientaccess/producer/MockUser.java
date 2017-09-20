package com.xidian.pki.clientaccess.producer;

import com.xidian.pki.component.client.User;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20.
 */
public class MockUser {

    private static final String PASSWORD = "123456";

    public User getUser() {
        User user = new User();

        user.setRecordId("");

        user.setUserName(generateUserName());
        user.setUserEmail(generateEmail());
        user.setPassword(PASSWORD);
        user.setUserTel(RandomStringUtils.randomNumeric(11));

        int companyNum = (int)(Math.random()*MockUserData.COMPANYNAMES.length);
        user.setCompanyName(MockUserData.COMPANYNAMES[companyNum]);
        user.setCompanyTel(MockUserData.COMPANYTEL[companyNum]);
        user.setCompanyAddress(MockUserData.COMPANYADDRESS[companyNum]);
        user.setPostCode(MockUserData.POSTCODE[companyNum]);
        user.setCompanyId(MockUserData.COMPANYID[companyNum]);
        user.setPcCode(MockUserData.PCCODE[companyNum]);

        user.setOrtherIdentityNum("-1");
        user.setOtherIdentityName("empty");

        return user;
    }

    private String generateEmail() {
        int emailLen = (int)(Math.random()*12) + 4;
        int suffix = (int)(MockUserData.EMAILSUFFIX.length * Math.random());
        return generateUserName() + MockUserData.EMAILSUFFIX[suffix];
    }
    private String generateUserName() {
        int emailLen = (int)(Math.random()*12) + 4;
        return RandomStringUtils.randomAlphanumeric(1)
                + RandomStringUtils.random(emailLen)
                + RandomStringUtils.randomAlphanumeric(1);
    }
}
