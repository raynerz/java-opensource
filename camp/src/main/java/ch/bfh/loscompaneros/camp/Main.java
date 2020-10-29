package ch.bfh.loscompaneros.camp;

import ch.bfh.loscompaneros.camp.config.ServiceConfiguration;
import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {

        /*/ Application context using annotations for DI - more magic, less control
        AnnotationConfigApplicationContext annotatedContext = new AnnotationConfigApplicationContext();
        annotatedContext.scan("ch.bfh.loscompaneros.camp");
        annotatedContext.refresh();

        PartyService partyServiceAnnotated = annotatedContext.getBean(PartyService.class);
        partyServiceAnnotated.createParty("Party_1");

        annotatedContext.close();*/

        // Application context using java configuration for DI - less magic, more control, @Service annotations are obsolete.
        AnnotationConfigApplicationContext configuredContext = new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        PartyService partyServiceConfigured = configuredContext.getBean(PartyService.class);
        partyServiceConfigured.createParty("Party_2");

        configuredContext.close();
    }
}
