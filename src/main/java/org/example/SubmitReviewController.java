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
                String catalogNumberString = courseString.split(" ")[1];
                int catalogNumber = Integer.parseInt(catalogNumberString);
                Course c = new Course(department, catalogNumber);
                String ratingString = ratingText.getText();
                int rating = Integer.parseInt(ratingString);
                implementation.submitReview(c, text, rating);
                resetFields();
                screenManager.switchScreen("main menu");
            }catch(NumberFormatException n){
                //Display error message about course code or rating being incorrect
                errorMessage.setText("Please format your input as [Department Code] [Catalog Number] and make sure your score is an integer.");
            }catch(ArrayIndexOutOfBoundsException o){
                //Display error message about incorrect course format
                errorMessage.setText("Please format your input as [Department Code] [Catalog Number]");
            }
        });
        mainMenu.setOnAction(e -> {
            resetFields();
            screenManager.switchScreen("main menu");
        });
    }

    private void resetFields(){
        reviewText.setText("");
        ratingText.setText("");
        courseText.setText("");
        errorMessage.setText("");
    }



}
