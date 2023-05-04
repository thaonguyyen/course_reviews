package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

public class LoginController {
    private CourseReviewImplementation implementation;
    private ScreenManager screenManager;
    private Student logginInStudent;

    @FXML
    private Label incorrectPassword, promptLogIn;
    @FXML
    private TextField usernameText, passwordText;
    @FXML
    private Scene loginScene;
    @FXML
    private Button logIn, backToWelcome;
    private boolean loggedin;
    private String username, password;
    @FXML
    public void initialize(){
        implementation = CourseReviewImplementation.getInstance();
        screenManager = screenManager.getInstance();
        incorrectPassword.setText("");
        promptLogIn.setText("Please log in below");
        usernameText.setPromptText("Username:");
        passwordText.setPromptText("Password:");
        logIn.setText("Login");
        backToWelcome.setText("Back to welcome");

        backToWelcome.setOnAction(e->{
            //go to welcome page
            //possibly move this somewhere else
            resetFields();
            screenManager.switchScreen("welcome");
        });
        logIn.setOnAction(e->{
            attemptLogIn();
        });
    }
    public void attemptLogIn(){

        username = (usernameText.getText()).toString();
        password = (passwordText.getText()).toString();
        if(implementation.existingUser(username) && implementation.login(username, password)){
            resetFields();
            screenManager.switchScreen("main menu");
        }
        else{
            failedLogIn();
        }
    }
    private void failedLogIn(){
        incorrectPassword.setText("Incorrect password. Please try again");
    }
    private void resetFields(){
        incorrectPassword.setText("");
        usernameText.setText("");
        passwordText.setText("");
    }

}
