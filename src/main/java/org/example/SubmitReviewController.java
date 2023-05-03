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
    private ScreenManager screenManager = ScreenManager.getInstance();

    @FXML
    public void initialize(){

        prompt.setText("Please fill out the following information:");
        implementation = CourseReviewImplementation.getInstance();
        screenManager = ScreenManager.getInstance();
        submit.setOnAction(e ->{
            try {
                String text = reviewText.getText();
                String courseString = courseText.getText();
                String department = courseString.split(" ")[0];
                String catalogNumber = courseString.split(" ")[1];
                Course c = new Course(department, catalogNumber);
                implementation.submitReview();
            }
        })
    }



}
