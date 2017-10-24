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
            new Attack("Ice Beam", "ice", 70, 100, 70)
    );


    public static Attack[] getAttacksList() {
        return (Attack[]) attacks.toArray();
    }

    public static Attack getAttackByName(String name) {
        return attacks.stream().filter(attack -> attack.getAttack().toLowerCase().equals(name.toLowerCase())).findAny().get();
    }
}
