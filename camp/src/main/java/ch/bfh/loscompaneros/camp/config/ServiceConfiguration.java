package ch.bfh.loscompaneros.camp.config;

import ch.bfh.loscompaneros.camp.service.DefaultHeroService;
import ch.bfh.loscompaneros.camp.service.DefaultPartyService;
import ch.bfh.loscompaneros.camp.service.HeroService;
import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration
{
    @Bean
    public HeroService heroService()
    {
        return new DefaultHeroService();
    }

    @Bean
    public PartyService partyService()
    {
        return new DefaultPartyService();
    }
}
