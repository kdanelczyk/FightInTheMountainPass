package model.warrior;

public class Hero extends Warrior {

    public Hero() {
        super(15,15,10,10,0);
    }

    @Override
    public void initBonusRatio(){
        bonusRatio.setRatio(100);
    }
}
