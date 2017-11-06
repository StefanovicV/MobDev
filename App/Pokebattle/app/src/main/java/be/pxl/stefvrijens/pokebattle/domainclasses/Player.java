package be.pxl.stefvrijens.pokebattle.domainclasses;

import java.io.Serializable;

/**
 * Created by stefv on 02-Nov-17.
 */

public class Player implements Serializable {
    private Pokemon[] team;
    private int ownedCoins;
    private int ownedPotions;
    private int ownedSuperPotions;
    private Attack[] ownedAttacks;
    private Pokemon[] ownedPokemon;

    public Player(Pokemon[] team, int ownedCoins, int ownedPotions, int ownedSuperPotions, Attack[] ownedAttacks, Pokemon[] ownedPokemon) {
        this.team = team;
        this.ownedCoins = ownedCoins;
        this.ownedPotions = ownedPotions;
        this.ownedSuperPotions = ownedSuperPotions;
        this.ownedAttacks = ownedAttacks;
        this.ownedPokemon = ownedPokemon;
    }

    public static Player generateInitialPlayerData() {
        Pokemon[] initialTeam = new Pokemon[] {Pokemon.generateTestPokemon()};
        Attack[] initialAttacks = new Attack[] {Attack.generateTestAttack(1)};
        return new Player(initialTeam, 50, 0, 0, initialAttacks, initialTeam);
    }

    public void updatePokemon(Pokemon pokemon) {
        for (int i = 0; i < ownedPokemon.length; i++) {
            if (ownedPokemon[i].getId() == pokemon.getId()) {
                ownedPokemon[i] = pokemon;
                break;
            }
        }
    }

    public int getTeamRating() {
        int rating = 0;
        for (int i = 0; i < team.length; i++) {
            rating += team[i].getPokemonRating();
        }
        return rating;
    }

    public Pokemon[] getOwnedPokemon() {
        return ownedPokemon;
    }

    public void setOwnedPokemon(Pokemon[] ownedPokemon) {
        this.ownedPokemon = ownedPokemon;
    }

    public Pokemon[] getTeam() {
        return team;
    }

    public void setTeam(Pokemon[] team) {
        this.team = team;
    }

    public int getOwnedCoins() {
        return ownedCoins;
    }

    public void setOwnedCoins(int ownedCoins) {
        this.ownedCoins = ownedCoins;
    }

    public int getOwnedPotions() {
        return ownedPotions;
    }

    public void setOwnedPotions(int ownedPotions) {
        this.ownedPotions = ownedPotions;
    }

    public int getOwnedSuperPotions() {
        return ownedSuperPotions;
    }

    public void setOwnedSuperPotions(int ownedSuperPotions) {
        this.ownedSuperPotions = ownedSuperPotions;
    }

    public Attack[] getOwnedAttacks() {
        return ownedAttacks;
    }

    public void setOwnedAttacks(Attack[] ownedAttacks) {
        this.ownedAttacks = ownedAttacks;
    }
}
