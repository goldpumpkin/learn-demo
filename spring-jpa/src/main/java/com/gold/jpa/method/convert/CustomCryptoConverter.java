package com.gold.jpa.method.convert;


import com.gold.jpa.crypto.CryptoUtil;
import com.gold.jpa.model.CryptoParam;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Slf4j
@Converter
public class CustomCryptoConverter implements AttributeConverter<CryptoParam, String> {

    @Override
    public String convertToDatabaseColumn(CryptoParam customField) {
        if (customField == null) {
            return null;
        }

        final String original = customField.getOriginal();
        final String encrypt = CryptoUtil.encrypt(original);
        // 如果不设置 则在内存实例中获取不到加密数据
        customField.setEncryption(encrypt);
        return encrypt;
    }

    @Override
    public CryptoParam convertToEntityAttribute(String dbData) {
        if (Objects.isNull(dbData)) {
            return null;
        }
        final String decrypt = CryptoUtil.decrypt(dbData);
        return CryptoParam.builder().encryption(dbData).original(decrypt).build();
    }
}

