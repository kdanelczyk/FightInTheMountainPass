package model;
import model.warrior.Warrior;

public class BonusRatio {

    private Warrior suitableEnemy;
    private int ratio;

    public BonusRatio() {
    }

    public Warrior getSuitableEnemy() {
        return suitableEnemy;
    }

    public void setSuitableEnemy(Warrior suitableEnemy) {
        this.suitableEnemy = suitableEnemy;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
}