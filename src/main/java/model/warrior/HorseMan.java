package model.warrior;

public class HorseMan extends Warrior {

    public HorseMan() {
        super(10,13,8,9,23);
    }

    @Override
    public void initBonusRatio() {
        bonusRatio.setRatio(30);
        bonusRatio.setSuitableEnemy(new Berserker());
    }
}
