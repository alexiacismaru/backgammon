package model;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Board {
private final Dice dice = new Dice();
private final Position[] positions=new Position[24];
private int outsideBlack=0;
private int outsideWhite=0;
private Move moves=new Move();
public boolean isPlayerTurn = true;

    public Board() {//the constructor for the board that sets up the pieces where they should be
        positions[0]=new Position("w",2);//w,2
        positions[1]=new Position();
        positions[2]=new Position();
        positions[3]=new Position();
        positions[4]=new Position();
        positions[5]=new Position("b",5);//b,5
        positions[6]=new Position();
        positions[7]=new Position("b",3);//b,3
        positions[8]=new Position();
        positions[9]=new Position();
        positions[10]=new Position();
        positions[11]=new Position("w",5);//w,5
        positions[12]=new Position("b",5);//b,5
        positions[13]=new Position();
        positions[14]=new Position();
        positions[15]=new Position();
        positions[16]=new Position("w",3);//w,3
        positions[17]=new Position();
        positions[18]=new Position("w",5);//w,5
        positions[19]=new Position();
        positions[20]=new Position();
        positions[21]=new Position();
        positions[22]=new Position();
        positions[23]=new Position("b",2);//b,2
    }

    public Position getPosition(int index) {//getter for position that returns a single position
        return positions[index];
    }

    public Position[] getPositions() {//getter por position that returns an array of positions
        return positions;
    }

    public Move getMoves() {//getter for moves
        return moves;
    }

    public int getOutsideBlack() {//getter for the number of black pieces in the middle of the board
        return outsideBlack;
    }

    public void setOutsideBlack(int outsideBlack) {//setter for the number of black pieces in the middle of the board
        this.outsideBlack = outsideBlack;
    }

    public int getOutsideWhite() {//getter for the number of white pieces in the middle of the board
        return outsideWhite;
    }

    public void setOutsideWhite(int outsideWhite) {//setter for the number of white pieces in the middle of the board
        this.outsideWhite = outsideWhite;
    }

    public boolean isPlayerTurn() {//getter for the variable isPlayerTurn
        return isPlayerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {//setter for the variable isPlayerTurn
        isPlayerTurn = playerTurn;
    }

    private String upSidePiece(int line, int i){//method to print topside pieces
        if (positions[i].getNumberOfPieces() >= line) {
            if (positions[i].getNumberOfPieces() >= (8+line))
                return positions[i].getColor()+positions[i].getColor();
            else
                return positions[i].getColor()+" ";
        } else
            return "  ";
    }

    private String downSidePiece(int line,int i){//method to print downside pieces
        if (positions[i].getNumberOfPieces() >= 17-line) {                   //when we start printing the first line we are actually at the 9th line
            if (positions[i].getNumberOfPieces() >= (17-line+8))             //so we have to build the stack backwards, first we check if the stack
                return positions[i].getColor()+positions[i].getColor();//reaches the line in question. Then we check if the second column is
            else                                                       //also filled up by comparing the same number plus 8.
                return positions[i].getColor()+" ";
        } else
            return "  ";//if the stack does reach the line level, it doesn't print pieces.
    }

    public void DisplayBoard() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("******************************************************************************");
        System.out.println("* 13    14    15    16    17    18*        * 19    20    21    22    23    24*");
        for (int line = 1; line < 9; line++) {
            if (line==4){
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*  b:%2d  * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        upSidePiece(line, 12), upSidePiece(line, 13), upSidePiece(line, 14), upSidePiece(line, 15), upSidePiece(line, 16), upSidePiece(line, 17)
                        ,outsideBlack, upSidePiece(line, 18), upSidePiece(line, 19), upSidePiece(line, 20), upSidePiece(line, 21), upSidePiece(line, 22), upSidePiece(line, 23));
            }
            else if (line==8) {
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*  Dice  * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        upSidePiece(line, 12), upSidePiece(line, 13), upSidePiece(line, 14), upSidePiece(line, 15), upSidePiece(line, 16), upSidePiece(line, 17)
                        , upSidePiece(line, 18), upSidePiece(line, 19), upSidePiece(line, 20), upSidePiece(line, 21), upSidePiece(line, 22), upSidePiece(line, 23));
            }
            else {
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*        * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        upSidePiece(line, 12), upSidePiece(line, 13), upSidePiece(line, 14), upSidePiece(line, 15), upSidePiece(line, 16), upSidePiece(line, 17)
                        , upSidePiece(line, 18), upSidePiece(line, 19), upSidePiece(line, 20), upSidePiece(line, 21), upSidePiece(line, 22), upSidePiece(line, 23));
            }
        }
        for (int line = 9; line < 17; line++) {
            if (line == 9) {
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*  %1d  %1d  * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        downSidePiece(line, 11), downSidePiece(line, 10), downSidePiece(line, 9), downSidePiece(line, 8), downSidePiece(line, 7), downSidePiece(line, 6)
                        , moves.getFirstAvailableValue(), moves.getSecondAvailableValue(), downSidePiece(line, 5), downSidePiece(line, 4), downSidePiece(line, 3), downSidePiece(line, 2), downSidePiece(line, 1), downSidePiece(line, 0));
            }
            else if (line == 13){
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*  w:%2d  * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        downSidePiece(line, 11), downSidePiece(line, 10), downSidePiece(line, 9), downSidePiece(line, 8), downSidePiece(line, 7), downSidePiece(line, 6)
                        , outsideWhite, downSidePiece(line, 5), downSidePiece(line, 4), downSidePiece(line, 3), downSidePiece(line, 2), downSidePiece(line, 1), downSidePiece(line, 0));
            }
            else {
                System.out.format("* %2s    %2s    %2s    %2s    %2s    %2s*        * %2s    %2s    %2s    %2s    %2s    %2s*\n",
                        downSidePiece(line, 11), downSidePiece(line, 10), downSidePiece(line, 9), downSidePiece(line, 8), downSidePiece(line, 7), downSidePiece(line, 6)
                        , downSidePiece(line, 5), downSidePiece(line, 4), downSidePiece(line, 3), downSidePiece(line, 2), downSidePiece(line, 1), downSidePiece(line, 0));
            }
        }
        System.out.println("""
                * 12    11    10    09    08    07*        * 06    05    04    03    02    01*
                ******************************************************************************
                q : main menu | s : save | l : load | p : leader board""");
        if (!isPlayerTurn)
            System.out.println("It is the PC's turn, press enter to continue");
    }

    private String getFriendlyColor(){//method that returns the color of the pieces based on the current turn
        if (isPlayerTurn)   //when it's the player's turn, this method returns b because the player owns the black pieces
            return "b";
        else                //when it's the PC's turn, this method returns w because the PC owns the white pieces
            return "w";
    }

    private void takeFromPosition(int index){//method that subtracts a piece from a position
        positions[index].subPawn();
    }

    private void stepOverPiece(){//method that sends a piece to the middle of the board
        if (isPlayerTurn)   //when it's the player's turn, a white piece is sent to the middle of the board
            outsideWhite++;
        else                //when it's the PC's turn, a black piece is sent to the middle of the board
            outsideBlack++;
    }

    private void reintroducePieceToBoard(){//method that subtracts a piece from the middle of the board
        if (isPlayerTurn)   //when it's the player's turn, a black piece is subtracted from the middle of the board
            outsideBlack--;
        else                ////when it's the PC's turn, a white piece is subtracted from the middle of the board
            outsideWhite--;
    }

    private void landOnPosition(int index){//method that adds a friendly piece to a position
        if (positionDoesNotContainEnemy(index)) //if the target position does not contain an opposing piece
            positions[index].addPawn();         //add a piece there
        else                                    //but if it does
            stepOverPiece();                    //send an opposing piece to the middle of the board
        positions[index].setColor(getFriendlyColor());  //set the color of the pieces on that position to a friendly color based on whose turn it is
    }

    public void displayPCTurn(int start,int finish){//method that states what the PC did during his turn
        if (start != 0 && finish != 0)  //when he did a normal move
            System.out.println("PC moved a piece from "+start+" to "+finish);
        else if (start == 0)            //when he's reintroducing a piece to the board
            System.out.println("PC reintroduced a piece to the board that landed on position "+finish);
        else                            //when he's taking a piece out of the board
            System.out.println("PC took a piece out of the board from position "+start);
    }

    public void makeMove(int start,int finish){//method that moves a piece on the board
        if (isMoveValid(start, finish)){//When a move is valid
            if (areTherePiecesInTheMiddle()){//when you introduce a piece into the board
                if (isPlayerTurn)                    //when it's the player's turn
                    moves.useMove(25 - finish);//the piece have to renter the board from the PC's home and
                                                    // therefore, the landing position will be between 18 and 24
                                                    //and have to be subtracted by 25 to know what die number was used to do the move
                else                                //when it's the PC's turn
                    moves.useMove(finish);          //the piece have to renter the board from the player's home and
                                                    //therefore, the landing position will be between 1 and 6
                                                    //and will be the die number used to do the move
                landOnPosition(finish - 1);//add a friendly piece to the landing position
                reintroducePieceToBoard();//subtract a friendly piece from middle of the table
                displayMessage(start, finish);//give feedback on the PC's turn
            }
            else if (finish == 0){//when you take a piece out of the board
                if (isPlayerTurn)//when it's the player's turn
                    moves.useMove(start);//the starting position is between 1 and 6 so the starting position is the same as the value of the die used
                else            //when it's the PC's turn
                    moves.useMove(25 - start);//the starting position is between 18 and 24, so to determine the value of the die used it have to be subtracted by 25
                takeFromPosition(start - 1);//subtract a piece from the starting position
                displayMessage(start, finish);//give feedback on the PC's turn
            }
            else {//if it's a normal move
                moves.useMove(Math.abs(start-finish));//I used Math.abs to always get a positive value
                                                      // since the PC's moves counter clock wise and the
                                                      //finishing position is bigger than the starting position
                takeFromPosition(start-1);//subtract a piece from the starting position
                landOnPosition(finish-1);//add a friendly piece to the landing position
                displayMessage(start, finish);//give feedback on the PC's turn
            }
        }
    }

    private void displayMessage(int start, int finish){//method that gives feedback on what happened during the PC's turn
        DisplayBoard();
        if (!isPlayerTurn)
            displayPCTurn(start,finish);
        if (moves.getNumberOfMoves()==0){
            if (!isPlayerTurn)
                System.out.println("Ending PC turn");
        }
    }

    public int getNumberOfMoves(){//getter for the current number of moves left
        return moves.getNumberOfMoves();
    }

    private boolean areAllPawnsHome(){//method that determines if all the pieces of the current turn holder are in his home
        if (areTherePiecesInTheMiddle())//if there are friendly pieces in the middle of the board
            return false;
        if (isPlayerTurn) {//when it's the player's turn the method checks if there are friendly pieces outside the player's home
            for (int i = 6; i < positions.length; i++) {//loop to go through every position outside the player's home, starting from position 7 to 24 on the board
                if (positions[i].getColor().equals(getFriendlyColor()))//if it detects any position holding friendly pieces
                    return false;
            }
        }
        else {//when it's the PC's turn the method checks if there are friendly pieces outside the PC's home
            for (int i = 17; i >= 0; i--) {//loop to go through every position outside the PC's, starting from position 18 to 1 on the board
                if (positions[i].getColor().equals(getFriendlyColor()))//if it detects any position holding friendly pieces
                    return false;
            }
        }
        return true;//if it didn't detect any friendly pieces
    }

    private boolean positionHasEnemyStack(int index){//function that determines if a position has an enemy stack on it based on whose turn it is
        return !positions[index].getColor().equals(getFriendlyColor()) && positions[index].getNumberOfPieces() > 1;
        // if the position is not friendly, and it holds more than 1 piece
    }

    private boolean areTherePiecesInTheMiddle(){//methods that checks if there are pieces in the middle depending on whose turn it is
        if (isPlayerTurn && outsideBlack != 0)
            return true;
        else if (!isPlayerTurn && outsideWhite != 0)
            return true;
        else
            return false;
    }

    private boolean doesMoveExist(int distance){//method that checks if the distance attempted is available
        if (areAllPawnsHome()){//when all the pieces are home, a distance smaller than the available die values is accepted because a piece can be taken out of the board using a move bigger than what is needed
            if (!(Math.abs(distance) <= moves.getFirstAvailableValue() || Math.abs(distance) <= moves.getSecondAvailableValue())) {//if you try to make a move bigger than the dice values
                return false;
            }
        }
        else {//when all the pieces are all home, only distances that are equal to one of the dice values are accepted
            if (!(Math.abs(distance) == moves.getFirstAvailableValue() || Math.abs(distance) == moves.getSecondAvailableValue())){
                return false;
            }
        }
        return true;//if no errors where found return true
    }

    private boolean isSelectedPositionFriendly(int index){//method that checks of a position is friendly or not depending on the current turn
        if (!positions[index].getColor().equals(getFriendlyColor())) //if the selected position is not friendly
            return false;
        else
            return true;
    }

    private boolean positionDoesNotContainEnemy(int index){//method that checks if a position does not contain enemy pieces depending on whose turn it is
        if (positions[index].getColor().equals(getFriendlyColor()) || positions[index].getColor().equals(" "))
            return true;
        return false;
    }

    private boolean isExactMoveAvailable(boolean shouldMakeMove){//method that checks if the current turn holder can make an exact move to take a piece out of the board
        if (!areAllPawnsHome())//if there are pieces still outside the home
            return false;
        if (isPlayerTurn) {//when it's the payers turn, the program checks if the positions equal to the dice values holds friendly pieces
            if (moves.getFirstAvailableValue() != 0){
                if (isSelectedPositionFriendly(moves.getFirstAvailableValue() - 1)) //if there are exact moves available, and you are attempting it
                    return true;
            }
            if (moves.getSecondAvailableValue() != 0){
                if (isSelectedPositionFriendly(moves.getSecondAvailableValue() - 1)) //if there are exact moves available, and you are attempting it
                    return true;
            }
        }
        else {//when it's the PC's turn, the program checks if the positions equal to 25 minus the dice values holds friendly pieces
            if (moves.getFirstAvailableValue() != 0){
                if (isSelectedPositionFriendly(24 - moves.getFirstAvailableValue())) { //if there are exact moves available, and you are attempting it
                    if (shouldMakeMove)
                        makeMove(25 - moves.getFirstAvailableValue(),0);
                    return true;
                }
            }
            if (moves.getSecondAvailableValue() != 0){
                if (isSelectedPositionFriendly(24 - moves.getSecondAvailableValue())) { //if there are exact moves available, and you are attempting it
                    if (shouldMakeMove)
                        makeMove(25 - moves.getSecondAvailableValue(),0);
                    return true;
                }
            }
        }
        return false;//if it didn't find anything
    }

    private boolean isAvailableExactMoveUsed(int start,int finish){//method that checks if the current turn holder have an exact move available and if he's using it
        if (isPlayerTurn) {
            if (moves.getFirstAvailableValue() != 0 && moves.getSecondAvailableValue() != 0){
                if (((isSelectedPositionFriendly(moves.getFirstAvailableValue() - 1) && moves.getFirstAvailableValue() == start) || (isSelectedPositionFriendly(moves.getSecondAvailableValue() - 1) && moves.getSecondAvailableValue() == start)) && finish == 0)
                    return true;
            }
            else {
                if (moves.getFirstAvailableValue() != 0) {
                    if (isSelectedPositionFriendly(moves.getFirstAvailableValue() - 1) && (moves.getFirstAvailableValue() == start) && finish == 0) //if there are exact moves available, and you are attempting it
                        return true;
                }
                if (moves.getSecondAvailableValue() != 0) {
                    if (isSelectedPositionFriendly(moves.getSecondAvailableValue() - 1) && (moves.getSecondAvailableValue() == start) && finish == 0) //if there are exact moves available, and you are attempting it
                        return true;
                }
            }
        }
        else {//when it's the PC's turn
            if (moves.getFirstAvailableValue() != 0 && moves.getSecondAvailableValue() != 0) {
                if (((isSelectedPositionFriendly(24 - moves.getFirstAvailableValue()) && 25 - moves.getFirstAvailableValue() == start) || (isSelectedPositionFriendly(24 - moves.getSecondAvailableValue()) && 25 - moves.getSecondAvailableValue() == start)) && finish == 0)
                    return true;
            } else {
                if (moves.getFirstAvailableValue() != 0) {
                    if (isSelectedPositionFriendly(24 - moves.getFirstAvailableValue()) && (25 - moves.getFirstAvailableValue() == start) && finish == 0) //if there are exact moves available, and you are attempting it
                        return true;
                }
                if (moves.getSecondAvailableValue() != 0) {
                    if (isSelectedPositionFriendly(24 - moves.getSecondAvailableValue()) && (25 - moves.getSecondAvailableValue() == start) && finish == 0) //if there are exact moves available, and you are attempting it
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isMoveValid(int start,int finish){//this method makes sure the rules of the game are followed, here only the player will have error messages displayed, so he understands what he's doing wrong
        if (start == finish || start > 24 || start < 0 || finish > 24 || finish < 0){//checking for illegal or out of bound moves
            System.out.println("Invalid input");
            return false;
        }
        if (areTherePiecesInTheMiddle()) {//when there are pieces in the middle
            if (start != 0) {//if you don't select a piece from the middle of  the board
                if (isPlayerTurn)
                    System.out.println("You have to reintroduce pieces from the middle of the board first");
                return false;
            }
            if (positionHasEnemyStack(finish-1)) {//if the landing position holds an enemy stack
                if (isPlayerTurn)
                    System.out.println("You can't land on an enemy stack");
                return false;
            }
            else
                return true;
        }//when there are no pieces in the middle
        if (start ==0){//when you try to select a piece from the middle of the board
            if (isPlayerTurn)
                System.out.println("There are no pieces in the middle of the board");
            return false;
        }
        if (!isSelectedPositionFriendly(start-1)) {//if the selected position is not friendly
            if (isPlayerTurn)
                System.out.println("You can only move your own pieces");
            return false;
        }
        if (isExactMoveAvailable(false) && finish != 0) {//if there is an exact move available and the turn holder is not taking a piece out of the board
            if (isPlayerTurn)
                System.out.println("There is an exact move available, you have to do it first!");
            return false;
        }
        if (finish == 0){//when taking a piece out of the board is attempted
            if (!areAllPawnsHome()) {//if the pieces are not all home
                if (isPlayerTurn)
                    System.out.println("You can't take out a piece if your pieces are not all home!");
                return false;
            }
            else{//when all the pieces are home
                if (isExactMoveAvailable(false)){//check if there is an exact move available
                    if (isAvailableExactMoveUsed(start, finish)){//check if available exact move is used
                        return true;
                    }
                    else {
                        if (isPlayerTurn)
                            System.out.println("There is an exact move available, you have to do it first!");
                        return false;
                    }
                }//PS: I know that the "isAvailableExactMoveUsed" method does the same thing as "isExactMoveAvailable" but if there are no exact moves it will also return false, so you have to check if there are exact moves available before using it
                if (areTherePiecesFurtherBack()) {//check if there are pieces further back in your home because if there are no exact moves you have to move the pieces that are the furthest back
                    if (isPlayerTurn)
                        System.out.println("You can't take a piece out of the board if there are no exact moves and there are pieces you can move further back.");
                    return false;
                }
                if (isPieceWithinReach(start)){//check if piece the distance travelled by the piece is within the dice value's range
                    return true;
                }
            }
        }
        else {//when a normal move is attempted
            if (positionHasEnemyStack(finish-1)) {//if the target position contains an enemy stack
                if (isPlayerTurn)
                    System.out.println("You can't land on an enemy stack!");
                return false;
            }
        }
        if (!doesMoveExist(start - finish)) {//if the move does not exist
            if (isPlayerTurn)
                System.out.println("Attempted move doesn't exist");
            return false;
        }
        return true;//if no errors where detected
    }

    private boolean isPieceWithinReach(int start){//check if a piece can be taken out of the board using the available dice values
        if (isPlayerTurn){
            if (moves.getFirstAvailableValue() != 0 && start < moves.getFirstAvailableValue())
                return true;
            if (moves.getSecondAvailableValue() != 0 && start < moves.getSecondAvailableValue())
                return true;
        }
        else {
            if (moves.getFirstAvailableValue() != 0 && start > 25 - moves.getFirstAvailableValue())
                return true;
            if (moves.getSecondAvailableValue() != 0 && start > 25 - moves.getSecondAvailableValue())
                return true;
        }
        return false;
    }

    public void rollDice(){//method that generates two random numbers between 1 and 6 and assigns them to moves
        dice.rollDice();
        moves=new Move(dice.getFirstValue(), dice.getSecondValue());
    }

    public void endCurrentTurn(){//method that ends the current turn and starts then other one
        if (isPlayerTurn)
            isPlayerTurn = false;
        else
            isPlayerTurn = true;
        rollDice();
        DisplayBoard();
    }

    private boolean noMoreMovesAvailable(){//method that checks if all the moves have been used up
        if (moves.getFirstAvailableValue() == 0 && moves.getSecondAvailableValue() == 0)
            return true;
        else
            return false;
    }

    private boolean canYouReintroducePieceToBoard(){//method to check if you can enter a piece from the middle of the board back into the board and tells the PC to do that move when it's his turn
        if (isPlayerTurn){//when it's the player's turn
            if (moves.getFirstAvailableValue() != 0 && !positionHasEnemyStack(24- moves.getFirstAvailableValue()))//if only the first move is available
                return true;
            if(moves.getSecondAvailableValue() != 0 && !positionHasEnemyStack(24- moves.getSecondAvailableValue()))
                return true;
        }
        else {//when it's the PC's turn
            if (moves.getFirstAvailableValue() != 0 && !positionHasEnemyStack(moves.getFirstAvailableValue() - 1)) {//if only the first move is available
                makeMove(0, moves.getFirstAvailableValue());
                return true;
            }
            if(moves.getSecondAvailableValue() != 0 && !positionHasEnemyStack(moves.getSecondAvailableValue() - 1)) {
                makeMove(0, moves.getSecondAvailableValue());
                return true;
            }
        }
        return false;
    }

    private boolean areTherePiecesFurtherBack(){//method that checks a normal move can be made after all the pieces got inside their home
        int reach = Math.max(moves.getFirstAvailableValue(),moves.getSecondAvailableValue());
        if (isPlayerTurn){
            for (int i = reach;i<7;i++){
                if (isSelectedPositionFriendly(i-1))
                    return true;
            }
        }
        else {
            for (int i = 25 - reach;i>18;i--){
                if (isSelectedPositionFriendly(i-1))
                    return true;
            }
        }
        return false;
    }

    private Boolean canPieceBeTakenOut(){//method that checks if a friendly piece can be taken out of the board
        if (isExactMoveAvailable(true)) {//if there is an exact move available
            return true;
        }
        if (areTherePiecesFurtherBack())//if you can make a normal move
            return false;
        else //if there are exact moves and no normal moves can be made you have to take a piece out
            return true;
    }

    private boolean canNormalMoveBeMade(){//method that checks if a normal move can be made over the whole board
        for (int i=0 ; i<24 ; i++){
            if (isPlayerTurn){//when it's the player's turn
                if (moves.getFirstAvailableValue() != 0 && (i+1-moves.getFirstAvailableValue() > 0) && isMoveValid(i+1,i+1-moves.getFirstAvailableValue()))
                    return true;
                if (moves.getSecondAvailableValue() != 0 && (i+1-moves.getSecondAvailableValue() > 0) && isMoveValid(i+1,i+1-moves.getSecondAvailableValue()))
                    return true;
            }
            else{//when it's the PC's turn
                if (moves.getFirstAvailableValue() != 0 && (i+1+moves.getFirstAvailableValue() < 25) && isMoveValid(i+1,i+1+moves.getFirstAvailableValue())) {
                    makeMove(i+1,i+1+moves.getFirstAvailableValue());
                    return true;
                }
                if (moves.getSecondAvailableValue() != 0 && (i+1+moves.getSecondAvailableValue() < 25) && isMoveValid(i+1,i+1+moves.getSecondAvailableValue())) {
                    makeMove(i+1,i+1+moves.getSecondAvailableValue());
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean checkForAvailableMoves(){//method that checks if any move can be made to determine if the turn should be ended and if there is it lets the PC do its turn
        PrintStream originalStream = System.out;                                                            //this piece of code makes sure no messages will be
        if (isPlayerTurn){                                                                                  //be displayed on the console while checking for available moves
            PrintStream dummyStream = new PrintStream(new OutputStream(){public void write(int b){}});      //when it's the player's turn
            System.setOut(dummyStream);                                                                     //
        }                                                                                                   //
        if (noMoreMovesAvailable()) {//end turn if there are no moves available
            System.setOut(originalStream);
            return false;
        }
        if (areTherePiecesInTheMiddle()) {//when there are pieces in the middle
            if (canYouReintroducePieceToBoard()) {//if a piece can be reintroduced to the board
                System.setOut(originalStream);
                return true;
            }
            System.setOut(originalStream);
            return false;
        }
        else {//when there are no pieces in the middle
            if (areAllPawnsHome()){//if all the pieces are home
                if (canPieceBeTakenOut()) {//if a piece can be taken out of the board
                    System.setOut(originalStream);
                    return true;
                }
            }
            if (canNormalMoveBeMade()) {//check if a normal move can be made anywhere on the board
                System.setOut(originalStream);
                return true;
            }
        }
        System.setOut(originalStream);
        return false;//if no possible move was detected
    }

    public void assignTurn(){//method to determine the starting turn
        Scanner input = new Scanner(System.in);
        String answer;
        while (dice.getFirstValue() == dice.getSecondValue()){//if we get a pair
            DisplayBoard();
            System.out.println("It's a tie ! Press enter to roll again");
            answer = input.nextLine();
            rollDice();
        }
        if (dice.getFirstValue()> dice.getSecondValue()){//if the first die is bigger than the second
            DisplayBoard();
            System.out.println("The player gets the first turn !");
            isPlayerTurn=true;
        }
        else {//if the second die is bigger than the first
            DisplayBoard();
            System.out.println("The PC gets the first turn !");
            isPlayerTurn=false;
        }
    }

    public boolean gameIsFinished () {//method that checks if the game is finished
        if (areTherePiecesInTheMiddle())
            return false;
        for (int i = 0; i < 24; i++) {
            if (isSelectedPositionFriendly(i))
                return false;
        }
        return true;
    }
}
