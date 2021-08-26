package com.gold.jpa.method.cstombasictype;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

public class CryptoStringType extends AbstractSingleColumnStandardBasicType<CustomCrypto> implements DiscriminatorType<CustomCrypto> {

    public static final CryptoStringType INSTANCE = new CryptoStringType();

    public CryptoStringType() {
        super(VarcharTypeDescriptor.INSTANCE, CryptoStringJavaDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return CustomCrypto.class.getTypeName();
    }

    @Override
    public CustomCrypto stringToObject(String xml) throws Exception {
        return fromString(xml);
    }

    @Override
    public String objectToSQLString(CustomCrypto value, Dialect dialect) throws Exception {
        return '\'' + toString(value) + '\'';
    }

}
