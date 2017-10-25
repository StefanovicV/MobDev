package be.pxl.stefvrijens.pokebattle.domainclasses;

/**
 * Created by stefv on 25-Oct-17.
 */

public enum Type {
    NORMAL(new Type[]{Type.FIGHT}, new Type[]{Type.GHOST}),
    FIRE(new Type[]{Type.WATER, Type.GROUND, Type.ROCK}, new Type[]{Type.FIRE, Type.GRASS, Type.ICE, Type.BUG, Type.STEEL}),
    WATER(new Type[]{Type.ELECTRIC, Type.GRASS}, new Type[]{Type.FIRE, Type.WATER, Type.ICE, Type.STEEL}),
    ELECTRIC(new Type[]{Type.GROUND}, new Type[]{Type.ELECTRIC, Type.FLYING, Type.STEEL}),
    GRASS(new Type[]{Type.FIRE, Type.ICE, Type.POISON, Type.FLYING, Type.BUG}, new Type[]{Type.WATER, Type.ELECTRIC, Type.GRASS, Type.GROUND}),
    ICE(new Type[]{Type.FIRE, Type.FIGHT, Type.ROCK, Type.STEEL}, new Type[]{Type.ICE}),
    FIGHT(new Type[]{Type.FLYING, Type.PSYCHIC}, new Type[]{Type.BUG, Type.ROCK, Type.DARK}),
    POISON(new Type[]{Type.GROUND, Type.PSYCHIC}, new Type[]{Type.GRASS, Type.POISON, Type.FIGHT, Type.BUG}),
    GROUND(new Type[]{Type.WATER, Type.GRASS, Type.ICE}, new Type[]{Type.ELECTRIC, Type.POISON, Type.ROCK}),
    FLYING(new Type[]{Type.ELECTRIC, Type.ICE, Type.ROCK}, new Type[]{Type.GRASS, Type.FIGHT, Type.GROUND, Type.BUG}),
    PSYCHIC(new Type[]{Type.BUG, Type.GHOST, Type.DARK}, new Type[]{Type.FIGHT, Type.PSYCHIC}),
    BUG(new Type[]{Type.FIRE, Type.FLYING, Type.ROCK}, new Type[]{Type.GRASS, Type.FIGHT, Type.GROUND}),
    ROCK(new Type[]{Type.WATER, Type.GRASS, Type.FIGHT, Type.GROUND, Type.STEEL}, new Type[]{Type.NORMAL, Type.FIRE, Type.POISON, Type.FLYING}),
    GHOST(new Type[]{Type.GHOST, Type.DARK}, new Type[]{Type.NORMAL, Type.FIGHT, Type.POISON, Type.BUG}),
    DRAGON(new Type[]{Type.ICE, Type.DRAGON}, new Type[]{Type.FIRE, Type.WATER, Type.ELECTRIC, Type.GRASS}),
    DARK(new Type[]{Type.FIGHT, Type.BUG}, new Type[]{Type.PSYCHIC, Type.GHOST, Type.DARK}),
    STEEL(new Type[]{Type.FIRE, Type.FIGHT, Type.GROUND}, new Type[]{Type.NORMAL, Type.GRASS, Type.ICE, Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.ROCK, Type.DRAGON, Type.STEEL});

    // These are considered defensively: the attack inside the property is considered the attacker.
    // These are the types that do double damage against this type
    private Type[] weakAgainst;
    // These are the types that do half damage against this type
    private Type[] strongAgainst;
    Type(Type[] weakTypes, Type[] strongTypes) {
        this.weakAgainst = strongTypes;
        this.strongAgainst = weakTypes;
    }

    public boolean isStrongAgainst(Type type) {
        for (int i = 0; i < this.strongAgainst.length; i++) {
            if (this.strongAgainst[i].equals(type)) {
                return true;
            }
        }
        return false;
    }
    public boolean isWeakAgainst(Type type) {
        for (int i = 0; i < this.weakAgainst.length; i++) {
            if (this.weakAgainst[i].equals(type)) {
                return true;
            }
        }
        return false;
    }
}
