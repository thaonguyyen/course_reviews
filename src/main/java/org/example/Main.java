package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public void start(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-view.fxml"));
        Scene scene = new Scene(loader.load());
        ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.initializeMainScene(scene);
        screenManager.addScreen("welcome", FXMLLoader.load(getClass().getResource("welcome-view.fxml")));
        screenManager.addScreen("new user", FXMLLoader.load(getClass().getResource("create-new-user.fxml")));
        screenManager.addScreen("login", FXMLLoader.load(getClass().getResource("login-view.fxml")));
        screenManager.addScreen("main menu", FXMLLoader.load(getClass().getResource("main-menu-view.fxml")));
        screenManager.addScreen("enter course",FXMLLoader.load(getClass().getResource("enter-course-view.fxml")));
        screenManager.addScreen("submit review", FXMLLoader.load(getClass().getResource("submit-review-view.fxml")));
        screenManager.addScreen("see reviews", FXMLLoader.load(getClass().getResource("see-reviews-view.fxml")));
        CourseReviewImplementation implementation = CourseReviewImplementation.getInstance();
        implementation.connect();
        stage.setOnCloseRequest(e ->{
            implementation.logout();
            implementation.disconnect();
            Platform.exit();
            System.exit(0);
        });



    }
    public static void main(String[] args) {
        launch();
    }
}