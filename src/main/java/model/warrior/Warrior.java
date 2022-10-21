package model.warrior;
import model.BonusRatio;

public abstract class Warrior {

    private double healthPoints;
    private int power;
    private int defense;
    private int attackPriority;
    private int price;
    protected BonusRatio bonusRatio;

    public Warrior() {

    }

    public Warrior(int healthPoints, int power, int defense, int attackPriority, int price) {
        this.healthPoints = healthPoints;
        this.power = power;
        this.defense = defense;
        this.attackPriority = attackPriority;
        this.price = price;
        this.bonusRatio = new BonusRatio();
    }

    public int getPrice() {
        return price;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttackPriority() {
        return attackPriority;
    }

    public void setAttackPriority(int attackPriority) {
        this.attackPriority = attackPriority;
    }

    public BonusRatio getBonusRatio() {
        return bonusRatio;
    }

    public abstract void initBonusRatio();

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return (className +
                " - Live length: " + getHealthPoints() +
                " Power: " + getPower() +
                " Defense: " + getDefense() +
                " AttackPriority: " + getAttackPriority() + "\n");
    }
}
