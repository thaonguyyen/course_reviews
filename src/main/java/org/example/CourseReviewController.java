package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CourseReviewController {
    private CourseReviewImplementation implementation;

    @FXML
    private Label passwordsDontMatch;
    @FXML
    private TextField username, password;
    @FXML
    private Scene loginScene, mainMenuScene;
    @FXML
    private Button createNewUser, logIn;
    @FXML
    public void initialize(){

        implementation = new CourseReviewImplementation();
        username = new TextField("Username: ");
        password = new TextField("Password: ");
        createNewUser = new Button("Create New User");
        logIn = new Button("Login");
        //logInScene.setRoot()

    }
    private void passwordsDontMatch(){
        passwordsDontMatch.setText("Incorrect username or password. Please try again");
    }





}
