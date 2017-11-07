package be.pxl.stefvrijens.pokebattle.domainclasses;

import java.io.Serializable;

/**
 * Created by stefv on 25-Oct-17.
 */

public class Pokemon implements Serializable{
    static final long serialVersionUID = 43L;
    private int id;
    private PokemonSpecies species;
    private Attack[] attacks;
    private int currentHp;

    public Pokemon(int id, PokemonSpecies species, Attack[] attacks) {
        this.id = id;
        this.species = species;
        this.attacks = attacks;
        this.currentHp = species.getHp() * 4;
    }

    public static Pokemon generateTestPokemon() {
        Attack[] attacks = new Attack[4];
        for (int i = 0; i < 4; i++) {
            attacks[i] = Attack.generateTestAttack(i+1);
        }
        return new Pokemon(1, PokemonSpecies.generateTestPokemonSpecies(), attacks);
    }

    public int getPokemonRating() {
        // Will usually be between 240 - 950
        int attackSetRating = 0;
        int rating;
        for (int i = 0; i < attacks.length; i++) {
            attackSetRating += attacks[i].getAttackRating();
        }
        rating = attackSetRating + species.getSpeed() + species.getDefense() + species.getAttack() + species.getHp();
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecies species) {
        this.species = species;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public void calculateDamage(Attack incomingAttack, Pokemon attackingPokemon) {
        double damage = incomingAttack.getPower() / 10;
        if (incomingAttack.getType().equals(attackingPokemon.getSpecies().getType1()) || incomingAttack.getType().equals(attackingPokemon.getSpecies().getType2())) {
            damage = 1.5 * damage;
        }
        if (species.getType1().hasResistanceTo(incomingAttack.getType())) {
            damage = 0.5 * damage;
    }
        if (species.getType2().hasResistanceTo(incomingAttack.getType())) {
        damage = 0.5 * damage;
    }
        if (species.getType1().hasWeaknessTo(incomingAttack.getType())) {
            damage = 2 * damage;
        }
        if (species.getType2().hasWeaknessTo(incomingAttack.getType())) {
            damage = 2 * damage;
        }
        int powerDifference = attackingPokemon.getSpecies().getAttack() - species.getDefense();
        double multiplier = 1 + powerDifference / 100;
        damage *= multiplier;
        currentHp = (int)(currentHp - damage);
    }
}
