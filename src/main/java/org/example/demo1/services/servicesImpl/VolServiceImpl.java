package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.VolRepository;
import org.example.demo1.entities.Vol;
import org.example.demo1.services.VolService;

import java.util.List;

public class VolServiceImpl implements VolService {
    VolRepository volRepository = new VolRepository();

    public void deleteVol(Vol vol) {
        Vol optVol=volRepository.getById(vol.getId_vol());
        if(optVol!=null) {
            volRepository.delete(optVol);
        }
    }
    public void updateVol(Vol vol) {
        Vol optVol=volRepository.getById(vol.getId_vol());
        if(optVol!=null) {
            volRepository.update(optVol);
        }
    }
    public void addVol(Vol vol) {

        volRepository.add(vol);

    }
    public List<Vol> getVols() {
        return volRepository.getAll();
    }
}
