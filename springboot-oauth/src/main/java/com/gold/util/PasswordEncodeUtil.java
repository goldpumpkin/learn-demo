package com.gold.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author goldhuang
 * @Description: oauth 密码加密
 * @date 2020-09-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordEncodeUtil {

    private static final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    public static String bcryptEncode(String password) {
        return bcryptEncoder.encode(password);
    }

    public static String genOauthEncodePwd(String password) {
        return "{bcrypt}" + bcryptEncode(password);
    }


//    public static void main(String[] args) {
//        String oriPwd = "123456";
//        System.out.println(bcryptEncode(oriPwd));
//        System.out.println(genOauthEncodePwd(oriPwd));
//    }
}
