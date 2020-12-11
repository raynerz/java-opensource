package ch.bfh.swos.prompter.client;


import ch.bfh.swos.prompter.model.Party;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArenaFallbackClient implements ArenaClient {

    @Override
    public String battle(List<Party> challangers) {
        return "The battle can't be started at the moment, please check the health of the arena service";
    }
}
