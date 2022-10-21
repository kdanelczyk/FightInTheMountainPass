package model;

import model.warrior.*;

public class WarriorFactory {

    public Warrior createWarrior(WarriorType WarriorType) {
        Warrior warrior;
        warrior = switch (WarriorType) {
            case ASSASSIN -> new Assassin();
            case GUARDIAN -> new Guardian();
            case HERO -> new Hero();
            case HORSEMAN -> new HorseMan();
            case PIKEMAN -> new PikeMan();
            case SWORDSMAN -> new SwordsMan();
            case BERSERKER -> new Berserker();
            };
        warrior.initBonusRatio();
        return warrior;
    }
}
