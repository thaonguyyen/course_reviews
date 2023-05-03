package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController {
    private CourseReviewImplementation implementation;
    @FXML
    private Button submitReview, seeReviews, logout;
    @FXML
    private Label title, prompt;
    @FXML
    public void initialize(){
        implementation = CourseReviewImplementation.getInstance();
        title.setText("Main Menu");
        prompt.setText("Please select an option below");
        submitReview.setText("Submit Review");
        seeReviews.setText("See Reviews");
        logout.setText("Logout");
        submitReview.setOnAction(e->{
            //switch to submit review scene
        });
        seeReviews.setOnAction(e->{
            //switch to see review scene
        });
        logout.setOnAction(e->{
            implementation.logout();
            //switch to welcome screen
        });
    }
}

