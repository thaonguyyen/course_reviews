package org.example;

import javafx.scene.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;

//Used this solution for switching scenes
//https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx
public class ScreenManager {
    HashMap<String, Pane> screens = new HashMap<String, Pane>();
    private Scene main;

    public ScreenManager(Scene main){
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
