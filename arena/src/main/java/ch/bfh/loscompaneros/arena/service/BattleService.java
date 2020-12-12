package ch.bfh.loscompaneros.arena.service;


import ch.bfh.loscompaneros.arena.model.Party;

public interface BattleService {
    String battle(Party challengee, Party challenger);
}
