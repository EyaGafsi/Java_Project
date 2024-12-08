package org.example.demo1.services;

import org.example.demo1.entities.Vol;

import java.util.List;

public interface VolService {
    void deleteVol(Vol vol);
    void updateVol(Vol vol);
    List<Vol> getVols();
    void addVol(Vol vol);
}
