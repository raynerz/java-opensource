package ch.bfh.swos.prompter.client;

import ch.bfh.swos.prompter.model.Party;

import java.util.List;


public interface ArenaClient {

    String battle(List<Party> challangers);

}

