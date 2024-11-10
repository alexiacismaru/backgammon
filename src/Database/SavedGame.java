package Database;

import model.Board;
import model.Move;
import model.Position;
import model.Score;
import java.sql.*;

public class SavedGame {
    private User user;

    public SavedGame(User user) {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/SavedGames", "postgres","Kokoko@123");
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS INT_save_user(
                    username VARCHAR(20) CONSTRAINT pk_username PRIMARY KEY);
                    CREATE TABLE IF NOT EXISTS INT_save_position(
                    index NUMERIC,
                    number_of_pieces NUMERIC CONSTRAINT nn_number_of_pieces NOT NULL,
                    color_of_pieces CHAR(1) CONSTRAINT nn_color_of_pieces NOT NULL,
                    username VARCHAR(20) CONSTRAINT fk_INT_save_user_username REFERENCES INT_save_user (username) ON DELETE CASCADE
                    );
                    CREATE TABLE IF NOT EXISTS INT_save_move(
                    username VARCHAR(20) CONSTRAINT fk_INT_save_user_username REFERENCES INT_save_user (username) ON DELETE CASCADE,
                    first_value NUMERIC CONSTRAINT nn_first_value NOT NULL,
                    second_value NUMERIC CONSTRAINT nn_second_value NOT NULL,
                    number_of_moves NUMERIC CONSTRAINT nn_number_of_moves NOT NULL
                    );
                    CREATE TABLE IF NOT EXISTS INT_save_progress(
                    username VARCHAR(20) CONSTRAINT fk_INT_save_user_username REFERENCES INT_save_user (username) ON DELETE CASCADE,
                    time_spent NUMERIC CONSTRAINT nn_time_spent NOT NULL
                    );
                    CREATE TABLE IF NOT EXISTS INT_save_board(
                    username VARCHAR(20) CONSTRAINT fk_INT_save_user_username REFERENCES INT_save_user (username) ON DELETE CASCADE,
                    outside_black_pieces NUMERIC CONSTRAINT nn_outside_black_pieces NOT NULL,
                    outside_white_pieces NUMERIC CONSTRAINT nn_outside_black_pieces NOT NULL,
                    is_player_turn BOOLEAN CONSTRAINT nn_is_player_turn NOT NULL
                    );""");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.user=user;
    }

    private String savePosition(Position position, int index){//method that saves a position into a database
        return "INSERT INTO INT_save_position (username,index,number_of_pieces,color_of_pieces) VALUES ('"+user+"',"+index+","+position.getNumberOfPieces()+",'"+position.getColor()+"')";
    }

    private String saveMove(Move move){
        return "INSERT INTO INT_save_move (username,first_value,second_value,number_of_moves) VALUES ('"+user+"',"+move.getFirstAvailableValue()+","+move.getSecondAvailableValue()+",'"+ move.getNumberOfMoves()+"')";
    }

    private String saveBoard(Board board){
        return "INSERT INTO INT_save_board (username,outside_black_pieces,outside_white_pieces,is_player_turn) VALUES ('"+user+"',"+board.getOutsideBlack()+","+board.getOutsideWhite()+",'"+board.isPlayerTurn()+"')";
    }

    private String saveProgress(Score score){
        return "INSERT INTO INT_save_progress (username,time_spent) VALUES ('"+user+"',"+score.getScore()+")";
    }

    public void saveTheGame(Board board,Score score){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/SavedGames", "postgres","Kokoko@123");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM INT_save_user WHERE username = '"+user+"'");
            statement.executeUpdate("INSERT INTO INT_save_user (username) VALUES ('"+user+"')");
            statement.executeUpdate(saveBoard(board));
            statement.executeUpdate(saveMove(board.getMoves()));
            for (int i=0;i<board.getPositions().length;i++)
            {
                statement.executeUpdate(savePosition(board.getPosition(i),i));
            }
            statement.executeUpdate(saveProgress(score));
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String loadPosition(int index){
        return"SELECT * FROM INT_save_position WHERE username ='"+user+"' AND index = "+index+";";
    }

    private String loadMove(){
            return "SELECT * FROM INT_save_move WHERE username ='"+user+"';";
    }

    private String loadBoard(){
        return "SELECT * FROM INT_save_board WHERE username ='"+user+"';";
    }

    private String loadProgress(){
            return "SELECT * FROM INT_save_progress WHERE username ='"+user+"';";
    }

    public boolean loadTheGame(Board board,Score score) {
        if (doesSaveExist()){
            try {
                Connection connection =
                        DriverManager.getConnection("jdbc:postgresql://localhost:5432/SavedGames", "postgres","Kokoko@123");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(loadProgress());
                if (!resultSet.wasNull()) {
                    resultSet.next();
                    score.loadScore(resultSet.getLong("time_spent"));
                }
                resultSet = statement.executeQuery(loadMove());
                if (!resultSet.wasNull()) {
                    resultSet.next();
                    board.getMoves().setFirstAvailableValue(resultSet.getInt("first_value"));
                    board.getMoves().setSecondAvailableValue(resultSet.getInt("second_value"));
                    board.getMoves().setNumberOfMoves(resultSet.getInt("number_of_moves"));
                }
                resultSet = statement.executeQuery(loadBoard());
                if (!resultSet.wasNull()) {
                    resultSet.next();
                    board.setOutsideBlack(resultSet.getInt("outside_black_pieces"));
                    board.setOutsideWhite(resultSet.getInt("outside_white_pieces"));
                    board.setPlayerTurn(resultSet.getBoolean("is_player_turn"));
                }
                for (int i = 0; i < board.getPositions().length; i++) {
                    resultSet = statement.executeQuery(loadPosition(i));
                    if (!resultSet.wasNull()) {
                        resultSet.next();
                        board.getPosition(i).setNumberOfPieces(resultSet.getInt("number_of_pieces"));
                        board.getPosition(i).setColor(resultSet.getString("color_of_pieces"));
                    }
                }
                statement.close();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean doesSaveExist(){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/SavedGames", "postgres","Kokoko@123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM INT_save_user WHERE username ='"+user+"';");
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                statement.close();
                connection.close();
                return false;
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
