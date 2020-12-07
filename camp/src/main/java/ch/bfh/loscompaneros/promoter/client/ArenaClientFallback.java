package ch.bfh.loscompaneros.prompter.client;

import ch.bfh.loscompaneros.prompter.model.Party;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArenaClientFallback implements ArenaClient {

    @Override
    public String battle(List<Party> challangers) {
        return "The battle has to be postponed because the arena is occupied at the moment";
    }

}
