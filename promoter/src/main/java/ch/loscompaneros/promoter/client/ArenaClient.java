package ch.loscompaneros.promoter.client;


import ch.loscompaneros.promoter.model.Party;

import java.util.List;

public interface ArenaClient {
    String battle(List<Party> challenger);
}
