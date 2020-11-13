package ch.bfh.loscompaneros.camp.controller;


import ch.bfh.loscompaneros.camp.model.Hero;
import ch.bfh.loscompaneros.camp.model.Party;
import ch.bfh.loscompaneros.camp.repository.HeroRepository;
import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PartyController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private RepositoryEntityLinks entityLinks;

    @GetMapping(value = "/createParty")
    public Party createParty(@RequestParam String name) {
        Party party = partyService.createParty(name);
        party.add(linkTo(methodOn(PartyController.class).createParty(name)).withSelfRel());
        for (int i = 0; i < party.getMembers().size(); i++) {
            party.add(entityLinks.linkToItemResource(Hero.class, party.getMembers().get(i).getID()).withRel("hero"+i));
        }
        return party;
    }

}