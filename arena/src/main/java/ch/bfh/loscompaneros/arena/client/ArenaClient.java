package ch.bfh.loscompaneros.arena.client;

import ch.bfh.loscompaneros.arena.model.Party;

import java.util.List;

public interface ArenaClient {
    String battle(List<Party> challenger);
}
