package com.gold.jpa.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoParam {

    /**
     * 原值
     */
    private String original;

    /**
     * 加密后的值
     */
    private String encryption;

    public static CryptoParam ofOriginal(String original) {
        return CryptoParam.builder().original(original).build();
    }

    public static CryptoParam ofOriginalJson(Object original) {
        if (Objects.isNull(original)) {
            return null;
        }
        final String json = JSON.toJSONString(original);
        return CryptoParam.ofOriginal(json);
    }
}
