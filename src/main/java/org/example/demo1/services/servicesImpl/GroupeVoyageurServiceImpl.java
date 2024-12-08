package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.GroupeVoyageurRepository;
import org.example.demo1.entities.GroupeVoyageur;
import org.example.demo1.services.GroupeVoyageurService;

import java.util.List;

public class GroupeVoyageurServiceImpl implements GroupeVoyageurService {
    GroupeVoyageurRepository groupeVoyageurRepository = new GroupeVoyageurRepository();
    public void deleteGroupeVoyageur(GroupeVoyageur groupeVoyageur) {
        GroupeVoyageur optGroupe=groupeVoyageurRepository.getById(groupeVoyageur.getId_groupe());
        if(optGroupe!=null) {
            groupeVoyageurRepository.delete(optGroupe);
        }
    }
    public void updateGroupeVoyageur(GroupeVoyageur groupeVoyageur) {
        GroupeVoyageur optGroupe=groupeVoyageurRepository.getById(groupeVoyageur.getId_groupe());
        if(optGroupe!=null) {
            groupeVoyageurRepository.update(optGroupe);
        }
    }
    public void addGroupeVoyageur(GroupeVoyageur groupeVoyageur) {

        groupeVoyageurRepository.add(groupeVoyageur);

    }
    public List<GroupeVoyageur> getGroupeVoyageurs() {
        return groupeVoyageurRepository.getAll();
    }
}
