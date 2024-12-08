package org.example.demo1.services;

import org.example.demo1.entities.Activite;

import java.util.List;

public interface ActiviteService {
    void deleteActivite(Activite activite);
    void updateActivite(Activite activite);
    List<Activite> getActivitees();
    void addActivite(Activite activite);
}
