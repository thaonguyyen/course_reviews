package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SubmitReviewController {
    @FXML
    private Label prompt, errorMessage, coursePrompt, reviewPrompt, ratingPrompt;
    @FXML
    private Button mainMenu, submit;
    @FXML
    private TextField courseText, ratingText;
    @FXML
    private TextArea reviewText;
    private CourseReviewImplementation implementation;

    @FXML
    public void initialize(){
        prompt.setText("Please fill out the following information:");
    }

}
