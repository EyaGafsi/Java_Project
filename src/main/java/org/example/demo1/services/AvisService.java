package org.example.demo1.services;

import org.example.demo1.entities.Avis;

import java.util.List;

public interface AvisService {
    void deleteAvis(Avis avis);
    void updateAvis(Avis avis);
    List<Avis> getAvis();
    void addAvis(Avis avis);
}
