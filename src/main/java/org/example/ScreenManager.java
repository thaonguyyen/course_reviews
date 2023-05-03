package org.example;

import javafx.scene.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;

//Used this solution for switching scenes, but made it a singleton so it's easier to pass around controller classes
//https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx
public class ScreenManager {
    private static ScreenManager instance;
    HashMap<String, Pane> screens = new HashMap<String, Pane>();
    private Scene main;

    private ScreenManager(){
    }

    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initializeMainScene(Scene main){
        this.main = main;
    }

    public void addScreen(String name, Pane pane){
        screens.put(name, pane);
    }

    public void removeScreen(String name){
        screens.remove(name);
    }

    public void switchScreen(String name){
        main.setRoot(screens.get(name));
    }


}
