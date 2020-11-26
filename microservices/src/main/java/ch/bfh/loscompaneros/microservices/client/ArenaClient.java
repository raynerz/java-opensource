package ch.bfh.loscompaneros.microservices.client;

import ch.bfh.loscompaneros.microservices.model.Party;

import java.util.List;

public interface ArenaClient {
    String battle(List<Party> challenger);
}
