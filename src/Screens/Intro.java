package Screens;

import model.Leaderboard;

public class Intro {
    Leaderboard leaderboard = new Leaderboard();
    public void DisplayIntro(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "Welcome to backgammon !\n" +
                "\n" +
                "Backgammon is one of the oldest games. It involves a board: 30 pawns, two dice, and two players.\n" +
                "In this case, the players are you and the computer.\n" +
                "\n" +
                "\n" +
                "\n" );
                leaderboard.displayLeaderboard(true);
                System.out.println("Press n for next page");
    }
}
