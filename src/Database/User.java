package Database;

import java.util.Scanner;

public class User {
    private String username;

    public User() {//when you creat a new User it automatically asks you to give a username
        System.out.print("Please enter Username between 3 to 20 characters : ");
        Scanner input = new Scanner(System.in);
        this.username=input.nextLine();
        while (this.username.length()<3 || this.username.length()>20){
            System.out.println("Invalid input!");
            this.username=input.nextLine();
        }
    }

    @Override
    public String toString() {
        return username;
    }
}
