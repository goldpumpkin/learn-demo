package com.gold.jpa.crypto;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-08-20
 */
public class CryptoUtil {

    public static String encrypt(String ori) {
        if ("".equals(ori)) {
            return "";
        }
        return ori + "qwer";
    }

    public static String decrypt(String encrypted) {
        if ("".equals(encrypted)) {
            return "";
        }
        return encrypted.substring(0, encrypted.length() - 4);
    }
}
