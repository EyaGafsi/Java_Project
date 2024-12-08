package org.example.demo1.services;

import org.example.demo1.entities.MoyenDeTransport;

import java.util.List;

public interface TransportService {
    void deleteTransport(MoyenDeTransport transport);
    void updateTransport(MoyenDeTransport transport);
    List<MoyenDeTransport> getTransports();
    void addTransport(MoyenDeTransport transport);
}
