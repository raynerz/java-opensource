package ch.bfh.loscompaneros.camp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Hero {

    @Id
    private String ID;
    private String name;
    private int atk;
    private int def;
    private double hp;


    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }


}
