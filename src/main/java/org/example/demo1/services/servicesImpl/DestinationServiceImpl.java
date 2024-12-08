package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.DestinationRepository;
import org.example.demo1.entities.Destination;
import org.example.demo1.services.DestinationService;

import java.util.List;

public class DestinationServiceImpl implements DestinationService {
    DestinationRepository destinationRepository = new DestinationRepository();
    public void deleteDestination(Destination destination) {
        Destination optDestination=destinationRepository.getById(destination.getId_destination());
        if(optDestination!=null) {
            destinationRepository.delete(optDestination);
        }
    }
    public void updateDestination(Destination destination) {
        Destination optDestination=destinationRepository.getById(destination.getId_destination());
        if(optDestination!=null) {
            destinationRepository.update(optDestination);
        }
    }
    public void addDestination(Destination destination) {

        destinationRepository.add(destination);

    }
    public List<Destination> getDestinations() {
        return destinationRepository.getAll();
    }
}
