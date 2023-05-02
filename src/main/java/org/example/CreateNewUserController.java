package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewUserController {
    @FXML
    private Label prompt, errorMessage;
    @FXML
    private TextField usernameText, passwordText, confirmPassword;
    @FXML
    private Button createNewUser, welcomePage;
    private CourseReviewImplementation implementation;
    private String username, password;
    private boolean userExists;

    @FXML
    public void initialize(){
        prompt.setText("Please enter the fields below: ");
        usernameText.setPromptText("Username: ");
        passwordText.setPromptText("Password: ");
        confirmPassword.setPromptText("Confirm Password: ");
        welcomePage.setText("Back to Welcome");
        createNewUser.setText("Create New User");
        createNewUser.setOnAction(e->{
            username = (usernameText.getText()).toString();
            password = (passwordText.getText()).toString();
            checkUserExists();
            if(!userExists) {
                implementation.createUser(username, password);
            }
        });
        welcomePage.setOnAction(e->{
            //go to welcome scene
        });
    }

    public void checkUserExists(){
        userExists = implementation.existingUser(username);
    }
}
