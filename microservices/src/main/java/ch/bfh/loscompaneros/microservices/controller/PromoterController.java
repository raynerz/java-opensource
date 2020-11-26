package ch.bfh.loscompaneros.microservices.controller;

import ch.bfh.loscompaneros.microservices.service.impl.DefaultPromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromoterController {

    @Autowired
    private DefaultPromoterService defaultPromoterService;

    @GetMapping(value = "/promoteFight")
    public String promoteFight() {
        String result = defaultPromoterService.promoteFight();
        return "The Promoter is proud to proclaim the following result of today's battle: "+result;
    }
}
