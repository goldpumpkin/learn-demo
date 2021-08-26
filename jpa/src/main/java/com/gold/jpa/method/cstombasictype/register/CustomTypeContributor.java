package com.gold.jpa.method.cstombasictype.register;

import com.gold.jpa.method.cstombasictype.CryptoStringJavaDescriptor;
import com.gold.jpa.method.cstombasictype.CryptoStringType;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-07-11
 */
public class CustomTypeContributor implements TypeContributor {
    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(CryptoStringType.INSTANCE);
        typeContributions.contributeJavaTypeDescriptor(CryptoStringJavaDescriptor.INSTANCE);
        typeContributions.contributeSqlTypeDescriptor(VarcharTypeDescriptor.INSTANCE);
    }
}