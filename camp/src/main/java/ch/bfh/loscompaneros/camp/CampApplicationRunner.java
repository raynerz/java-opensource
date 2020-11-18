package ch.bfh.loscompaneros.camp;

import ch.bfh.loscompaneros.camp.repository.HeroRepository;
import ch.bfh.loscompaneros.camp.service.HeroService;
import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CampApplicationRunner implements ApplicationRunner {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (heroRepository.count() == 0) {
            heroService.createHero("Jean-Pierre");
            heroService.createHero("Jean-Eude");
            heroService.createHero("Jean-Richard");
            heroService.createHero("Jean-Jacques");
        }
    }
}
