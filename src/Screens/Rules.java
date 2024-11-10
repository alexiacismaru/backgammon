package Screens;

public class Rules {
    @Override
    public String toString() {
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                "Rules \n" +
                "\n" +
                "1. move your pieces counter clockwise from the upper right by entering the starting position's number followed by the\n" +
                "finishing position's number, while your opponent's move clockwise from the bottom right \n" +
                "\n" +
                "2. the game starts with both players each rolling a single die to determine who goes first. The one with the higher \n" +
                "roll starts and  uses the dice to make his first move. \n" +
                "\n" +
                "3. each turn, a player rolls the dice. The numbers shown decide how far the player may move their pieces. \n" +
                "\n" +
                "4. if the same number is rolled on both dice, the player gets 4 moves. \n" +
                "\n" +
                "5. as you move a piece, you may move onto a position that contains no opposing stacks. \n" +
                "\n" +
                "6. if you move a piece onto an opposing piece, that piece gets removed and placed onto the middle of the board. That piece's\n" +
                "owner must then return it back onto the board, starting from the beginning. They may not perform any other moves until all \n" +
                "their  pieces are back on the board. \n" +
                "\n" +
                "7. once all your pieces have made it around the board and into your home (the first 6 position from the right side facing you),\n" +
                " you may start removing pieces. A piece can bear off if:  \n" +
                "you roll a number that corresponds exactly to how far the piece would have to travel to exit the board or an  \n" +
                "unused die has a higher value than is required to bear any piece off. In which case, you may bear off the piece that's\n" +
                "furthest from the end. \n" +
                "\n" +
                "8. the player who manages to bear off all their pieces first is the winner.\n" +
                "\n" +
                "9. to take a piece out of the board the finishing position must be 0 and to reintroduce a piece back into the board the starting\n" +
                "position must be 0.\n" +
                "\n" +
                "Press n to start playing";
    }
}
