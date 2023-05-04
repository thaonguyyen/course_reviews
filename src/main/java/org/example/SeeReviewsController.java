package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SeeReviewsController {

    @FXML
    public Button mainMenuButton, enterCourseButton;
    @FXML
    public TextField enterCourseTextField;
    @FXML
    public Label errorMessage, reviews, avgRatingLabel;

    private ScreenManager screenManager;
    private CourseReviewImplementation implementation;

    @FXML
    public void initialize(){
        implementation = CourseReviewImplementation.getInstance();
        screenManager = ScreenManager.getInstance();
        avgRatingLabel.setText("");
        reviews.setText("");
        errorMessage.setText("");
        mainMenuButton.setOnAction(e -> {
            resetFields();
            screenManager.switchScreen("main menu");
        });
        enterCourseButton.setOnAction(e ->{
            String courseString = enterCourseTextField.getText();
            String department = courseString.split(" ")[0];
            String catalogNumberString = courseString.split(" ")[1];
            int catalogNumber = Integer.parseInt(catalogNumberString);
            System.out.println(department + " " + catalogNumber);
            Course c = new Course(department, catalogNumber);
            if(implementation.hasReviews(c)) {
                displayReviews(c);
            }
            else{
                errorMessage.setText("Course has no reviews!");
            }

        });

    }

    private void displayReviews(Course c){
        List<String> allReviews= implementation.getAllReviewMessages(c);
        reviews.setText("");
        for(String review : allReviews) {
            reviews.setText(reviews.getText() + review+ "\n\n");
        }
    }

    private void resetFields(){
        avgRatingLabel.setText("");
        reviews.setText("");
        errorMessage.setText("");
    }

}
