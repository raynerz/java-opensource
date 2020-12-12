package ch.loscompaneros.promoter.client;


import ch.loscompaneros.promoter.model.Party;
import org.springframework.hateoas.EntityModel;

public interface CampClient {
    EntityModel<Party> createParty(String name);
}
