package model.warrior;

public class Guardian extends Warrior{

    public Guardian() {
        super(16,8,10,6,20);
    }

    @Override
    public void initBonusRatio(){
        bonusRatio.setRatio(35);
        bonusRatio.setSuitableEnemy(new SwordsMan());
    }
}
