import Database.SavedGame;
import Database.User;
import Screens.LoserScreen;
import Screens.WinnerScreen;
import model.Board;
import Screens.Startup;
import model.Leaderboard;
import model.Score;
import java.util.Scanner;

public class BackgammonGame {

    static Board board = new Board();//set up the board
    static User user = new User();//Take username
    static SavedGame savedGame = new SavedGame(user);//setup database connection and tables
    static Startup startup = new Startup(board);//shows the intro and rules
    static Scanner input = new Scanner(System.in);//scanner to take input from user throughout the game
    static Score score = new Score();//timer the records how much time it took to finish the game
    static Leaderboard leaderboard = new Leaderboard();//setup database connection and table

    public static String takeInput(){//method that returns the user input as a string
        input = new Scanner(System.in);
        return input.nextLine();
    }

    public static boolean isInputInteger(String input){//method that checks that the input of the user is an integer
        for (int i=0;i<input.toCharArray().length;i++ ) {//loop goes through all the individual characters of the given string
            if (!(input.toCharArray()[i] >= 48 && input.toCharArray()[i] <= 57))//it checks if it's ascii value is between 48 and 57 or the character is a number
                return false;                                                   // ascii 48 = 0 and ascii 57 = 9
        }
        return !input.isEmpty();//checks if the string is empty
    }

    public static void navigationCommand(String input){//switch case scenarios that works based on the input of the user
        switch (input.toLowerCase()){
            case "q"://when the user enters q he is shown the intro and rules pages
                startup = new Startup(board);
                break;
            case "s"://when the user enters s the current game is saved
                savedGame.saveTheGame(board,score);
                System.out.println("Your game have been saved");
                break;
            case "l"://when the user enters l a previous game is loaded if there is one
                if (savedGame.loadTheGame(board,score))
                    board.DisplayBoard();
                else
                    System.out.println("No available save detected");
                break;
            case "p":
                leaderboard.displayLeaderboard(false);
                System.out.println("Enter p again to go back");
                while(!takeInput().equalsIgnoreCase("p")){
                    System.out.println("Invalid input !");
                }
                board.DisplayBoard();
                break;
            default:
                System.out.println("Invalid input !");
        }
    }

    public static void playTheGame(int start){//method that allows the player to make a move
        String to=takeInput();
        if (isInputInteger(to))
            board.makeMove(start, Integer.parseInt(to));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        String answer;
        while(true) {//loop to keep the game running even after finishing a round if the player wants to play another one
            System.out.println("Press enter to roll dice and assign the first turn, your die is the one on the left");
            score.startChronometer();
            while (true) {

                answer = takeInput();
                if (answer.equals("")) {
                    board.rollDice();
                    board.assignTurn();
                    break;
                } else
                    navigationCommand(answer);
            }

            while (true) {//this loop holds the play through of the game, here the runs keep happening until someone wins
                if (board.isPlayerTurn && !board.checkForAvailableMoves())//this checks if it's the players turn, and he still has moves
                    System.out.println("No moves available, press enter to continue");
                input.skip("");//this does nothing, there used to be a bug where you had to hit enter twice before the program does anything
                answer = takeInput();//take the user input and store it in variable answer
                if (board.isPlayerTurn) {//when it's the player's turn
                    if (board.checkForAvailableMoves()) {//if he can do a move
                        if (isInputInteger(answer)) {//if the user enters a number
                            playTheGame(Integer.parseInt(answer));//play the turn
                        } else {//if it's not a number
                            navigationCommand(answer);//do the chosen option
                        }
                    } else {//if the player does not have an available move
                        board.endCurrentTurn();//end his turn
                    }
                } else {//when it's the PC's turn
                    if (!answer.equals(""))//if the input is not just an enter
                        navigationCommand(answer);//do the chosen option
                    else {//if the input is just an enter
                        if (!board.checkForAvailableMoves()) {//if no moves can be done
                            if (board.getNumberOfMoves() != 0) {//check if it's because of the number of available moves left to declare it
                                board.DisplayBoard();
                                System.out.println("No more moves available ending PC turn, press enter to continue");
                                answer = takeInput();
                                if (answer.equals(""))
                                    board.endCurrentTurn();
                                else
                                    navigationCommand(answer);
                            } else
                                board.endCurrentTurn();
                        }
                    }
                }
                if (board.gameIsFinished()) {//when the game is finished
                    if (board.isPlayerTurn) {//if the player played the last round of the game then he won
                        WinnerScreen winnerScreen = new WinnerScreen();
                        score.stopChronometer();//stop the chronometer to get time
                        System.out.println(winnerScreen);//get the winner screen
                        score.getValue();//get the score
                        leaderboard.saveScore(user, score.getScore());//upload the score to the leaderboard
                    } else {//if the PC played the last turn then the player lost
                        LoserScreen loserScreen = new LoserScreen();
                        System.out.println(loserScreen);//get looser screen
                    }
                    break;
                }
            }
            System.out.println("Do you want to play again ?(y/n)");//ask if the player wants to play again
            answer = takeInput();
            while(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")){
                System.out.println("invalid input!");
                answer = takeInput();
            }
            if (answer.equalsIgnoreCase("n"))
                break;
            else {
                board = new Board();
                board.DisplayBoard();
            }
        }
    }
}