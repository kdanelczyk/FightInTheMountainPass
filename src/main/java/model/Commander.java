package model;

import model.warrior.Warrior;

import java.util.ArrayList;

public class Commander {

    private int respect;
    private double gold;
    private ArrayList<Warrior> warriors;

    public Commander() {

    }

    public Commander(int gold, int respect) {
        this.respect = respect;
        this.gold = gold;
        this.warriors = new ArrayList<>();
    }

    public int getRespect() {
        return respect;
    }

    public void setRespect(int respect) {
        this.respect = respect;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getGold() {
        return gold;
    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }

    public void setWarriors(ArrayList<Warrior> warriors) {
        this.warriors = warriors;
    }

    @Override
    public String toString() {
        return "Stranger: \n" +
                "your gold: " + gold +
                ", \n your respect: " + getRespect() + "\n";
    }
}


