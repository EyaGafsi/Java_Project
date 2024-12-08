package org.example.demo1.dao;

import org.example.demo1.entities.Activite;

public class ActiviteRepository extends GenericDAO<Activite> {
    public ActiviteRepository() {
        super(Activite.class);
    }
}
