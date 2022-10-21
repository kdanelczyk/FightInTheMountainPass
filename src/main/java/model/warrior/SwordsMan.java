package model.warrior;

public class SwordsMan extends Warrior{

    public SwordsMan() {
        super(11,12,7,10,26);
    }

    @Override
    public void initBonusRatio(){
        bonusRatio.setRatio(40);
        bonusRatio.setSuitableEnemy(new PikeMan());
    }
}
