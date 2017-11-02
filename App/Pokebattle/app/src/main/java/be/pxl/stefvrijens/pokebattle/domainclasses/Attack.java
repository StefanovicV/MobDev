package be.pxl.stefvrijens.pokebattle.domainclasses;

/**
 * Created by stefv on 25-Oct-17.
 */

public class Attack {
    private String name;
    private Type type;
    private int power;
    private int accuracy;

    public Attack(String name, Type type, int power, int accuracy) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
    }
    public int getAttackRating() {
        // Will usually be between 40 & 100
        return (int)(this.power * (this.accuracy / 100.0));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
