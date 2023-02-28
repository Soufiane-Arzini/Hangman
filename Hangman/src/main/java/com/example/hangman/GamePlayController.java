package com.example.hangman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {
    @FXML
    ImageView img;
    Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/2.png")));
    Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/3.png")));
    Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/4.png")));
    Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/5.png")));
    Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/6.png")));
    Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/7.png")));
    Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Pictures/8.png")));
    @FXML
    Label scoreP, usernameP;
    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;
    @FXML
    TextField tf6;
    @FXML
    TextField tf7;
    @FXML
    TextField tf8;
    @FXML
    TextField input;
    @FXML
    Label hint;
    @FXML
    Label letter_count;
    @FXML
    Label hint_label;
    @FXML
    Button Back;

    String[] data = {
            "java langage de programmation",
            "class poo",
            "public Access Modifiers",
            "static Access Modifiers",
            "void procedure",
            "main fonction principale",
            "string type de variable",
            "double type de variable",
            "boolean type de variable",
            "else condition",
            "elgamal cryptosystem",
            "while boucle ",
            "extends heritage",
            "switch condition",
            "scanner input",
            "break  stopper",
            "continue jump",
            "return methode",
            "import ajouter package",
            "package poo",
            "cesar cryptosystem",
            "catch erreur",
            "finally erreur",
            "throws erreur",
            "abstract poo",
            "private Access Modifiers",
            "const constante",
            "super heritage",
            "true boolean",
            "false boolean",
            "boucle for, while...",
            "methode fonction",
            "heritage poo",
            "print ecrire",
            "computer machine",
            "array table"
    };

    public void switchtohome() throws IOException {
        Navigator.switchTo("LoginCreate.fxml",Back);
    }
    int random = new Random().nextInt(data.length);
    String word_hint = data [random];
    String[] split = word_hint.split(" ", 2);
    String word = split[0];
    String hint_str = split[1];
    int letter_size = word.length();



    public void setHint(){
        hint.setText(hint_str);
        letter_count.setText(letter_size+" Letters");

        if(letter_size==7){
            tf8.setVisible(false);
        }
        if(letter_size==6){
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==5){
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
        if(letter_size==4){
            tf5.setVisible(false);
            tf6.setVisible(false);
            tf7.setVisible(false);
            tf8.setVisible(false);
        }
    }


    public void CheckInput() throws SQLException {
        String str = input.getText();
        if (word.contains(str)) {
            int index = 0;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (String.valueOf(c).equals(str)) {
                    setLetter(index, Character.toString(c),word.length());
                }
                index++;
            }
        }
        else {
            setImage();
        }
    }

    public void setLetter(int index,String str,int taille) throws SQLException {
        if(index==0)
            tf1.setText(str);
        else if(index==1)
            tf2.setText(str);
        else if(index==2)
            tf3.setText(str);
        else if(index==3)
            tf4.setText(str);
        else if(index==4)
            tf5.setText(str);
        else if(index==5)
            tf6.setText(str);
        else if(index==6)
            tf7.setText(str);
        else if(index==7)
            tf8.setText(str);
        int check=0;
        TextField [] table={tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8};
        for (TextField T : table){
            if (!Objects.equals(T.getText(), "") && check<=taille)
                check++;
            else
                break;

        }
        if (check==taille){

            String email=LoginController.getEmailText();
            img.setImage(image8);
            String[] sc=ScoreHandler.getStats(email);
            int id=Integer.parseInt(sc[0]);
            int newsc=Integer.parseInt(sc[1])+50;
            ScoreHandler.insertPlayerStats(id,newsc);
        }
    }


    int life=6;
    public void setImage(){
        if(life==6)
            img.setImage(image2);
        else if(life==5)
            img.setImage(image3);
        else if(life==4)
            img.setImage(image4);
        else if(life==3)
            img.setImage(image5);
        else if(life==2)
            img.setImage(image6);
        else if(life==1)
            img.setImage(image7);
        else if(life==0)
            img.setImage(image7);
        life--;
    }


    public void changeScene(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GamePlay.fxml")));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Hangman Game");
        window.setScene(new Scene(parent, 800, 650));
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHint();
        try {
            ScoreHandler.showStats(scoreP, usernameP);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}