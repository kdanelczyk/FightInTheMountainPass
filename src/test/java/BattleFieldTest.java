import model.Commander;
import model.warrior.Assassin;
import model.warrior.Berserker;
import model.warrior.HorseMan;
import model.warrior.Warrior;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleFieldTest {

    private final BattleField battleField = new BattleField(
            new Commander(50, 100),
            new Commander(0, 500));
    private final ArrayList<Warrior> playerWarriors = new ArrayList<>();
    private final ArrayList<Warrior> enemyWarriors = new ArrayList<>();

    @Test
    void testFriendlyWarriorIsDeadFalse() {
        playerWarriors.add(new Assassin());
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getPlayer().getWarriors().get(0).setHealthPoints(1);
        assertFalse(battleField.friendlyWarriorIsDead());
    }

    @Test
    void testFriendlyWarriorIsDeadTrue() {
        playerWarriors.add(new Assassin());
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getPlayer().getWarriors().get(0).setHealthPoints(0);
        assertTrue(battleField.friendlyWarriorIsDead());
    }

    @Test
    void testEnemyWarriorIsDeadFalse() {
        enemyWarriors.add(new Assassin());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getEnemy().getWarriors().get(0).setHealthPoints(1);
        assertFalse(battleField.enemyWarriorIsDead());
    }

    @Test
    void testEnemyWarriorIsDeadTrue() {
        enemyWarriors.add(new Assassin());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getEnemy().getWarriors().get(0).setHealthPoints(0);
        assertTrue(battleField.enemyWarriorIsDead());
    }

    @Test
    void friendlyWarriorAttackWithBonusRatioWorking() {
        playerWarriors.add(new HorseMan());
        enemyWarriors.add(new Berserker());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getEnemy().getWarriors().get(0).initBonusRatio();
        battleField.getPlayer().getWarriors().get(0).initBonusRatio();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), 10);
        battleField.friendlyWarriorAttack();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), -2.8999999999999986);
    }

    @Test
    void friendlyWarriorAttack() {
        playerWarriors.add(new Assassin());
        enemyWarriors.add(new Berserker());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getEnemy().getWarriors().get(0).initBonusRatio();
        battleField.getPlayer().getWarriors().get(0).initBonusRatio();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), 10);
        battleField.friendlyWarriorAttack();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), 4);
    }

    @Test
    void enemyWarriorAttackWithBonusRatioWorking() {
        playerWarriors.add(new Berserker());
        enemyWarriors.add(new HorseMan());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getEnemy().getWarriors().get(0).initBonusRatio();
        battleField.getPlayer().getWarriors().get(0).initBonusRatio();
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), 10);
        battleField.enemyWarriorAttack();
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), -2.8999999999999986);
    }

    @Test
    void enemyWarriorAttack() {
        playerWarriors.add(new Assassin());
        enemyWarriors.add(new HorseMan());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getEnemy().getWarriors().get(0).initBonusRatio();
        battleField.getPlayer().getWarriors().get(0).initBonusRatio();
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), 13);
        battleField.enemyWarriorAttack();
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), 5);
    }

    @Test
    void duelBattle() {
        playerWarriors.add(new Assassin());
        enemyWarriors.add(new HorseMan());
        battleField.getEnemy().setWarriors(enemyWarriors);
        battleField.getPlayer().setWarriors(playerWarriors);
        battleField.getEnemy().getWarriors().get(0).initBonusRatio();
        battleField.getPlayer().getWarriors().get(0).initBonusRatio();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), 10);
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), 13);
        battleField.duelBattle();
        assertEquals(battleField.getEnemy().getWarriors().get(0).getHealthPoints(), 8);
        assertEquals(battleField.getPlayer().getWarriors().get(0).getHealthPoints(), 5);
    }
}