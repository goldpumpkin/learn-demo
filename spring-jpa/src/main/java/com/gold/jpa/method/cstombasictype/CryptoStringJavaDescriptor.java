package com.gold.jpa.method.cstombasictype;

import com.gold.jpa.crypto.CryptoUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

public class CryptoStringJavaDescriptor extends AbstractTypeDescriptor<CustomCrypto> {

    public static final CryptoStringJavaDescriptor INSTANCE = new CryptoStringJavaDescriptor();

    public CryptoStringJavaDescriptor() {
        super(CustomCrypto.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public String toString(CustomCrypto value) {
        return value.toString();
    }

    @Override
    public CustomCrypto fromString(String string) {
        String encryption = string;
        String ori = encryption.substring(0, encryption.length() - 1);
        return CustomCrypto.builder()
                .original(ori)
                .encryption(encryption)
                .build();
    }

    /**
     * javaType 到数据库 type
     */
    @Override
    public <X> X unwrap(CustomCrypto value, Class<X> type, WrapperOptions options) {
        if (value == null)
            return null;

        if (String.class.isAssignableFrom(type)) {
            String encryption = value.getEncryption();
            if (StringUtils.isEmpty(encryption)) {
                encryption = CryptoUtil.encrypt((String) value.getOriginal());
            }
            value.setEncryption(encryption);
            return (X) encryption;
        }

        throw unknownUnwrap(type);
    }

    @Override
    public <X> CustomCrypto wrap(X value, WrapperOptions options) {
        if (value == null)
            return null;

        if (String.class.isInstance(value)) {
            String encryption = (String) value;
            String ori = CryptoUtil.decrypt(encryption);
            return CustomCrypto.builder()
                    .original(ori)
                    .encryption(encryption)
                    .build();
        }

        throw unknownWrap(value.getClass());
    }
}
