package com.example.hangman;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CreateController {
    @FXML
    private Button save,Back;
    @FXML
    private TextField firstName , lastName, email;
    @FXML
    private PasswordField password;


    public void switchtohome() throws IOException {
        Navigator.switchTo("LoginCreate.fxml",Back);
    }
    public void register() throws IOException {

        String userName = firstName.getText() + "-" + lastName.getText();
        String emailText = email.getText();
        String passwordText = password.getText();

        DataBase db = new DataBase();
        try {
            db.insertRecord(userName, emailText, passwordText);
            Navigator.switchTo("login.fxml",save);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
