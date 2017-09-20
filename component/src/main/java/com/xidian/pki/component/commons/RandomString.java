package com.xidian.pki.component.commons;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Author: LvLiuWei
 * Created: 2017/9/20.
 */
public class RandomString {

    public static void main(String[] args) {
        RandomString randomString = new RandomString();

        while (true) {
            System.out.print(randomString.getAlpha());
        }
    }

    char getAlpha() {
        return RandomStringUtils.randomAlphabetic(1).toLowerCase().charAt(0);
    }

}
