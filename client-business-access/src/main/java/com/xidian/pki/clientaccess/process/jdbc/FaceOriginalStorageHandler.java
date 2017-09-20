package com.xidian.pki.clientaccess.process.jdbc;


import com.xidian.pki.clientaccess.process.Processor;
import com.xidian.pki.component.client.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20
 */
@Slf4j
@Service
public class FaceOriginalStorageHandler implements Processor<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void process(final List<User> userList) {

        if (userList == null || userList.isEmpty()) {
            return;
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("INSERT INTO data_face_original")
                .append("(")
                .append("record_id,")
                .append("user_email,")
                .append("password,")
                .append("user_name,")
                .append("user_tel,")

                .append("company_name,")
                .append("company_tel,")
                .append("company_address,")
                .append("post_code,")
                .append("company_id,")
                .append("pc_code,")

                .append("other_id_name,")
                .append("other_id_num")
                .append(")")
                .append(" VALUES ");

        for (User user : userList) {
            sqlBuilder
                    .append("(")
                    .append("'").append(user.getRecordId()).append("',")
                    .append("'").append(user.getUserEmail()).append(",")
                    .append("'").append(user.getPassword()).append(",")
                    .append("'").append(user.getUserName()).append("',")
                    .append(user.getUserTel()).append("'").append(",")

                    .append("'").append(user.getCompanyName()).append("',")
                    .append("'").append(user.getCompanyTel()).append("',")
                    .append("'").append(user.getCompanyAddress()).append(",")
                    .append(user.getPostCode()).append(",")
                    .append("'").append(user.getCompanyId()).append("'").append(",")
                    .append("'").append(user.getPcCode()).append("'").append(",")

                    .append("'").append(user.getOtherIdentityName()).append("'").append(",")
                    .append("'").append(user.getOrtherIdentityNum()).append("'")
                    .append("),");
        }
        sqlBuilder.setLength(sqlBuilder.length() - 1);

        try {
            int count = jdbcTemplate.update(sqlBuilder.toString());
            if (count >= 1) {
                log.warn("User data into MySQL Success!{}", count);
            } else {
                log.info("User data into MySQL Failure!{}", count);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}

