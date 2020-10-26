package ch.bfh.loscompaneros.camp.repository;

import ch.bfh.loscompaneros.camp.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends CrudRepository<Hero, String> {

    Long countByAtkGreaterThan(Integer atk);

}