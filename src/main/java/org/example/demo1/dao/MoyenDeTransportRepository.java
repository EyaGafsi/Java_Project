package org.example.demo1.dao;

import org.example.demo1.entities.MoyenDeTransport;

public class MoyenDeTransportRepository extends GenericDAO<MoyenDeTransport> {
    public MoyenDeTransportRepository() {
        super(MoyenDeTransport.class);
    }
}
