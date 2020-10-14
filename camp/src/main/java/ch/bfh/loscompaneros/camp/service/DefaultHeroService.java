package ch.bfh.loscompaneros.camp.service;

import ch.bfh.loscompaneros.camp.model.Hero;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultHeroService implements HeroService {

    public Hero createHero(String name) {
        Hero hero = new Hero();
        hero.setName(name);

        System.out.println("The Hero " + name + " has been instantiated.");

        int atk = new Random().nextInt(100);
        hero.setAtk(atk);

        System.out.println(name + " has ATK value of " + hero.getAtk());

        int def = new Random().nextInt(100);
        hero.setDef(def);
        System.out.println(name + " has DEF value of " + hero.getDef());

        hero.setHp(100);
        System.out.println(name + " has HP value of " + hero.getHp());

        System.out.println("Hero " + name + " has been created");
        return hero;
    }
}
