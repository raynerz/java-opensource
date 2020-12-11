package ch.bfh.loscompaneros.camp.service;

import ch.bfh.loscompaneros.camp.model.Hero;
import ch.bfh.loscompaneros.camp.model.Party;
import ch.bfh.loscompaneros.camp.repository.HeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collections;
import java.util.List;


public class DefaultPartyService implements PartyService
{

    @Autowired
    private HeroRepository heroRepository;

    @Override
    public Party createParty(String name)
    {

        Party party = new Party();
        party.setName(name);

        List<Hero> members = heroRepository.findAll();
        Collections.shuffle(members);

        List<Hero> defHeroes = members.subList(0, 4);


        party.setMembers(members);

        System.out.println("The party has been successfully created.");

        return party;
    }
}
