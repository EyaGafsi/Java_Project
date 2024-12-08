package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.ActiviteRepository;
import org.example.demo1.entities.Activite;
import org.example.demo1.services.ActiviteService;

import java.util.List;

public class ActiviteServiceImpl implements ActiviteService {
    ActiviteRepository activiteRepository = new ActiviteRepository();
    public void deleteActivite(Activite activite) {
        Activite optAct=activiteRepository.getById(activite.getId_activite());
        if(optAct!=null) {
            activiteRepository.delete(optAct);
        }
    }
    public void updateActivite(Activite activite) {
        Activite optAct=activiteRepository.getById(activite.getId_activite());
        if(optAct!=null) {
            activiteRepository.update(optAct);
        }
    }
    public void addActivite(Activite activite) {

        activiteRepository.add(activite);

    }
    public List<Activite> getActivitees() {
        return activiteRepository.getAll();
    }
}
