package hello.data;

import hello.domainclasses.Attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefv on 24-Oct-17.
 */
public class AttackList {
    private static List<Attack> attacks = Arrays.asList(
            new Attack("Earthquake", "ground", 100, 100, 100),
            new Attack("Thunderbolt", "electric", 70, 100, 70),
            new Attack("Flamethrower", "fire", 70, 100, 70),
            new Attack("Ice Beam", "ice", 70, 100, 70),
            new Attack("Megahorn", "bug", 120, 85, 80),
            new Attack("Silver wind", "bug", 60, 100, 75),
            new Attack("Dragonbreath", "dragon", 60, 100, 70),
            new Attack("Meteoric Swarm", "dragon", 140, 80, 95),
            new Attack("Thunder", "electric", 120, 70, 85),
            new Attack("Volt Attack", "electric", 120, 100, 80),
            new Attack("Spark", "electric", 65, 100, 70),
            new Attack("Cross Shop", "fight", 100, 80, 75),
            new Attack("Sky Uppercut", "fight", 85, 90, 70),
            new Attack("Blast Burn", "fire", 150, 90, 85),
            new Attack("Fire Blast", "fire", 120, 85, 80),
            new Attack("Aeroblast", "flying", 100, 95, 75),
            new Attack("Drill Peck", "flying", 80, 100, 75),
            new Attack("Shadow Ball", "ghost", 80, 100, 80),
            new Attack("Astonish", "ghost", 30, 100, 60),
            new Attack("Frenzy Plant", "grass", 150, 90, 95),
            new Attack("Razor Leaf", "grass", 55, 90, 65),
            new Attack("Bonemerang", "ground", 50, 90, 65),
            new Attack("Bone Club", "ground", 65, 85, 70),
            new Attack("Doubleslap", "normal", 75, 85, 70),
            new Attack("Strength", "normal", 80, 100, 75),
            new Attack("Poison Fang", "poison", 50, 100, 60),
            new Attack("Sludge Bomb", "poison", 90, 100, 80),
            new Attack("Dream Eater", "psychic", 100, 100, 85),
            new Attack("Luster Purge", "psychic", 65, 100, 70),
            new Attack("Rock Blast", "rock", 55, 100, 60),
            new Attack("Rollout", "rock", 90, 100, 75),
            new Attack("Hydro Cannon", "water", 150, 90, 90),
            new Attack("Octazooka", "water", 95, 100, 75),
            new Attack("Whirlpool", "water", 55, 100, 60)
    );


    public static Attack[] getAttacksList() {
        return (Attack[]) attacks.toArray();
    }

    public static Attack getAttackByName(String name) {
        return attacks.stream().filter(attack -> attack.getAttack().toLowerCase().equals(name.toLowerCase())).findAny().get();
    }
}
