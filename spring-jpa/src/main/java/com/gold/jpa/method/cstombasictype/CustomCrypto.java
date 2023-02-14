package com.gold.jpa.method.cstombasictype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-07-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomCrypto<T> {

    private T original;

    private String encryption;

    public static <T> CustomCrypto ofOriginal(T original) {
        return CustomCrypto.builder().original(original).build();
    }

    public static CustomCrypto<String> ofStringOriginal(String original) {
        return CustomCrypto.<String>builder().original(original).build();
    }
}
