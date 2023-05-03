package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WelcomeController {
    @FXML
    private Button logInToExistingUser, createNewUser;
    @FXML
    private Label welcome, prompt;
    private CourseReviewImplementation implementation;
    private ScreenManager screenManager;

    @FXML
    public void initialize(){
        implementation = CourseReviewImplementation.getInstance();
        screenManager = ScreenManager.getInstance();
        welcome.setText("Welcome!");
        prompt.setText("Please select an option below to get started");
        logInToExistingUser.setText("Loging-to an Existing User");
        createNewUser.setText("Create a New User");
        logInToExistingUser.setOnAction(e->{
            //switch to login scene
            screenManager.switchScreen("login");
        });
        createNewUser.setOnAction(e->{
            //switch to create new user scene
            screenManager.switchScreen("new user");
        });
    }
}
