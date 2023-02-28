package com.example.hangman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {
    public static void switchTo(String path, Button b) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(path)));
        Stage window = (Stage) (b.getScene().getWindow());
        window.setScene(new Scene(root));
    }
}
