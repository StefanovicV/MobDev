package hello.data;

import hello.domainclasses.PokemonSpecies;
import hello.domainclasses.Type;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stefv on 24-Oct-17.
 */
public class SpeciesList {
    // Cost = 50 (v. cheap) - 7500 (legendary)
    // Stats: 10 = v. low, 130 = v. high

    private static PokemonSpecies venusaur = new PokemonSpecies(3, "Venusaur", Type.GRASS, Type.POISON, 0, null, 0, 130, 60, 110, 50, "https://www.serebii.net/sunmoon/pokemon/003.png");
    private static PokemonSpecies ivysaur = new PokemonSpecies(2, "Ivysaur", Type.GRASS, Type.POISON, 0, venusaur, 1000, 105, 50, 80, 40, "https://www.serebii.net/sunmoon/pokemon/002.png");
    private static PokemonSpecies bulbasaur = new PokemonSpecies(1, "Bulbasaur", Type.GRASS, Type.POISON, 300, ivysaur, 600, 60, 40, 60, 40, "https://www.serebii.net/sunmoon/pokemon/001.png");

    private static PokemonSpecies charizard = new PokemonSpecies(6, "Charizard", Type.FIRE, Type.FLYING, 0, null, 0, 90, 110, 60, 90, "https://www.serebii.net/sunmoon/pokemon/006.png");
    private static PokemonSpecies charmeleon = new PokemonSpecies(5, "Charmeleon", Type.FIRE, null, 0, charizard, 1000, 60, 85, 50, 80, "https://www.serebii.net/sunmoon/pokemon/005.png");
    private static PokemonSpecies charmander = new PokemonSpecies(4, "Charmander", Type.FIRE, null, 300, charmeleon, 600, 50, 60, 40, 50, "https://www.serebii.net/sunmoon/pokemon/004.png");

    private static PokemonSpecies blastoise = new PokemonSpecies(9, "Blastoise", Type.WATER, null, 0, null, 0, 110, 85, 85, 70, "https://www.serebii.net/sunmoon/pokemon/009.png");
    private static PokemonSpecies wartortle = new PokemonSpecies(8, "Wartortle", Type.WATER, null, 0, blastoise, 1000, 80, 65, 65, 65, "https://www.serebii.net/sunmoon/pokemon/008.png");
    private static PokemonSpecies squirtle = new PokemonSpecies(7, "Squirtle", Type.WATER, null, 300, wartortle, 600, 55, 50, 50, 45, "https://www.serebii.net/sunmoon/pokemon/007.png");

    private static PokemonSpecies butterfree = new PokemonSpecies(12, "Butterfree", Type.BUG, Type.FLYING, 0, null, 0, 60, 30, 90, 70, "https://www.serebii.net/sunmoon/pokemon/012.png");
    private static PokemonSpecies metapod = new PokemonSpecies(11, "Metapod", Type.BUG, null, 0, butterfree, 200, 60, 15, 95, 30, "https://www.serebii.net/sunmoon/pokemon/011.png");
    private static PokemonSpecies caterpie = new PokemonSpecies(10, "Caterpie", Type.BUG, null, 50, metapod, 75, 30, 15, 25, 30, "https://www.serebii.net/sunmoon/pokemon/010.png");

    private static PokemonSpecies beedrill = new PokemonSpecies(15, "Beedrill", Type.BUG, Type.POISON, 0, null, 0, 60, 90, 30, 70);
    private static PokemonSpecies kakuna = new PokemonSpecies(14, "Kakuna", Type.BUG, Type.POISON, 0, beedrill, 200, 60, 15, 95, 30);
    private static PokemonSpecies weedle = new PokemonSpecies(13, "Weedle", Type.BUG, Type.POISON, 50, kakuna, 75, 30, 25, 15, 30);

    private static PokemonSpecies pidgeot = new PokemonSpecies(18, "Pidgeot", Type.NORMAL, Type.FLYING, 0, null, 0, 70, 80, 50, 100);
    private static PokemonSpecies pidgeotto = new PokemonSpecies(17, "Pidgeotto", Type.NORMAL, Type.FLYING, 0, pidgeot, 500, 55, 70, 40, 85);
    private static PokemonSpecies pidgey = new PokemonSpecies(16, "Pidgey", Type.NORMAL, Type.FLYING, 100, pidgeotto, 200, 50, 40, 40, 70);

    private static PokemonSpecies raichu = new PokemonSpecies(26, "Raichu", Type.ELECTRIC, null, 0, null, 0, 90, 90, 60, 110);
    private static PokemonSpecies pikachu = new PokemonSpecies(25, "Pikachu", Type.ELECTRIC, null, 0, raichu, 1000, 70, 75, 40, 90);
    private static PokemonSpecies pichu = new PokemonSpecies(172, "Pichu", Type.ELECTRIC, null, 300, pikachu, 600, 45, 65, 35, 55);

