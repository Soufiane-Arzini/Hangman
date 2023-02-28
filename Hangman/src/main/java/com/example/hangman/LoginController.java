package com.example.hangman;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    @FXML
    private Button login,Back;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    private static String emailText, passwordText;

    public void switchtohome() throws IOException {
        Navigator.switchTo("LoginCreate.fxml",Back);
    }

    public void signIn() throws IOException{
        emailText = email.getText();
        passwordText = password.getText();
        DataBase db = new DataBase();
        boolean drapeau = db.validate(emailText, passwordText);
        if (drapeau) Navigator.switchTo("GamePlay.fxml", login);

    }
    public static String getEmailText() {
        return emailText;
    }
}
