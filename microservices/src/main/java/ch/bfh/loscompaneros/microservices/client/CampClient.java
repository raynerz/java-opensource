package ch.bfh.loscompaneros.microservices.client;

import ch.bfh.loscompaneros.microservices.model.Party;
import org.springframework.hateoas.EntityModel;

public interface CampClient {
    EntityModel<Party> createParty(String name);
}
