package com.xidian.client.util;

import lombok.Data;

import java.security.cert.X509Certificate;
import java.util.Calendar;

/**
 * Author: LvLiuWei
 * Created: 2017/9/22.
 */
@Data
public class SignatureInfo {

    private X509Certificate x509Certificate;

    private Calendar signDate;

    private Calendar timeStamp;

    private String digestAlgorithm;

    private String hashAlgorithm;

    public SignatureInfo(X509Certificate x509Certificate,
                         Calendar signDate,
                         Calendar timeStamp,
                         String digestAlgorithm,
                         String hashAlgorithm) {
        this.x509Certificate = x509Certificate;
        this.signDate = signDate;
        this.timeStamp = timeStamp;
        this.digestAlgorithm = digestAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }
}
