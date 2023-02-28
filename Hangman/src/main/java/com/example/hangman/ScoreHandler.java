package com.example.hangman;

import javafx.scene.control.Label;

import java.sql.*;

import static com.example.hangman.DataBase.printSQLException;

public class ScoreHandler {
    private static final String PlAYER_QUERY = "update users set score = ? where id = ?;";
    private static final String ID_QUERY = "select * from users where email = ?;";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pendu?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "irving11";

    public static void insertPlayerStats(int id, int score){

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(PlAYER_QUERY)
        ) {
            preparedStatement.setString(1, String.valueOf(score));

            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }




    public static String[] getStats(String email) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(ID_QUERY);
        preparedStatement.setString(1, email);

        ResultSet response = preparedStatement.executeQuery();

        response.next();
        String id = response.getString(5);
        String score = response.getString(4);


        String username = response.getString(1);
        return new String[]{id, score, username};
    }


    public static void showStats(Label sp, Label up) throws SQLException {
        String[] stats = getStats(LoginController.getEmailText());
        int score = Integer.parseInt(stats[1]);

        String username = stats[2];
        sp.setText(String.valueOf(score));

        up.setText(username);
    }


}
