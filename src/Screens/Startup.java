package Screens;

import model.Board;

import java.util.Scanner;

public class Startup {
    public Startup(Board board) {
        Scanner input = new Scanner(System.in);
        String answer;
        Intro intro = new Intro();
        Rules rules = new Rules();
        intro.DisplayIntro();
        answer= input.nextLine();
        while(!answer.equalsIgnoreCase("n")){
            System.out.println("Invalid input");
            answer= input.nextLine();
        }
        System.out.println(rules);
        answer= input.nextLine();
        while(!answer.equalsIgnoreCase("n")){
            System.out.println("Invalid input");
            answer= input.nextLine();
        }
        board.DisplayBoard();
    }
}
