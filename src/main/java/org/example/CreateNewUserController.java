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
    private ScreenManager screenManager;
    private String username, password;
    private boolean userExists;

    @FXML
    public void initialize(){
        implementation = CourseReviewImplementation.getInstance();
        screenManager = ScreenManager.getInstance();
        errorMessage.setText("");
        prompt.setText("Please enter the fields below: ");
        usernameText.setPromptText("Username: ");
        passwordText.setPromptText("Password: ");
        confirmPassword.setPromptText("Confirm Password: ");
        welcomePage.setText("Back to Welcome");
        createNewUser.setText("Create New User");
        createNewUser.setOnAction(e->{
            username = (usernameText.getText()).toString();
            password = (passwordText.getText()).toString();
            String confirmedPassword = confirmPassword.getText();
            if(!implementation.existingUser(username) && confirmedPassword.equals(password)) {
                implementation.createUser(username, password);
                implementation.login(username, password);
                resetFields();
                screenManager.switchScreen("main menu");
            } else if (implementation.existingUser(username)){
                errorMessage.setText("User already exists");
            }
            else if (!confirmedPassword.equals(password)) {
                errorMessage.setText("Passwords do not match!");
            }
        });
        welcomePage.setOnAction(e->{
            //go to welcome scene
            resetFields();
            screenManager.switchScreen("welcome");
        });


    }
    private void resetFields(){
        errorMessage.setText("");
        usernameText.setText("");
        passwordText.setText("");
        confirmPassword.setText("");
    }
}
