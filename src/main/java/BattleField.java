import model.Commander;

public class BattleField {

    private final Commander player;
    private final Commander enemy;
    private double hitPoints = 0;

    public BattleField(Commander player, Commander enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Commander getPlayer() {
        return player;
    }

    public Commander getEnemy() {
        return enemy;
    }

    public boolean friendlyWarriorIsDead() {
        return player.getWarriors().get(0).getHealthPoints() <= 0;
    }

    public boolean enemyWarriorIsDead() {
        return enemy.getWarriors().get(0).getHealthPoints() <= 0;
    }

    public void friendlyWarriorAttack() {
        hitPoints = player.getWarriors().get(0).getPower();
        if (player
                .getWarriors()
                .get(0)
                .getBonusRatio()
                .getSuitableEnemy()
                .getClass()
                .getSimpleName()
                .equals(enemy.getWarriors().get(0).getClass().getSimpleName())
        ) {
            double ratio = (double) player.getWarriors().get(0).getBonusRatio().getRatio() / 100;
            double bonus = hitPoints * ratio;
            hitPoints = hitPoints + bonus;
        }
        hitPoints = hitPoints - enemy.getWarriors().get(0).getDefense();
        if (hitPoints > 0) {
            enemy
                    .getWarriors()
                    .get(0)
                    .setHealthPoints(enemy.getWarriors().get(0).getHealthPoints() - hitPoints);
        }
        hitPoints = 0;
    }

    public void enemyWarriorAttack() {
        hitPoints = enemy.getWarriors().get(0).getPower();
        if (enemy
                .getWarriors()
                .get(0)
                .getBonusRatio()
                .getSuitableEnemy()
                .getClass()
                .getSimpleName()
                .equals(player.getWarriors().get(0).getClass().getSimpleName())) {
            double ratio = (double) enemy.getWarriors().get(0).getBonusRatio().getRatio() / 100;
            double bonus = hitPoints * ratio;
            hitPoints = hitPoints + bonus;
        }
        hitPoints = hitPoints - player.getWarriors().get(0).getDefense();
        if (hitPoints > 0) {
            player
                    .getWarriors()
                    .get(0)
                    .setHealthPoints(player.getWarriors().get(0).getHealthPoints() - hitPoints);
        }
    }

    public void duelBattle() {
        if (player.getWarriors().get(0).getAttackPriority()
                > enemy.getWarriors().get(0).getAttackPriority()) {
            friendlyWarriorAttack();
            if (enemyWarriorIsDead()) {
                enemy.getWarriors().remove(enemy.getWarriors().get(0));
            } else {
                enemyWarriorAttack();
                if (friendlyWarriorIsDead()) {
                    player.getWarriors().remove(player.getWarriors().get(0));
                }
            }
        } else if (player.getWarriors().get(0).getAttackPriority()
                < enemy.getWarriors().get(0).getAttackPriority()) {
            enemyWarriorAttack();
            if (friendlyWarriorIsDead()) {
                player.getWarriors().remove(player.getWarriors().get(0));
            } else {
                friendlyWarriorAttack();
                if (enemyWarriorIsDead()) {
                    enemy.getWarriors().remove(enemy.getWarriors().get(0));
                }
            }
        } else if (player.getWarriors().get(0).getAttackPriority()
                == enemy.getWarriors().get(0).getAttackPriority()) {
            int random = getRandomNumber();
            if (random == 1) {
                friendlyWarriorAttack();
                if (enemyWarriorIsDead()) {
                    enemy.getWarriors().remove(enemy.getWarriors().get(0));
                }
            } else {
                enemyWarriorAttack();
                if (friendlyWarriorIsDead()) {
                    player.getWarriors().remove(player.getWarriors().get(0));
                }
            }
        }
    }

    public int getRandomNumber() {
        return (int) ((Math.random() * (3 - 1)) + 1);
    }
}
