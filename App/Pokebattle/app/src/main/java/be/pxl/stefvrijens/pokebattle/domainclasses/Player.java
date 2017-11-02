package be.pxl.stefvrijens.pokebattle.domainclasses;

/**
 * Created by stefv on 02-Nov-17.
 */

public class Player {
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
