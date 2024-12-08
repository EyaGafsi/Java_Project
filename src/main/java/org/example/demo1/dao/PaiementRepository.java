package org.example.demo1.dao;

import org.example.demo1.entities.Paiement;

public class PaiementRepository extends GenericDAO<Paiement> {
    public PaiementRepository() {
        super(Paiement.class);
    }
}
