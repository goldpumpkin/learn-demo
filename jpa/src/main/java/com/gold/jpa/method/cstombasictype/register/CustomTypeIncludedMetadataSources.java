package com.gold.jpa.method.cstombasictype.register;

import com.gold.jpa.method.cstombasictype.CryptoStringType;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.internal.MetadataBuilderImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-07-11
 */
public class CustomTypeIncludedMetadataSources extends MetadataSources {

    @Override
    public MetadataBuilder getMetadataBuilder() {
        MetadataBuilder b = new MetadataBuilderImpl(this);
        applyCustomTypes(b);
        return b;
    }

    @Override
    @Deprecated
    public MetadataBuilder getMetadataBuilder(StandardServiceRegistry serviceRegistry) {
        MetadataBuilder b = new MetadataBuilderImpl(this, serviceRegistry);
        applyCustomTypes(b);
        return b;
    }

    private void applyCustomTypes(MetadataBuilder b) {
        b.applyBasicType(new CryptoStringType());

    }
}

