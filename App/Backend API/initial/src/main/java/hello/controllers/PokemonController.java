package hello.controllers;

import hello.data.AttackList;
import hello.data.SpeciesList;
import hello.domainclasses.Attack;
import hello.domainclasses.Pokemon;
import hello.domainclasses.PokemonSpecies;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stefv on 07-Nov-17.
 */
@RestController
public class PokemonController {
//    @RequestMapping("/pokemon")
//    public Pokemon generatePokemonByRating(@RequestParam(value = "pokemonRating", defaultValue = "400")String pokemonRating) {
//        int rating = Integer.parseInt(pokemonRating);
//        int attacksRating;
//        int speciesRating;
//        int numberOfAttacks;
//        PokemonSpecies species;
//        Attack[] attacks;
//
//        if (rating / 2 > 400) {
//            speciesRating = rating - 400;
//        } else {
//            speciesRating = rating / 2;
//        }
//        species = SpeciesList.getSpeciesByRating(speciesRating);
//        attacksRating = rating - species.getRating();
//        if (attacksRating < 60) {
//            numberOfAttacks = 1;
//        } else if (attacksRating < 120) {
//            numberOfAttacks = 2;
//        } else if (attacksRating < 180) {
//            numberOfAttacks = 3;
//        } else {
//            numberOfAttacks = 4;
//        }
//        attacks = new Attack[numberOfAttacks];
//        for (int i = 0; i < numberOfAttacks; i++) {
//            int thisAttackRating = attacksRating / (numberOfAttacks - i);
//            attacks[i] = AttackList.getAttackByRating(thisAttackRating);
//            attacksRating = attacksRating - attacks[i].getRating();
//        }
//        return new Pokemon(1, species, attacks);
//    }
    @RequestMapping("/pokemon")
    public Pokemon[] generateTeamByRating(@RequestParam(value = "pokemonRating", defaultValue = "1500")String pokemonRating) {
        int teamRating = Integer.parseInt(pokemonRating);
        int amountOfPokemon = 6;
        for (int i = 1; i <= 6; i++) {
            if ((teamRating / i) <=  500) {
                amountOfPokemon = i;
                break;
            }
        }
        Pokemon[] team = new Pokemon[amountOfPokemon];
        for (int i = 0; i < team.length; i++) {
            team[i] = generatePokemonByRating(teamRating / (amountOfPokemon - i), i+1);
            teamRating -= team[i].getRating();
        }
        return team;
    }

    private Pokemon generatePokemonByRating(int rating, int id) {
        int speciesRating;
        int attacksRating;
        PokemonSpecies pokemon;
        Attack[] attacks = new Attack[0];

        if (rating > 1100) {
            speciesRating = 550;
        } else {
            speciesRating = rating / 2;
        }
        pokemon = SpeciesList.getSpeciesByRating(speciesRating);
        attacksRating = rating - pokemon.getRating();
        for (int i = 1; i <= 4; i++) {
            if ((attacksRating / i) <= 40 || i == 4) {
                attacks = new Attack[i];
                break;
            }
        }
        if (attacksRating > 400) {
            attacksRating = 400;
        }
        for (int i = 0; i < attacks.length; i++) {
            attacks[i] = AttackList.getAttackByRating(attacksRating / (attacks.length - i));
            attacksRating -= attacks[i].getRating();
            if (attacksRating <= 30) {
                break;
            }
        }
        return new Pokemon(id, pokemon, attacks);
    }
}
