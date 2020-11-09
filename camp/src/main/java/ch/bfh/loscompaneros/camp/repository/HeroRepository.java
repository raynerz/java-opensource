package ch.bfh.loscompaneros.camp.repository;

import ch.bfh.loscompaneros.camp.model.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface HeroRepository extends MongoRepository<Hero, String> {

    Long countByAtkGreaterThan(Integer atk);

}