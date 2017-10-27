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

    private static PokemonSpecies charmander = new PokemonSpecies(4, "Charmander", "Fire", "", 100, charmeleon, 150, 50, 40, 60, 40);
    private static PokemonSpecies charmeleon = new PokemonSpecies(5, "Charmeleon", "Fire", "", 0, charizard, 200, 70, 50, 80, 40);
    private static PokemonSpecies charizard = new PokemonSpecies(6, "Charizard", "Fire", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies squirtle = new PokemonSpecies(7, "Squirtle", "Water", "", 0, wartortle, 150, 50, 40, 60, 40);
    private static PokemonSpecies wartortle = new PokemonSpecies(8, "Wartortle", "Water", "", 0, blastoise, 200, 70, 50, 80, 40);
    private static PokemonSpecies blastoise = new PokemonSpecies(9, "Blastoise", "Water", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies caterpie = new PokemonSpecies(10, "Caterpie", "Bug", "", 0, metapod, 150, 50, 40, 60, 40);
    private static PokemonSpecies metapod = new PokemonSpecies(11, "Metapod", "Bug", "", 0, butterfree, 200, 70, 50, 80, 40);
    private static PokemonSpecies butterfree = new PokemonSpecies(12, "Butterfree", "Bug", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies pidgey = new PokemonSpecies(13, "Pidgey", "Normal", "Flying", 0, pidgeotto, 150, 50, 40, 60, 40);
    private static PokemonSpecies pidgeotto = new PokemonSpecies(14, "Pidgeotto", "Normal", "Flying", 0, pidgeot, 200, 70, 50, 80, 40);
    private static PokemonSpecies pidgeot = new PokemonSpecies(15, "Pidgeot", "NOrmal", "Flying", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies poliwag = new PokemonSpecies(16, "Poliwag", "Water", "", 0, poliwhirl, 150, 50, 40, 60, 40);
    private static PokemonSpecies poliwhirl = new PokemonSpecies(17, "Poliwhirl", "Water", "", 0, poliwrath, 200, 70, 50, 80, 40);
    private static PokemonSpecies poliwrath = new PokemonSpecies(18, "Poliwrath", "Water", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies abra = new PokemonSpecies(19, "Abra", "Psychic", "", 0, kadabra, 150, 50, 40, 60, 40);
    private static PokemonSpecies kadabra = new PokemonSpecies(20, "Kadabra", "Psychic", "", 0, alakazam, 200, 70, 50, 80, 40);
    private static PokemonSpecies alakazam = new PokemonSpecies(21, "Alakazam", "Psychic", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies machop = new PokemonSpecies(22, "Machop", "Fighting", "", 0, machoke, 150, 50, 40, 60, 40);
    private static PokemonSpecies machoke = new PokemonSpecies(23, "Machoke", "Fighting", "", 0, machamp, 200, 70, 50, 80, 40);
    private static PokemonSpecies machamp = new PokemonSpecies(24, "Machamp", "Fighting", "", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies bellsprout = new PokemonSpecies(25, "Bellsprout", "Grass", "Poison", 0, weepinbell, 150, 50, 40, 60, 40);
    private static PokemonSpecies weepinbell = new PokemonSpecies(26, "Weepinbell", "Grass", "Poison", 0, victreebell, 200, 70, 50, 80, 40);
    private static PokemonSpecies victreebell = new PokemonSpecies(27, "Victreebell", "Grass", "Poison", 0, null, 0, 100, 60, 100, 40);

    private static PokemonSpecies geodude = new PokemonSpecies(28, "Geodude", "Rock", "Ground", 0, graveler, 150, 50, 40, 60, 40);
    private static PokemonSpecies graveler = new PokemonSpecies(29, "Graveler", "Rock", "Ground", 0, golem, 200, 70, 50, 80, 40);
    private static PokemonSpecies golem = new PokemonSpecies(30, "Golem", "Rock", "Ground", 0, null, 0, 100, 60, 100, 40);


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
