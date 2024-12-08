package org.example.demo1.services;

import org.example.demo1.entities.GroupeVoyageur;

import java.util.List;

public interface GroupeVoyageurService {
    void deleteGroupeVoyageur(GroupeVoyageur groupeVoyageur);
    void updateGroupeVoyageur(GroupeVoyageur groupeVoyageur);
    List<GroupeVoyageur> getGroupeVoyageurs();
    void addGroupeVoyageur(GroupeVoyageur groupeVoyageur);
}
