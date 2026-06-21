package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class KeyEventDemo extends Application {
    
    Label prompt;
    Label showKey;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Handle Keyboard Events");

        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 0, 10);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);
        myStage.setScene(myScene);

        prompt = new Label("Press a key.");
        showKey = new Label("");

        // handle a key typed evert on the scene
        myScene.setOnKeyTyped(event -> 
            showKey.setText("you typed: " + event.getCharacter()
        ));

        // handle a key pressed event on the scene
        myScene.setOnKeyPressed(event -> 
            showKey.setText("you pressed: " + event.getCode().toString()
        ));

        rootNode.getChildren().addAll(prompt, showKey);

        myStage.show();
    }
}
