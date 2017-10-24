package hello.domainclasses;

/**
 * Created by stefv on 24-Oct-17.
 */
public class Attack
{
    private String attack;
    private String type;
    private int power;
    private int accuracy;
    private int cost;


    public Attack(String attack, String type, int power, int accuracy, int cost) {
        this.attack = attack;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.cost = cost;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
