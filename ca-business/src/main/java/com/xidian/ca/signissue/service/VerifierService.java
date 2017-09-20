package com.xidian.ca.signissue.service;

/**
 * Created by LvLiuWei on 2017/8/19.
 */
public interface VerifierService {
    boolean checkUserIdValid(String userId);

    boolean checkEmailValid(String email);
}
