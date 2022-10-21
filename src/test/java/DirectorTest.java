import model.Commander;
import model.warrior.Assassin;
import model.warrior.HorseMan;
import model.warrior.Warrior;
import model.warrior.WarriorType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectorTest {

    Director director = new Director(
            new BattleField(
                    new Commander(50, 100),
                    new Commander(0, 500)));
    ArrayList<Warrior> playerWarriors = new ArrayList<>();
    ArrayList<Warrior> enemyWarriors = new ArrayList<>();

    @Test
    void hireWarrior() {
        assertEquals(director.getBattleField().getPlayer().getRespect(), 100);
        director.hireWarrior(new Assassin(), WarriorType.ASSASSIN);
        assertEquals(director.getBattleField().getPlayer().getWarriors().size(), 1);
        assertEquals(director.getBattleField().getPlayer().getWarriors().get(0).getClass().getSimpleName(), "Assassin");
        assertEquals(director.getBattleField().getPlayer().getRespect(), 80);
    }

    @Test
    void hireWarriorNotRespectedCommander() {
        director.getBattleField().getPlayer().setRespect(10);
        assertEquals(director.getBattleField().getPlayer().getRespect(), 10);
        director.hireWarrior(new Assassin(), WarriorType.ASSASSIN);
        assertEquals(director.getBattleField().getPlayer().getWarriors().size(), 0);
        assertEquals(director.getBattleField().getPlayer().getRespect(), 10);
    }

    @Test
    void viewOnDuelBattle() {
        playerWarriors.add(new Assassin());
        enemyWarriors.add(new HorseMan());

        director.getBattleField().getEnemy().setWarriors(enemyWarriors);
        director.getBattleField().getPlayer().setWarriors(playerWarriors);

        assertEquals(director.viewOnDuelBattle(), "A ~VS~ H");
        assertEquals(director.getBattleField().getPlayer().getWarriors().get(0).getClass().getSimpleName(), "Assassin");
    }

    @Test
    void viewOnDuelBattleEnemyHasTwoWarriors() {
        playerWarriors.add(new Assassin());
        enemyWarriors.add(new HorseMan());
        enemyWarriors.add(new Assassin());

        director.getBattleField().getEnemy().setWarriors(enemyWarriors);
        director.getBattleField().getPlayer().setWarriors(playerWarriors);

        assertEquals(director.viewOnDuelBattle(), "A ~VS~ H..");
        assertEquals(director.getBattleField().getPlayer().getWarriors().get(0).getClass().getSimpleName(), "Assassin");
    }

    @Test
    void constructALineOfFriendlyWarriors() {
        playerWarriors.add(new Assassin());
        playerWarriors.add(new HorseMan());
        playerWarriors.add(new Assassin());

        director.getBattleField().getPlayer().setWarriors(playerWarriors);

        assertEquals(director.constructALineOfFriendlyWarriors(), "AHA");
    }

    @Test
    void createEnemyCommander() {
        assertEquals(director.getBattleField().getEnemy().getWarriors().size(), 0);
        director.createEnemyCommander();
        assertEquals(director.getBattleField().getEnemy().getWarriors().size(), 6);
    }

    @Test
    void takeASignOfEnemyWarrior() {
        enemyWarriors.add(new Assassin());
        director.getBattleField().getEnemy().setWarriors(enemyWarriors);

        assertEquals(director.takeASignOfEnemyWarrior(), "A");
    }

}