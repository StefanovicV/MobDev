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
    @RequestMapping("/pokemon")
    public Pokemon generatePokemonByRating(@RequestParam(value = "pokemonRating", defaultValue = "400")String pokemonRating) {
        int rating = Integer.parseInt(pokemonRating);
        int attacksRating;
        int speciesRating;
        int numberOfAttacks;
        PokemonSpecies species;
        Attack[] attacks;

        if (rating / 2 > 400) {
            speciesRating = rating - 400;
        } else {
            speciesRating = rating / 2;
        }
        species = SpeciesList.getSpeciesByRating(speciesRating);
        attacksRating = rating - species.getRating();
        if (attacksRating < 60) {
            numberOfAttacks = 1;
        } else if (attacksRating < 120) {
            numberOfAttacks = 2;
        } else if (attacksRating < 180) {
            numberOfAttacks = 3;
        } else {
            numberOfAttacks = 4;
        }
        attacks = new Attack[numberOfAttacks];
        for (int i = 0; i < numberOfAttacks; i++) {
            int thisAttackRating = attacksRating / (numberOfAttacks - i);
            attacks[i] = AttackList.getAttackByRating(thisAttackRating);
            attacksRating = attacksRating - attacks[i].getRating();
        }
        return new Pokemon(1, species, attacks);
    }
}
