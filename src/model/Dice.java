package model;

import java.util.Random;

public class Dice {
    private int firstValue=0;
    private int secondValue=0;

    public Dice() {
    }

    public void rollDice(){
        Random number = new Random();
        this.firstValue = number.nextInt(6)+1;
        this.secondValue = number.nextInt(6)+1;
    }

    public int getFirstValue() {
        return firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }
}
