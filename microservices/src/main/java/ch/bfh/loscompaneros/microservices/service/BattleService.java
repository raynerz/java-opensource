package ch.bfh.loscompaneros.microservices.service;

import ch.bfh.loscompaneros.microservices.model.Party;

public interface BattleService {

    String battle(Party challengee, Party challenger);
}
