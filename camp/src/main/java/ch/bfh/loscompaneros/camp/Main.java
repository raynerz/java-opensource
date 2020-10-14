package ch.bfh.loscompaneros.camp;

import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {

        // Application context using annotations for DI - more magic, less control
        AnnotationConfigApplicationContext annotatedContext = new AnnotationConfigApplicationContext();
        annotatedContext.scan("ch.bfh.loscompaneros.camp.service");
        annotatedContext.refresh();

        PartyService partyServiceAnnotated = annotatedContext.getBean(PartyService.class);
        partyServiceAnnotated.createParty("Party_1");

        annotatedContext.close();
    }
}
