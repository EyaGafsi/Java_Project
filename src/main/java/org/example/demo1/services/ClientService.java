package org.example.demo1.services;

import org.example.demo1.entities.Client;

import java.util.List;

public interface ClientService {
    void deleteClient(Client client);
    void updateClient(Client client);
    List<Client> getClients();
    void addClient(Client client);
}
