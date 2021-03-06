package hello.domainclasses;

/**
 * Created by stefv on 07-Nov-17.
 */
public class Pokemon {
    private int id;
    private PokemonSpecies species;
    private Attack[] attacks;

    public Pokemon(int id, PokemonSpecies species, Attack[] attacks) {
        this.id = id;
        this.species = species;
        this.attacks = attacks;
    }

    public int getRating() {
        int rating = 0;
        for (int i = 0; i < attacks.length; i++) {
            if (attacks[i] != null) {
                rating += attacks[i].getRating();
            }
        }
        rating += species.getRating();
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
}
