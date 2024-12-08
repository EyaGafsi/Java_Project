package org.example.demo1.dao;

import org.example.demo1.entities.Agent;

public class AgentRepository extends GenericDAO<Agent> {
    public AgentRepository() {
        super(Agent.class);
    }
}
