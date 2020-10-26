package ch.bfh.loscompaneros.camp.config;

import ch.bfh.loscompaneros.camp.service.DefaultHeroService;
import ch.bfh.loscompaneros.camp.service.DefaultPartyService;
import ch.bfh.loscompaneros.camp.service.HeroService;
import ch.bfh.loscompaneros.camp.service.PartyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "ch.bfh.loscompaneros.camp.repository")
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

    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.H2);
        bean.setGenerateDdl(true);
        return bean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter)
    {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("ch.bfh.loscompaneros.camp.model");
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf)
    {
        return new JpaTransactionManager(emf);
    }
}
