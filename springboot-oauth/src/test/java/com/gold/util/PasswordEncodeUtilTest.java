package com.gold.util;

import org.junit.Test;

public class PasswordEncodeUtilTest {

    @Test
    public void genOauthEncodePwd() {
        String oriPwd = "123456";
        System.out.println(PasswordEncodeUtil.bcryptEncode(oriPwd));
        System.out.println(PasswordEncodeUtil.genOauthEncodePwd(oriPwd));
    }
}