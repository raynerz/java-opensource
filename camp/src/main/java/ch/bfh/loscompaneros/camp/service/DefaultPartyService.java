package ch.bfh.loscompaneros.camp.service;

import ch.bfh.loscompaneros.camp.model.Hero;
import ch.bfh.loscompaneros.camp.model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPartyService implements PartyService
{
    private static final int NBR_HEROES = 4;

    @Autowired
    private HeroService heroService;

    @Override
    public Party createParty(String name)
    {
        Party party = new Party();
        party.setName(name);

        List<Hero> members = new ArrayList<Hero>();
        for (int i = 0; i < NBR_HEROES; i++)
            members.add(heroService.createHero("Hero #"+(1+i)));

        party.setMembers(members);

        System.out.println("The party has been successfully created.");

        return party;
    }
}
