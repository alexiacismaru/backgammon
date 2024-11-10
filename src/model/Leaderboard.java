package model;

import Database.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;
import java.sql.SQLException;

public class Leaderboard {


    public Leaderboard() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/Leaderboard", "postgres","Kokoko@123");
            Statement statement = connection.createStatement();
            statement.executeUpdate(""" 
                    CREATE TABLE IF NOT EXISTS Leaderboard(
                    username VARCHAR(20),
                    score NUMERIC,
                    date TIMESTAMP
                    );
                    """);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveScore(User user, long score){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/Leaderboard", "postgres","Kokoko@123");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO LEADERBOARD (username,score,date) VALUES ('"+user+"',"+score+",'"+formatter.format(date)+"')");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayLeaderboard(boolean forIntro){
        try {
            Connection connection =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/Leaderboard",
                            "postgres", "Kokoko@123"); //the url that connect the database to the java code
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("""
                SELECT username, MIN(score), date
                from LEADERBOARD
                GROUP BY username,date
                ORDER BY 2 LIMIT 5
            """);
            if (!forIntro)
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("************************************************************");
            System.out.println("*                        LEADERBOARD                       *");
            System.out.println("************************************************************");
            Score score = new Score();
            while(result.next()){
                String username = result.getString(1);
                long time = result.getLong(2);
                String date = result.getString(3);
                System.out.printf("* %-20s       %6s     %s*\n", username,score.getTimeValue(time),date);
            }
            System.out.println("************************************************************");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}