package com.gold.jpa.method.cstombasictype.register;

import com.gold.jpa.method.cstombasictype.CryptoStringType;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;
import org.slf4j.LoggerFactory;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-07-11
 */
public class CustomDataTypesRegistration implements SessionFactoryBuilderFactory {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomDataTypesRegistration.class);

    @Override
    public SessionFactoryBuilder getSessionFactoryBuilder(final MetadataImplementor metadata, final SessionFactoryBuilderImplementor defaultBuilder) {
        logger.info("Registering custom Hibernate data types");
        metadata.getTypeResolver().registerTypeOverride(CryptoStringType.INSTANCE);
        metadata.getTypeConfiguration().getBasicTypeRegistry();
        return defaultBuilder;
    }
}
