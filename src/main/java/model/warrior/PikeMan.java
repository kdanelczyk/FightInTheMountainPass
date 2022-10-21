package model.warrior;

public class PikeMan extends Warrior{

    public PikeMan() {
        super(12,10,9,9,19);
    }

    @Override
    public void initBonusRatio(){
        bonusRatio.setRatio(60);
        bonusRatio.setSuitableEnemy(new HorseMan());
    }
}
