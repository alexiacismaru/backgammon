package model;

public class Move {
    private int numberOfMoves=0;
    private int firstAvailableValue=0;
    private int secondAvailableValue=0;

    public Move() {
    }

    public Move(int firstDie, int secondDie) {
        if(firstDie != secondDie){
            this.numberOfMoves=2;
            this.firstAvailableValue=firstDie;
            this.secondAvailableValue=secondDie;
        }
        else {
            this.numberOfMoves=4;
            this.firstAvailableValue=firstDie;
            this.secondAvailableValue=firstDie;
        }
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getFirstAvailableValue() {
        return firstAvailableValue;
    }

    public int getSecondAvailableValue() {
        return secondAvailableValue;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public void setFirstAvailableValue(int firstAvailableValue) {
        this.firstAvailableValue = firstAvailableValue;
    }

    public void setSecondAvailableValue(int secondAvailableValue) {
        this.secondAvailableValue = secondAvailableValue;
    }

    public boolean areBothMovesAvailable(){
        return firstAvailableValue != 0 && secondAvailableValue != 0;
    }

    public void useMove(int value) {
        numberOfMoves--;
        if (firstAvailableValue != secondAvailableValue){ //if the values are different
            if (value == firstAvailableValue) {
                firstAvailableValue = 0;
            }
            else if (value == secondAvailableValue) {
                secondAvailableValue = 0;
            }
            else if (areBothMovesAvailable()){
                if (firstAvailableValue < secondAvailableValue){
                    if (value < firstAvailableValue)
                        firstAvailableValue = 0;
                    else
                        secondAvailableValue = 0;
                }
                else if (secondAvailableValue < firstAvailableValue){
                    if (value < secondAvailableValue)
                        secondAvailableValue = 0;
                    else
                        firstAvailableValue = 0;
                }
            }
            else {
                if (firstAvailableValue == 0)
                    secondAvailableValue = 0;
                else
                    firstAvailableValue = 0;
            }
        }
        else { //if it's a pair
            if (value <= firstAvailableValue) {
                if (numberOfMoves == 1)
                    secondAvailableValue=0;
                if (numberOfMoves == 0)
                    firstAvailableValue = 0;
            }
        }
    }
}
