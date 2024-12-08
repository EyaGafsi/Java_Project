package org.example.demo1.services;

import org.example.demo1.entities.Destination;

import java.util.List;

public interface DestinationService {
    void deleteDestination(Destination destination);
    void updateDestination(Destination destination);
    List<Destination> getDestinations();
    void addDestination(Destination destination);
}
