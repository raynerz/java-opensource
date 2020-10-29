package ch.bfh.loscompaneros.camp.config;

import ch.bfh.loscompaneros.camp.service.DefaultHeroService;
import ch.bfh.loscompaneros.camp.service.DefaultPartyService;
import ch.bfh.loscompaneros.camp.service.HeroService;
import ch.bfh.loscompaneros.camp.service.PartyService;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@EnableMongoRepositories(basePackages= "ch.bfh.loscompaneros.camp.repository")
public class ServiceConfiguration
{
    public ServiceConfiguration()
    {
        //Remove temporary mongodb files incase of crash
        if (System.getenv("OS") != null && System.getenv("OS").contains("Windows"))
        {
            File f = new File(System.getenv("temp") + File.separator);

            File[] files = f.listFiles();

            if(files != null)
            {
                for (File file : files)
                {
                    if (file.isDirectory())
                        continue;

                    if (!file.getAbsolutePath().endsWith("mongod.exe"))
                        continue;

                    try
                    {
                        Files.deleteIfExists(file.toPath());
                    }
                    catch (IOException e)
                    {
                        System.err.println(e);
                    }
                }
            }
        }
    }
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

    @Bean
    @DependsOn("mongo")
    public MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "heroes");
    }

    @Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version("3.4.17")
                .bindIp("localhost")
                .port(27017)
                .build();
    }
}
