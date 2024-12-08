package org.example.demo1.dao;

import org.example.demo1.entities.Client;

public class ClientRepository extends GenericDAO<Client> {
    public ClientRepository() {
        super(Client.class);
    }
}
