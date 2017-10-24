package hello.domainclasses;

/**
 * Created by stefv on 24-Oct-17.
 */
public class Item {
    private String name;
    private int cost;
    private int healingEffect;

    public Item(String name, int cost, int healingEffect) {
        this.name = name;
        this.cost = cost;
        this.healingEffect = healingEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealingEffect() {
        return healingEffect;
    }

    public void setHealingEffect(int healingEffect) {
        this.healingEffect = healingEffect;
    }
}
