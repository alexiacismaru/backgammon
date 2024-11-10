package model;

public class Position {
    private String color=" ";
    private int numberOfPieces=0;

    public Position() {//Empty constructor to create a new position
    }

    public Position(String color, int qty) {//constructor to create a new position with specific color and number of pieces
        this.color=color;
        this.numberOfPieces=qty;
    }

    public String getColor() {
        return color;
    }//getter for the color

    public void setColor(String color) {
        this.color = color;
    }//setter for the color

    public void setNumberOfPieces(int quantity) {
        this.numberOfPieces = quantity;
    }//setter for the numberOfPieces

    public int getNumberOfPieces() {
        return numberOfPieces;
    }//getter for the numberOfPieces

    public void subPawn() {//methods that subtracts a piece from a position
        this.numberOfPieces--;//subtract numberOfPieces by 1
        if(numberOfPieces==0)//if the position doesn't have any more pieces in it, set it's color to neither black nor white
            this.color=" ";
    }

    public void addPawn() {
        this.numberOfPieces++;
    }//method that adds a piece to a position
}
