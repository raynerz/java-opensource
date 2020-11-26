package ch.bfh.loscompaneros.microservices.client.impl;

import ch.bfh.loscompaneros.microservices.client.ArenaClient;
import ch.bfh.loscompaneros.microservices.model.Party;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DefaultArenaClient implements ArenaClient {
    @Override
    public String battle(List<Party> challenger) {
        ResponseEntity<String> response =  new RestTemplate().exchange(
                "http://localhost:3333/battle",
                HttpMethod.POST,
                new HttpEntity<>(challenger),
                String.class);
        return response.getBody();
    }
}
