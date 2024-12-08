package org.example.demo1.dao;

import org.example.demo1.entities.Destination;

public class DestinationRepository extends GenericDAO<Destination> {
    public DestinationRepository() {
        super(Destination.class);
    }
}
