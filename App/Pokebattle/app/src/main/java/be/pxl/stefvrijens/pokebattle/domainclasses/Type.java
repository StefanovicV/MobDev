package be.pxl.stefvrijens.pokebattle.domainclasses;

/**
 * Created by stefv on 25-Oct-17.
 */

public enum Type {
    NORMAL("normal"),
    FIRE("fire"),
    WATER("water"),
    ELECTRIC("electric"),
    GRASS("grass"),
    ICE("ice"),
    FIGHT("fight"),
    POISON("poison"),
    GROUND("ground"),
    FLYING("flying"),
    PSYCHIC("psychic"),
    BUG("bug"),
    ROCK("rock"),
    GHOST("ghost"),
    DRAGON("dragon"),
    DARK("dark"),
    STEEL("steel");

    private String typeName;

    Type(String name) {
        typeName = name;
    }

    public boolean hasResistanceTo(Type attackingType) {
        Type[] resistances;
        switch (this.typeName) {
            case ("normal"):
                resistances = new Type[]{Type.GHOST};
                break;
            case ("fire"):
                resistances = new Type[]{Type.FIRE, Type.GRASS, Type.ICE, Type.BUG, Type.STEEL};
                break;
            case ("water"):
                resistances = new Type[]{Type.FIRE, Type.WATER, Type.ICE, Type.STEEL};
                break;
            case ("electric"):
                resistances = new Type[]{Type.ELECTRIC, Type.FLYING, Type.STEEL};
                break;
            case ("grass"):
                resistances = new Type[]{Type.WATER, Type.ELECTRIC, Type.GRASS, Type.GROUND};
                break;
            case ("ice"):
                resistances = new Type[]{Type.ICE};
                break;
            case ("fight"):
                resistances = new Type[]{Type.BUG, Type.ROCK, Type.DARK};
                break;
            case ("poison"):
                resistances = new Type[]{Type.GRASS, Type.POISON, Type.FIGHT, Type.BUG};
                break;
            case ("ground"):
                resistances = new Type[]{Type.ELECTRIC, Type.POISON, Type.ROCK};
                break;
            case ("flying"):
                resistances = new Type[]{Type.GRASS, Type.FIGHT, Type.GROUND, Type.BUG};
                break;
            case ("psychic"):
                resistances = new Type[]{Type.FIGHT, Type.PSYCHIC};
                break;
            case ("bug"):
                resistances = new Type[]{Type.GRASS, Type.FIGHT, Type.GROUND};
                break;
            case ("rock"):
                resistances = new Type[]{Type.NORMAL, Type.FIRE, Type.POISON, Type.FLYING};
                break;
            case ("ghost"):
                resistances = new Type[]{Type.NORMAL, Type.FIGHT, Type.POISON, Type.BUG};
                break;
            case ("dragon"):
                resistances = new Type[]{Type.FIRE, Type.WATER, Type.ELECTRIC, Type.GRASS};
                break;
            case ("dark"):
                resistances = new Type[]{Type.PSYCHIC, Type.GHOST, Type.DARK};
                break;
            case ("steel"):
                resistances = new Type[]{Type.NORMAL, Type.GRASS, Type.ICE, Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.ROCK, Type.DRAGON, Type.STEEL};
                break;
            default:
                resistances = new Type[0];
        }
        for (int i = 0; i < resistances.length; i++) {
            if (resistances[i] == attackingType) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWeaknessTo(Type attackingType) {
        Type[] weaknesses;
        switch (this.typeName) {
            case ("normal"):
                weaknesses = new Type[]{Type.FIGHT};
                break;
            case ("fire"):
                weaknesses = new Type[]{Type.WATER, Type.GROUND, Type.ROCK};
                break;
            case ("water"):
                weaknesses = new Type[]{Type.ELECTRIC, Type.GRASS};
                break;
            case ("electric"):
                weaknesses = new Type[]{Type.GROUND};
                break;
            case ("grass"):
                weaknesses = new Type[]{Type.FIRE, Type.ICE, Type.POISON, Type.FLYING, Type.BUG};
                break;
            case ("ice"):
                weaknesses = new Type[]{Type.FIRE, Type.FIGHT, Type.ROCK, Type.STEEL};
                break;
            case ("fight"):
                weaknesses = new Type[]{Type.FLYING, Type.PSYCHIC};
                break;
            case ("poison"):
                weaknesses = new Type[]{Type.GROUND, Type.PSYCHIC};
                break;
            case ("ground"):
                weaknesses = new Type[]{Type.WATER, Type.GRASS, Type.ICE};
                break;
            case ("flying"):
                weaknesses = new Type[]{Type.ELECTRIC, Type.ICE, Type.ROCK};
                break;
            case ("psychic"):
                weaknesses = new Type[]{Type.BUG, Type.GHOST, Type.DARK};
                break;
            case ("bug"):
                weaknesses = new Type[]{Type.FIRE, Type.FLYING, Type.ROCK};
                break;
            case ("rock"):
                weaknesses = new Type[]{Type.WATER, Type.GRASS, Type.FIGHT, Type.GROUND, Type.STEEL};
                break;
            case ("ghost"):
                weaknesses = new Type[]{Type.GHOST, Type.DARK};
                break;
            case ("dragon"):
                weaknesses = new Type[]{Type.ICE, Type.DRAGON};
                break;
            case ("dark"):
                weaknesses = new Type[]{Type.FIGHT, Type.BUG};
                break;
            case ("steel"):
                weaknesses = new Type[]{Type.FIRE, Type.FIGHT, Type.GROUND};
                break;
            default:
                weaknesses = new Type[0];
        }
        for (int i = 0; i < weaknesses.length; i++) {
            if (weaknesses[i] == attackingType) {
                return true;
            }
        }
        return false;
    }
}
