package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.ClientRepository;
import org.example.demo1.entities.Client;
import org.example.demo1.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    ClientRepository clientDAO = new ClientRepository();
    public void deleteClient(Client client) {
        Client optClient=clientDAO.getById(client.getId_Client());
        if(optClient!=null) {
            clientDAO.delete(optClient);
        }
    }
    public void updateClient(Client client) {
        Client optClient=clientDAO.getById(client.getId_Client());
        if(optClient!=null) {
            clientDAO.update(optClient);
        }
    }
    public void addClient(Client client) {

            clientDAO.add(client);

    }
    public List<Client> getClients() {
            return clientDAO.getAll();
    }
}
