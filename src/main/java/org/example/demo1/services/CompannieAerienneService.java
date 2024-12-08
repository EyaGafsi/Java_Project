package org.example.demo1.services;

import org.example.demo1.entities.CompannieAerienne;

import java.util.List;

public interface CompannieAerienneService {
    void deleteCompannieAerienne(CompannieAerienne compannieAerienne);
    void updateCompannieAerienne(CompannieAerienne compannieAerienne);
    List<CompannieAerienne> getCompannieAerienne();
    void addCompannieAerienne(CompannieAerienne compannieAerienne);
}
