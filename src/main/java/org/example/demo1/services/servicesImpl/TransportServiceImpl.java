package org.example.demo1.services.servicesImpl;


import org.example.demo1.dao.MoyenDeTransportRepository;
import org.example.demo1.entities.MoyenDeTransport;
import org.example.demo1.services.TransportService;

import java.util.List;

public class TransportServiceImpl implements TransportService {
    MoyenDeTransportRepository moyenDeTransportRepository = new MoyenDeTransportRepository();
    public void deleteTransport(MoyenDeTransport transport) {
        MoyenDeTransport opttransport=moyenDeTransportRepository.getById(transport.getId_moyen_transport());
        if(opttransport!=null) {
            moyenDeTransportRepository.delete(opttransport);
        }
    }
    public void updateTransport(MoyenDeTransport transport) {
        MoyenDeTransport opttransport=moyenDeTransportRepository.getById(transport.getId_moyen_transport());
        if(opttransport!=null) {
            moyenDeTransportRepository.update(opttransport);
        }
    }
    public void addTransport(MoyenDeTransport transport) {

        moyenDeTransportRepository.add(transport);

    }
    public List<MoyenDeTransport> getTransports() {
        return moyenDeTransportRepository.getAll();
    }
}
