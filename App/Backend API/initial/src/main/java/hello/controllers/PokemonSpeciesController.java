package hello.controllers;

import hello.data.SpeciesList;
import hello.domainclasses.PokemonSpecies;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stefv on 24-Oct-17.
 */
@RestController
public class PokemonSpeciesController {
    @RequestMapping("/species")
    public PokemonSpecies[] pokemonSpecies(@RequestParam(value = "shop", defaultValue = "") String shop) {
        if (shop.equals("")) {
            return SpeciesList.getAllSpecies();
        } else {
            return SpeciesList.getBuyableSpecies();
        }
    }

    @RequestMapping("/species/{speciesId}")
    public PokemonSpecies getSpeciesById(@PathVariable(value="speciesId")String speciesId) {
        return SpeciesList.getSpeciesById(speciesId);
    }
}