    private static PokemonSpecies crobat = new PokemonSpecies(169, "Crobat", Type.POISON, Type.FLYING, 0, null, 0, 70, 80, 50, 130);
    private static PokemonSpecies golbat = new PokemonSpecies(42, "Golbat", Type.POISON, Type.FLYING, 0, crobat, 700, 55, 55, 40, 100);
    private static PokemonSpecies zubat = new PokemonSpecies(41, "Zubat", Type.POISON, Type.FLYING, 100, golbat, 200, 50, 40, 35, 75);

    private static PokemonSpecies vileplume = new PokemonSpecies(45, "Vileplume", Type.GRASS, Type.POISON, 0, null, 0, 130, 50, 120, 35);
    private static PokemonSpecies gloom = new PokemonSpecies(44, "Gloom", Type.GRASS, Type.POISON, 0, vileplume, 800, 85, 50, 85, 40);
    private static PokemonSpecies oddish = new PokemonSpecies(43, "Oddish", Type.GRASS, Type.POISON, 200, gloom, 400, 50, 40, 60, 50);

    private static PokemonSpecies poliwrath = new PokemonSpecies(62, "Poliwrath", Type.WATER, Type.FIGHT, 0, null, 0, 85, 100, 90, 60);
    private static PokemonSpecies poliwhirl = new PokemonSpecies(61, "Poliwhirl", Type.WATER, null, 0, poliwrath, 800, 70, 80, 60, 50);
    private static PokemonSpecies poliwag = new PokemonSpecies(60, "Poliwag", Type.WATER, null, 200, poliwhirl, 400, 50, 60, 40, 50);

    private static PokemonSpecies alakazam = new PokemonSpecies(65, "Alakazam", Type.PSYCHIC, null, 0, null, 0, 80, 155, 40, 100);
    private static PokemonSpecies kadabra = new PokemonSpecies(64, "Kadabra", Type.PSYCHIC, null, 0, alakazam, 1500, 65, 100, 40, 75);
    private static PokemonSpecies abra = new PokemonSpecies(63, "Abra", Type.PSYCHIC, null, 350, kadabra, 750, 50, 60, 40, 50);

    private static PokemonSpecies machamp = new PokemonSpecies(68, "Machamp", Type.FIGHT, null, 0, null, 0, 100, 60, 140, 50);
    private static PokemonSpecies machoke = new PokemonSpecies(67, "Machoke", Type.FIGHT, null, 0, machamp, 800, 70, 50, 80, 40);
    private static PokemonSpecies machop = new PokemonSpecies(66, "Machop", Type.FIGHT, null, 200, machoke, 400, 50, 70, 50, 30);

    private static PokemonSpecies golem = new PokemonSpecies(76, "Golem", Type.ROCK, Type.GROUND, 0, null, 0, 125, 60, 150, 40);
    private static PokemonSpecies graveler = new PokemonSpecies(75, "Graveler", Type.ROCK, Type.GROUND, 0, golem, 1500, 90, 50, 105, 40);
    private static PokemonSpecies geodude = new PokemonSpecies(74, "Geodude", Type.ROCK, Type.GROUND, 350, graveler, 750, 60, 40, 60, 40);

    private static PokemonSpecies gengar = new PokemonSpecies(94, "Gengar", Type.GHOST, Type.POISON, 0, null, 0, 80, 145, 50, 100);
    private static PokemonSpecies haunter = new PokemonSpecies(93, "Haunter", Type.GHOST, Type.POISON, 0, gengar, 1500, 65, 90, 50, 75);
    private static PokemonSpecies gastly = new PokemonSpecies(92, "Gastly", Type.GHOST, Type.POISON, 350, haunter, 750, 50, 40, 60, 50);

    private static PokemonSpecies dragonite = new PokemonSpecies(149, "Dragonite", Type.DRAGON, Type.FLYING, 0, null, 0, 100, 120, 80, 100);
    private static PokemonSpecies dragonair = new PokemonSpecies(148, "Dragonair", Type.DRAGON, null, 0, dragonite, 2000, 75, 85, 65, 75);
    private static PokemonSpecies dratini = new PokemonSpecies(147, "Dratini", Type.DRAGON, null, 500, dragonair, 1000, 50, 50, 50, 50);

    private static PokemonSpecies articuno = new PokemonSpecies(144, "Articuno", Type.ICE, Type.FLYING, 5000, null, 0, 130, 120, 140, 110);

    private static PokemonSpecies zapdos = new PokemonSpecies(145, "Zapdos", Type.ELECTRIC, Type.FLYING, 5000, null, 0, 120, 120,120 ,140);

    private static PokemonSpecies moltres = new PokemonSpecies(146, "Moltres", Type.FIRE, Type.FLYING, 5000, null, 0, 120, 130, 130, 120);

    private static PokemonSpecies mewtwo = new PokemonSpecies(150, "Mewtwo", Type.PSYCHIC, null, 7500, null, 0, 130,150,150,120);



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
