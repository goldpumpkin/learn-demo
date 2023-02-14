package com.gold.jpa.method.cstombasictype.register;

import com.gold.jpa.method.cstombasictype.CryptoStringType;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * @author goldhuang
 * @Description:
 * @date 2021-07-11
 */
public class CustomIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().build();
        MetadataSources sources = new MetadataSources(standardRegistry);
        org.hibernate.boot.MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        metadataBuilder.applyBasicType(CryptoStringType.INSTANCE);


    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        return;
    }
}
