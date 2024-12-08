package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.CompannieAerienneRepository;
import org.example.demo1.entities.CompannieAerienne;
import org.example.demo1.services.CompannieAerienneService;

import java.util.List;

public class CompannieAerienneServiceImpl implements CompannieAerienneService {
    CompannieAerienneRepository compannieAerienneRepository = new CompannieAerienneRepository();

    public void deleteCompannieAerienne(CompannieAerienne compannieAerienne) {
        CompannieAerienne optComp=compannieAerienneRepository.getById(compannieAerienne.getId_CompannieArienne());
        if(optComp!=null) {
            compannieAerienneRepository.delete(optComp);
        }
    }
    public void updateCompannieAerienne(CompannieAerienne compannieAerienne) {
        CompannieAerienne optComp=compannieAerienneRepository.getById(compannieAerienne.getId_CompannieArienne());
        if(optComp!=null) {
            compannieAerienneRepository.update(optComp);
        }
    }
    public void addCompannieAerienne(CompannieAerienne compannieAerienne) {

        compannieAerienneRepository.add(compannieAerienne);

    }
    public List<CompannieAerienne> getCompannieAerienne() {
        return compannieAerienneRepository.getAll();
    }
}
