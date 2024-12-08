package org.example.demo1.dao;

import org.example.demo1.entities.Voyage;

public class VoyageRepository extends GenericDAO<Voyage> {
    public VoyageRepository() {
        super(Voyage.class);
    }
}
