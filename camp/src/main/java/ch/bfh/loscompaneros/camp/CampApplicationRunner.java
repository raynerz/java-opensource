package ch.bfh.loscompaneros.camp;

import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CampApplicationRunner implements ApplicationRunner {

    @Autowired
    private PartyService partyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        partyService.createParty("Party1");
    }
}
