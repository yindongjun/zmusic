package com.example.zmusic.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class KsuidIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(
            SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        return KsuidUtils.generateKsuid();
    }
}
