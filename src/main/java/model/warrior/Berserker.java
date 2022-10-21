package model.warrior;

public class Berserker extends Warrior {

    public Berserker() {
        super(10, 15, 4,11,20);
    }

    @Override
    public void initBonusRatio(){
        bonusRatio.setRatio(30);
        bonusRatio.setSuitableEnemy(new PikeMan());
    }
}
