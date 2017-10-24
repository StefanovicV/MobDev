package hello.data;

import hello.domainclasses.PokemonSpecies;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stefv on 24-Oct-17.
 */
public class SpeciesList {
    private static PokemonSpecies venusaur = new PokemonSpecies(3, "Venusaur", "Grass", "Poison", 0, null, 0, 100, 60, 100, 40);
    private static PokemonSpecies ivysaur = new PokemonSpecies(2, "Ivysaur", "Grass", "Poison", 0, venusaur, 200, 70, 50, 80, 40);
    private static PokemonSpecies bulbasaur = new PokemonSpecies(1, "Bulbasaur", "Grass", "Poison", 100, ivysaur, 150, 50, 40, 60, 40);


    private static List<PokemonSpecies> allSpecies = Arrays.asList(venusaur, ivysaur, bulbasaur);
    private static List<PokemonSpecies> buyableSpecies = Arrays.asList(bulbasaur);

    public static PokemonSpecies[] getAllSpecies() {
        return (PokemonSpecies[]) allSpecies.toArray();
    }

    public static PokemonSpecies[] getBuyableSpecies() {
        return (PokemonSpecies[]) buyableSpecies.toArray();
    }

    public static PokemonSpecies getSpeciesByName(String name) {
        return allSpecies.stream().filter(pokemonSpecies -> pokemonSpecies.getName().toLowerCase().equals(name.toLowerCase())).findAny().get();
    }


}
