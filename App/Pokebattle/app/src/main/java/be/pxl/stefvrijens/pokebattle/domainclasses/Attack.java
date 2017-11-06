package be.pxl.stefvrijens.pokebattle.domainclasses;

import java.io.Serializable;

/**
 * Created by stefv on 25-Oct-17.
 */

public class Attack implements Serializable{
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int cost;

    public Attack(String name, Type type, int power, int accuracy, int cost) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.cost = cost;
    }

    public static Attack generateTestAttack(int variable) {
        return new Attack("TestAttack " + variable, Type.NORMAL, 10 * variable, 100, variable * 20);
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
