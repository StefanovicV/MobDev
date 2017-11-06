package be.pxl.stefvrijens.pokebattle.domainclasses;

import java.io.Serializable;

/**
 * Created by stefv on 25-Oct-17.
 */

public class PokemonSpecies implements Serializable {
    static final long serialVersionUID = 42L;
    private int id;
    private String name;
    private Type type1;
    private Type type2;
    private int cost;
    private PokemonSpecies nextEvolution;
    private int evolutionCost;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private String imageUrl;

    public PokemonSpecies(int id, String name, Type type1, Type type2, int cost, PokemonSpecies nextEvolution, int evolutionCost, int hp, int attack, int defense, int speed, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.cost = cost;
        this.nextEvolution = nextEvolution;
        this.evolutionCost = evolutionCost;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.imageUrl = imageUrl;
    }

    public static PokemonSpecies generateTestPokemonSpecies() {
        return new PokemonSpecies(999, "Missingno", Type.NORMAL, null, 10, null, 0, 100, 100, 100, 100, "https://static.giantbomb.com/uploads/square_small/9/95666/2775264-sprite_missingno.gif");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PokemonSpecies getNextEvolution() {
        return nextEvolution;
    }

    public void setNextEvolution(PokemonSpecies nextEvolution) {
        this.nextEvolution = nextEvolution;
    }

    public int getEvolutionCost() {
        return evolutionCost;
    }

    public void setEvolutionCost(int evolutionCost) {
        this.evolutionCost = evolutionCost;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
