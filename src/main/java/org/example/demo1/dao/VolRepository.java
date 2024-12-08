package org.example.demo1.dao;

import org.example.demo1.entities.Vol;

public class VolRepository extends GenericDAO<Vol> {
    public VolRepository() {
        super(Vol.class);
    }
}
