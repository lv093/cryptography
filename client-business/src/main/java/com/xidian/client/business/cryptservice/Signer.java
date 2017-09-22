package com.xidian.client.business.cryptservice;

/**
 * Author: LvLiuWei
 * Created: 2017/9/22.
 */
public interface Signer {

    byte[] computeHash();

    boolean sign(byte[] signature);

    boolean sign(byte[] signature, String filePath);
}
