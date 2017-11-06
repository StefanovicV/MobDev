package hello.domainclasses;

/**
 * Created by 11500809 on 3/11/2017.
 */
public class Shop {
    private Item[] items;
    private Attack[] attacks;
    private PokemonSpecies[] pokemonSpecies;

    public Shop(Item[] items, Attack[] attacks, PokemonSpecies[] pokemonSpecies){
        this.items = items;
        this.attacks = attacks;
        this.pokemonSpecies = pokemonSpecies;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    public PokemonSpecies[] getPokemonSpecies() {
        return pokemonSpecies;
    }

    public void setPokemonSpecies(PokemonSpecies[] pokemonSpecies) {
        this.pokemonSpecies = pokemonSpecies;
    }
}
