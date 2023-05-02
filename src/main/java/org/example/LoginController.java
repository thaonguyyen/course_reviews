package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    private CourseReviewImplementation implementation;
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
        implementation = new CourseReviewImplementation();
        promptLogIn.setText("Please log in below");
        usernameText.setPromptText("Username:");
        passwordText.setPromptText("Password:");
        logIn.setText("Login");
        backToWelcome.setText("Back to welcome");

        backToWelcome.setOnAction(e->{
            //go to welcome page
            //possibly move this somewhere else
        });
    }
    public void attemptLogIn(){
        logIn.setOnAction(e->{
            username = (usernameText.getText()).toString();
            password = (passwordText.getText()).toString();
            loggedin = implementation.login(username, password);
        });
        if(!loggedin){
            failedLogIn();
        }
//        else if (loggedin){
//            implementation.
//        }
    }
    public void failedLogIn(){
        incorrectPassword.setText("Incorrect password. Please try again");
    }

}
