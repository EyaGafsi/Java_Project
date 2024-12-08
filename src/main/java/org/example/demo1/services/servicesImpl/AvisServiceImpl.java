package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.AvisRepository;
import org.example.demo1.entities.Avis;
import org.example.demo1.services.AvisService;

import java.util.List;

public class AvisServiceImpl implements AvisService {
    AvisRepository avisRepository = new AvisRepository();

    public void deleteAvis(Avis avis) {
        Avis optAvis=avisRepository.getById(avis.getId_Avis());
        if(optAvis!=null) {
            avisRepository.delete(optAvis);
        }
    }
    public void updateAvis(Avis avis) {
        Avis optAvis=avisRepository.getById(avis.getId_Avis());
        if(optAvis!=null) {
            avisRepository.update(optAvis);
        }
    }
    public void addAvis(Avis avis) {

        avisRepository.add(avis);

    }
    public List<Avis> getAvis() {
        return avisRepository.getAll();
    }
}
