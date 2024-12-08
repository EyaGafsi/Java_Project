package org.example.demo1.services;

import org.example.demo1.entities.Voyage;

import java.util.List;

public interface VoyageService {
    void deleteVoyage(Voyage voyage);
    void updateVoyage(Voyage voyage);
    List<Voyage> getVoyages();
    void addVoyage(Voyage voyage);
}
