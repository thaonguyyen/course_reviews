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
        errorMessage.setText("");
        implementation = CourseReviewImplementation.getInstance();
        screenManager = ScreenManager.getInstance();
        submit.setOnAction(e ->{
            try {
                errorMessage.setText("");
                String text = reviewText.getText();
                String courseString = courseText.getText();
                String department = courseString.split(" ")[0];
                String catalogNumberString = courseString.split(" ")[1];
                int catalogNumber = Integer.parseInt(catalogNumberString);
                Course c = new Course(department, catalogNumber);
                String ratingString = ratingText.getText();
                int rating = Integer.parseInt(ratingString);
                if(!implementation.checkReviewAlreadyExists(c) && rating >= 1 && rating <= 5) {
                    implementation.submitReview(c, text, rating);
                    resetFields();
                    screenManager.switchScreen("main menu");
                }
                else if(rating < 1 || rating > 5){
                    errorMessage.setText("Invalid rating, must be between 1 and 5.");
                }
                else{
                    errorMessage.setText("You cannot submit a review for a course you already reviewed!");
                }

            }catch(NumberFormatException n){
                //Display error message about course code or rating being incorrect
                errorMessage.setText("Please format your input as [Department Code] [Catalog Number] and make sure your rating is an integer.");
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
