package org.example.demo1.dao;

import org.example.demo1.entities.CompannieAerienne;

public class CompannieAerienneRepository extends GenericDAO<CompannieAerienne> {
    public CompannieAerienneRepository() {
        super(CompannieAerienne.class);
    }
}
