package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.VoyageRepository;
import org.example.demo1.entities.Voyage;
import org.example.demo1.services.VoyageService;

import java.util.List;

public class VoyageServiceImpl implements VoyageService {
    VoyageRepository voyageRepository = new VoyageRepository();

    public void deleteVoyage(Voyage voyage) {
        Voyage optVoyage=voyageRepository.getById(voyage.getId_voyage());
        if(optVoyage!=null) {
            voyageRepository.delete(optVoyage);
        }
    }
    public void updateVoyage(Voyage voyage) {
        Voyage optVoyage=voyageRepository.getById(voyage.getId_voyage());
        if(optVoyage!=null) {
            voyageRepository.update(optVoyage);
        }
    }
    public void addVoyage(Voyage voyage) {

        voyageRepository.add(voyage);

    }
    public List<Voyage> getVoyages() {
        return voyageRepository.getAll();
    }
}
