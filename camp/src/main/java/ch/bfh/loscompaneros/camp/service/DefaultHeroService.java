package ch.bfh.loscompaneros.camp.service;

import ch.bfh.loscompaneros.camp.model.Hero;
import ch.bfh.loscompaneros.camp.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Random;


public class DefaultHeroService implements HeroService {

    @Autowired
    private HeroRepository HeroRepository;

    public Hero createHero(String name)
    {
        Hero hero = new Hero();
        hero.setName(name);

        System.out.println("The Hero " + hero.getName() + " has been instantiated.");

        int atk = new Random().nextInt(100);
        hero.setAtk(atk);

        System.out.println(name + " has ATK value of " + hero.getAtk());

        int def = new Random().nextInt(100);
        hero.setDef(def);
        System.out.println(name + " has DEF value of " + hero.getDef());

        hero.setHp(100);
        System.out.println(name + " has HP value of " + hero.getHp());

        System.out.println("Hero " + name + " has been created");

        String id = HeroRepository.save(hero).getID();

        Optional<Hero> tmpHero = HeroRepository.findById(id);

        return tmpHero.orElse(null);
    }
}
