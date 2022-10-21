import model.Commander;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Director director = new Director(
                new BattleField(
                        new Commander(50, 100),
                        new Commander(0, 500)));
        director.createEnemyCommander();
        director.startScene();
        Scanner input = new Scanner(System.in);
        input.nextLine();
        for(int i = 0; i < (int) Double.POSITIVE_INFINITY; i++) {
            director.townScene();
        }
    }
}
