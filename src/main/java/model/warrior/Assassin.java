package model.warrior;

public class Assassin extends Warrior {

    public Assassin() {
        super(13,10,5,12,20);
    }

    @Override
    public void initBonusRatio() {
        bonusRatio.setRatio(50);
        bonusRatio.setSuitableEnemy(new Assassin());
    }
}
