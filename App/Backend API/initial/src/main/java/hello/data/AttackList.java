package hello.data;

import hello.domainclasses.Attack;
import hello.domainclasses.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefv on 24-Oct-17.
 */
public class AttackList {
    private static List<Attack> attacks = Arrays.asList(
            // Formules for cost (all rounded to nearest 5)
            // Super Cheap = (Power * Accuracy) * 0.5
            // Cheap = (Power * Accuracy) * 0.66
            // Medium = Power * Accuracy
            // Expensive = (Power * Accuracy) * 1.33

            // Normal: Super Cheap
            new Attack("Cut", Type.NORMAL, 50, 100, 25),
            new Attack("Hyper Voice", Type.NORMAL, 90, 100, 45),

            // Fire: Medium
            new Attack("Flamethrower", Type.FIRE, 70, 100, 70),
            new Attack("Ember", Type.FIRE, 40, 100, 40),
            new Attack("Fire Blast", Type.FIRE, 110, 80, 90),

            // Water: Medium
            new Attack("Hydro Pump", Type.WATER, 110, 80, 90),
            new Attack("Surf", Type.WATER, 90, 100, 90),
            new Attack("Water Gun", Type.WATER, 40, 100, 40),

            // Electric: Cheap
            new Attack("Thunderbolt", Type.ELECTRIC, 70, 100, 45),
            new Attack("Thunder", Type.ELECTRIC, 110, 80, 60),
            new Attack("Thunder Shock", Type.ELECTRIC, 40, 100, 25),

            // Grass: Super Cheap
            new Attack("Power Whip", Type.GRASS, 110, 80, 45),
            new Attack("Vine Whip", Type.GRASS, 40, 100, 20),
            new Attack("Energy Ball", Type.GRASS, 90, 100, 45),

            // Ice: Medium
            new Attack("Ice Beam", Type.ICE, 90, 100, 90),
            new Attack("Ice Shard", Type.ICE, 40, 100, 40),
            new Attack("Blizzard", Type.ICE, 110, 80, 90),

            // Fight: Medium
            new Attack("Brick Break", Type.FIGHT, 75, 100, 75),
            new Attack("Rock Smash", Type.FIGHT, 40, 100, 40),
            new Attack("Focus Blast", Type.FIGHT, 120, 70, 80),

            // Poison: Super Cheap
            new Attack("Acid", Type.POISON, 40, 100, 20),
            new Attack("Sludge Bomb", Type.POISON, 90, 100, 45),
            new Attack("Gunk Shot", Type.POISON, 120, 80, 50),

            // Ground: Expensive
            new Attack("Earthquake", Type.GROUND, 100, 100, 130),
            new Attack("Mud Shot", Type.GROUND, 55, 95, 70),
            new Attack("Bulldoze", Type.GROUND, 75, 100, 100),

            // Flying: Medium
            new Attack("Aeroblast", Type.FLYING, 100, 95, 95),
            new Attack("Drill Peck", Type.FLYING, 80, 100, 80),
            new Attack("Gust", Type.FLYING, 40, 100, 40),

            // Psychic: Medium
            new Attack("Confusion", Type.PSYCHIC, 50, 100, 50),
            new Attack("Extrasensory", Type.PSYCHIC, 80, 100, 80),
            new Attack("Psystrike", Type.PSYCHIC, 100,100,100),

            // Bug: Cheap
            new Attack("Megahorn", Type.BUG, 120, 85, 65),
            new Attack("X-Scissor", Type.BUG, 80, 100, 55),
            new Attack("Struggle Bug", Type.BUG, 50, 100, 35),

            // Rock: Expensive
            new Attack("Rock Throw", Type.ROCK, 50, 90, 60),
            new Attack("Rock Slide", Type.ROCK, 75, 90, 90),
            new Attack("Head Smash", Type.ROCK, 150, 80, 160),

            // Ghost: Medium
            new Attack("Shadow Ball", Type.GHOST, 80, 100, 80),
            new Attack("Astonish", Type.GHOST, 30, 100, 30),
            new Attack("Shadow Force", Type.GHOST, 120, 80, 100),

            // Dragon: Medium
            new Attack("Dragonbreath", Type.DRAGON, 60, 100, 60),
            new Attack("Dragon Pulse", Type.DRAGON, 85, 100, 85),
            new Attack("Draco Meteor", Type.DRAGON, 130, 80, 105),

            // Dark: Medium
            new Attack("Pursuit", Type.DARK, 40, 100, 40),
            new Attack("Crunch", Type.DARK, 80, 100, 80),
            new Attack("Throat Chop", Type.DARK, 120, 80, 95),

            // Steel: Cheap
            new Attack("Bullet Punch", Type.STEEL, 40, 100, 25),
            new Attack("Flash Cannon", Type.STEEL, 80, 100, 55),
            new Attack("Meteor Mash", Type.STEEL, 120, 80, 65)
    );


    public static Attack[] getAttacksList() {
        return (Attack[]) attacks.toArray();
    }

    public static Attack getAttackByName(String name) {
        return attacks.stream().filter(attack -> attack.getAttack().toLowerCase().equals(name.toLowerCase())).findAny().get();
    }
}
