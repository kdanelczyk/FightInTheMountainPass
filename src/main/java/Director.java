import model.WarriorFactory;
import model.warrior.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Director {

    private ArrayList<Warrior> friendlyWarriors;
    private final BattleField battleField;

    public Director(BattleField battleField) {
        this.friendlyWarriors = new ArrayList<>();
        this.battleField = battleField;
    }

    public ArrayList<Warrior> getFriendlyWarriors() {
        return friendlyWarriors;
    }

    public void setFriendlyWarriors(ArrayList<Warrior> friendlyWarriors) {
        this.friendlyWarriors = friendlyWarriors;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void startScene() {
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Welcome Stranger!
                Thanks to the missions you completed,
                you have earned enough respect and gold to face the challenge.
                Take up the fight in the mountain pass through which the only path to your goal runs!
                """);
    }

    public void townScene() {
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                You are currently in a town.
                Here you have time to prepare your team:
                
                1. Go to the Inn.
                2. Train your warriors.
                3. Check the sorted list of warriors.
                4. Check their order in battle.
                5. Go into battle!
                """);
        System.out.println("Your gold: " + battleField.getPlayer().getGold());
        System.out.println("Your Respect: " + battleField.getPlayer().getRespect());
        System.out.println("Your warriors:");
        for(int i = 0; i < battleField.getPlayer().getWarriors().size(); i++) {
            System.out.print((i + 1) + ". " + battleField.getPlayer().getWarriors().get(i).toString());
        }
        Scanner in = new Scanner(System.in);

        String nextIntString = in.nextLine();
        int choice = filter(nextIntString);

        switch (choice) {
            case 1 -> hireCase();
            case 2 -> trainCase();
            case 3 -> sortingCase();
            case 4 -> orderOfWarriorsCase();
            case 5 -> {
                createYourself();
                System.out.println("""
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        You and your team have entered a place where you cannot turn your back to the enemy.
                        It's a time to start the battle for the mountain pass!
                        """);
                for (int i = 0; i < 100; i++) {
                    System.out.print("\nturn: " + (i + 1));
                    for (int j = 0; j < i + 1; j++) {
                        System.out.print("~");
                    }
                    System.out.println();
                    in.nextLine();
                    battleScene();
                    battleField.duelBattle();
                }
            }
            default -> townScene();
        }
    }

    public void hireCase() {
        System.out.println("""
                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                 In order to find the right people who will go into battle with you, you entered the inn.
                 Your reputation is high, so people want to go with you.
                 Now you have to choose the right warriors.
                 the order of buying a warriors is the same as the order of combat.
                 
                 the warrior represented by a given initial has a special attack bonus for that warrior.
                 A > A
                 B > P
                 G > S
                 H > B
                 P > H
                 S > G
                """);
        System.out.println("""
                                  
                Choose a mate for battle!
                1. Assasin..
                2. Berserker..
                3. Guardian..
                4. HorseMan..
                5. PikeMan..
                6. SwordsMan..
                0. <---
                                    
                """);
        Scanner in = new Scanner(System.in);
        String nextIntString = in.nextLine();
        int choiceOfPurchase = filter(nextIntString);
        if (choiceOfPurchase == 0) {
            townScene();
        }
        switch (choiceOfPurchase) {
            case 1 -> hireWarrior(new Assassin(), WarriorType.ASSASSIN);
            case 2 -> hireWarrior(new Berserker(), WarriorType.BERSERKER);
            case 3 -> hireWarrior(new Guardian(), WarriorType.GUARDIAN);
            case 4 -> hireWarrior(new HorseMan(), WarriorType.HORSEMAN);
            case 5 -> hireWarrior(new PikeMan(), WarriorType.PIKEMAN);
            case 6 -> hireWarrior(new SwordsMan(), WarriorType.SWORDSMAN);
            default -> hireCase();
        }
    }

    public void trainCase() {
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Investing in people and equipment is a very wise decision.
                Pay with gold to increase your fighter's stats.
                   """);
        System.out.println("\n" + "Choose the number of warrior: ");
        System.out.println("0. <---");
        Scanner in = new Scanner(System.in);
        String nextIntString = in.nextLine();
        int numberOfWarrior = filter(nextIntString);
        System.out.println("\n" + "Choose the number of stat: ");
        System.out.println("0. <---");
        Scanner in2 = new Scanner(System.in);
        String nextIntString2 = in2.nextLine();
        int numberOfStat = filter(nextIntString2);
        if (numberOfStat == 0 || numberOfWarrior == 0) {
            townScene();
        }
        switch (numberOfStat) {
            case 1:
                if (battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getHealthPoints()
                        > battleField.getPlayer().getGold()) {
                    System.out.println("\n" + "Not enough gold");
                } else {
                    battleField.getPlayer().setGold(battleField.getPlayer().getGold() - battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getHealthPoints());
                    battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).setHealthPoints(battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getHealthPoints() + 1);
                }
                break;
            case 2:
                if (battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getPower() >
                        battleField.getPlayer().getGold()) {
                    System.out.println("\n" + "Not enough gold");
                } else {
                    battleField.getPlayer().setGold(battleField.getPlayer().getGold() - battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getPower());
                    battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).setPower(battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getPower() + 1);
                }
                break;
            case 3:
                if (battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getDefense() >
                        battleField.getPlayer().getGold()) {
                    System.out.println("\n" + "Not enough gold");
                } else {
                    battleField.getPlayer().setGold(battleField.getPlayer().getGold() - battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getDefense());
                    battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).setDefense(battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getDefense() + 1);
                }
                break;
            case 4:
                if (battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getAttackPriority() >
                        battleField.getPlayer().getGold()) {
                    System.out.println("\n" + "Not enough gold");
                } else {
                    battleField.getPlayer().setGold(battleField.getPlayer().getGold() - battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getAttackPriority());
                    battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).setAttackPriority(battleField.getPlayer().getWarriors().get(numberOfWarrior - 1).getAttackPriority() + 1);
                }
                break;
            default:
                trainCase();
        }
    }

    public void sortingCase() {
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Sorting does not change the order of the fighters.
                Sort By:\s
                                    
                1. HealthPoints..
                2. Power..
                3. Defense..
                4. Attack Priority..
                0. <---
                """);
        Scanner in = new Scanner(System.in);
        String nextIntString = in.nextLine();
        int choiceOfSorting = filter(nextIntString);
        if (choiceOfSorting == 0) {
            townScene();
        }
        if (battleField.getPlayer().getWarriors().size() > 0) {
            switch (choiceOfSorting) {
                case 1 -> {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorted warriors by HealthPoints:");
                    sortByHealthPoints();
                    for (Warrior friendlyWarrior : friendlyWarriors) {
                        System.out.print(friendlyWarrior.toString());
                    }
                }
                case 2 -> {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorted warriors by Power:");
                    sortByPower();
                    for (Warrior friendlyWarrior : friendlyWarriors) {
                        System.out.print(friendlyWarrior.toString());
                    }
                }
                case 3 -> {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorted warriors by Defense:");
                    sortByDefense();
                    for (Warrior friendlyWarrior : friendlyWarriors) {
                        System.out.print(friendlyWarrior.toString());
                    }
                }
                case 4 -> {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Sorted warriors by Attack Priority:");
                    sortByAttackPriority();
                    for (Warrior friendlyWarrior : friendlyWarriors) {
                        System.out.print(friendlyWarrior.toString());
                    }
                }
                default -> sortingCase();
            }
        } else {
            System.out.println("No warriors!");
        }
    }

    public void orderOfWarriorsCase(){
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Enemies will be on the right side of your order.
                
                0. <---
                """);
        System.out.println(constructALineOfFriendlyWarriors() + "--->");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

    public void createYourself() {
       hireWarrior(new Hero(), WarriorType.HERO);
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Choose your favorite enemy, to set bonus ratio for your Hero!
                1. Assasin..
                2. Berserker..
                3. Guardian..
                4. HorseMan..
                5. PikeMan..
                6. SwordsMan..
                                
                """);
        Scanner in2 = new Scanner(System.in);
        String nextIntString2 = in2.nextLine();
        int choiceOfBonus = filter(nextIntString2);
        switch (choiceOfBonus) {
            case 2 -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new Berserker());
            case 3 -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new Guardian());
            case 4 -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new HorseMan());
            case 5 -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new PikeMan());
            case 6 -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new SwordsMan());
            default -> battleField
                    .getPlayer()
                    .getWarriors()
                    .get(battleField.getPlayer().getWarriors().size() - 1)
                    .getBonusRatio()
                    .setSuitableEnemy(new Assassin());
        }
    }

    public void hireWarrior(Warrior warrior, WarriorType warriorType) {
        WarriorFactory warriorFactory = new WarriorFactory();
        ArrayList<Warrior> warriorList;
        int price = warrior.getPrice();
        if(battleField.getPlayer().getRespect() >= price) {
            warriorList = battleField.getPlayer().getWarriors();
            warriorList.add(warriorFactory.createWarrior(warriorType));
            battleField.getPlayer().setWarriors(warriorList);
            battleField.getPlayer().setRespect(battleField.getPlayer().getRespect() - price);
        }
        else {
            System.out.println("\n" + "Not enough respect");
        }
    }

    public void battleScene() {
        if(battleField.getPlayer().getWarriors().size() != 0 && battleField.getEnemy().getWarriors().size() != 0) {
            System.out.println(battleField.getPlayer().getWarriors().get(0).getClass().getSimpleName() +
                    "\nLive length: " + battleField.getPlayer().getWarriors().get(0).getHealthPoints() +
                    "\nPower: " + battleField.getPlayer().getWarriors().get(0).getPower() +
                    "\nDefense: " + battleField.getPlayer().getWarriors().get(0).getDefense() +
                    "\nAttack priority: " + battleField.getPlayer().getWarriors().get(0).getAttackPriority() +
                    "\n" + "--------------------" +
                    "\n" + " " +viewOnDuelBattle() + " " +
                    "\n" + "--------------------" +
                    "\n" +
                    battleField.getEnemy().getWarriors().get(0).getClass().getSimpleName() +
                    "\nLive length: " + battleField.getEnemy().getWarriors().get(0).getHealthPoints() +
                    "\nPower: " + battleField.getEnemy().getWarriors().get(0).getPower() +
                    "\nDefense: " + battleField.getEnemy().getWarriors().get(0).getDefense() +
                    "\nAttack priority: " + battleField.getEnemy().getWarriors().get(0).getAttackPriority()
            );
        }
        if(battleField.getPlayer().getWarriors().size() == 0) {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    You lost!
                    Congratulations, more dead bodies will be decomposing here..
                    """);
            System.exit(0);
        }
        if(battleField.getEnemy().getWarriors().size() == 0) {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    You won!
                    Congratulations, now everyone can safely travel through the mountain pass!
                    """);
            System.exit(0);
        }
    }
    public String viewOnDuelBattle(){
        if(battleField.getEnemy().getWarriors().size() == 1) {
            return constructALineOfFriendlyWarriors() + " ~VS~ " + takeASignOfEnemyWarrior();
        }
        else {
            return constructALineOfFriendlyWarriors() + " ~VS~ " + takeASignOfEnemyWarrior() + "..";
        }
    }

    public String constructALineOfFriendlyWarriors() {
        StringBuilder lineOfFriendlyWarriors = new StringBuilder();
        if(battleField.getPlayer().getWarriors().size() != 0) {
            for (int i = battleField.getPlayer().getWarriors().size() - 1; i >= 0; i--) {
                lineOfFriendlyWarriors.append(battleField
                        .getPlayer()
                        .getWarriors()
                        .get(i)
                        .getClass()
                        .getSimpleName()
                        .charAt(0));
            }
        }
        return lineOfFriendlyWarriors.toString();
    }

    public void createEnemyCommander() {
        ArrayList<Warrior> enemy = new ArrayList<>();
        WarriorFactory warriorFactory = new WarriorFactory();
        enemy.add(warriorFactory.createWarrior(WarriorType.SWORDSMAN));
        enemy.add(warriorFactory.createWarrior(WarriorType.BERSERKER));
        enemy.add(warriorFactory.createWarrior(WarriorType.PIKEMAN));
        enemy.add(warriorFactory.createWarrior(WarriorType.BERSERKER));
        enemy.add(warriorFactory.createWarrior(WarriorType.PIKEMAN));
        enemy.add(warriorFactory.createWarrior(WarriorType.SWORDSMAN));
        battleField.getEnemy().setWarriors(enemy);
    }

    public String takeASignOfEnemyWarrior() {
        if(battleField.getEnemy().getWarriors().size() != 0)
            return String.valueOf(battleField
                    .getEnemy()
                    .getWarriors()
                    .get(0)
                    .getClass()
                    .getSimpleName()
                    .charAt(0));
        else
            return "No warriors!";
    }

    public int filter(String str) {
        int choice;
        if (str.equals("1")
                || str.equals("2")
                || str.equals("3")
                || str.equals("4")
                || str.equals("5")
                || str.equals("6")
                || str.equals("7")
                || str.equals("8")
                || str.equals("9")
                || str.equals("0")) {
            choice = Integer.parseInt(str);

        } else {
            choice = 0;
        }
        return choice;
    }

    public void sortByHealthPoints() {
        friendlyWarriors = battleField.getPlayer().getWarriors();
        friendlyWarriors.sort(Collections.reverseOrder(new WarriorHealthPointsComparator()));
    }

    public void sortByPower() {
        friendlyWarriors = battleField.getPlayer().getWarriors();
        friendlyWarriors.sort(Collections.reverseOrder(new WarriorPowerComparator()));
    }

    public void sortByDefense() {
        friendlyWarriors = battleField.getPlayer().getWarriors();
        friendlyWarriors.sort(Collections.reverseOrder(new WarriorDefenseComparator()));
    }
    public void sortByAttackPriority() {
        friendlyWarriors = battleField.getPlayer().getWarriors();
        friendlyWarriors.sort(Collections.reverseOrder(new WarriorAttackPriorityComparator()));
    }

    static class WarriorHealthPointsComparator implements Comparator<Warrior> {

        @Override
        public int compare(Warrior o1, Warrior o2) {
            return Double.compare(o1.getHealthPoints(), o2.getHealthPoints());
        }
    }
    static class WarriorPowerComparator implements Comparator<Warrior> {

        @Override
        public int compare(Warrior o1, Warrior o2) {
            return Integer.compare(o1.getPower(), o2.getPower());
        }
    }
    static class WarriorDefenseComparator implements Comparator<Warrior> {

        @Override
        public int compare(Warrior o1, Warrior o2) {
            return Integer.compare(o1.getDefense(), o2.getDefense());
        }
    }

    static class WarriorAttackPriorityComparator implements Comparator<Warrior> {

        @Override
        public int compare(Warrior o1, Warrior o2) {
            return Integer.compare(o1.getAttackPriority(), o2.getAttackPriority());
        }
    }
}
