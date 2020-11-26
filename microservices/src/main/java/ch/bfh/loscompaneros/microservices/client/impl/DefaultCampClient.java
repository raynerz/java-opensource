package ch.bfh.loscompaneros.microservices.client.impl;

import ch.bfh.loscompaneros.microservices.client.CampClient;
import ch.bfh.loscompaneros.microservices.model.Party;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultCampClient implements CampClient {

    @Override
    public EntityModel<Party> createParty(String name) {
        ResponseEntity<EntityModel<Party>> response=  new RestTemplate().exchange(
                "http://localhost:2222/createParty?name={name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EntityModel<Party>>() {}
                , name);
        return response.getBody();
    }
}
