package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("/welcome-view.fxml"));
        Scene scene = new Scene(welcomeLoader.load());
        stage.setScene(scene);
        stage.show();
        ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.initializeMainScene(scene);
        FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("/create-new-user.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/main-menu-view.fxml"));
        FXMLLoader submitReviewLoader = new FXMLLoader(getClass().getResource("/submit-review-view.fxml"));
        FXMLLoader seeReviewLoader = new FXMLLoader(getClass().getResource("/see-reviews-view.fxml"));
        screenManager.addScreen("welcome", (Pane)scene.getRoot());
        screenManager.addScreen("new user", newUserLoader.load());
        screenManager.addScreen("login", loginLoader.load());
        screenManager.addScreen("main menu", mainMenuLoader.load());
        screenManager.addScreen("submit review", submitReviewLoader.load());
        screenManager.addScreen("see reviews", seeReviewLoader.load());
        CourseReviewImplementation implementation = CourseReviewImplementation.getInstance();
        implementation.connect();
        stage.setOnCloseRequest(e ->{
            implementation.logout();
            implementation.disconnect();
            Platform.exit();
            System.exit(0);
        });
        WelcomeController welcomeController = welcomeLoader.getController();
        LoginController loginController = loginLoader.getController();
        CreateNewUserController createNewUserController = newUserLoader.getController();
        MainMenuController mainMenuController = mainMenuLoader.getController();
        SubmitReviewController submitReviewcontroller = submitReviewLoader.getController();
        SeeReviewsController seeReviewsController = seeReviewLoader.getController();




    }
    public static void main(String[] args) {
        launch();
    }
}