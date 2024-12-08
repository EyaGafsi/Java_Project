package org.example.demo1.dao;

import org.example.demo1.entities.Avis;

public class AvisRepository extends GenericDAO<Avis> {
    public AvisRepository() {
        super(Avis.class);
    }
}
