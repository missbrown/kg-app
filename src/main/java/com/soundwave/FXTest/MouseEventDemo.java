package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MouseEventDemo extends Application {
    
    Label showEvent;
    Label showLocation;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Handle Mouse Events");

        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 0, 10);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);
        myStage.setScene(myScene);

        showEvent = new Label("Use the mouse");
        showLocation = new Label("");

        // handle mouse clicked event on the scene
        myScene.setOnMouseClicked(event -> {
            switch(event.getButton()) {
                case PRIMARY:
                    showEvent.setText("you clicked the primary mouse button");
                    break;
                case MIDDLE:
                    showEvent.setText("you clicked the middle mouse button");
                    break;
                case SECONDARY:
                    showEvent.setText("you clicked the secondary mouse button");
                    break;
            }
        });

        myScene.setOnMouseMoved(event -> {
            showLocation.setText("Mouse moved to: " + event.getX() + ", " + event.getY());
        });

        rootNode.getChildren().addAll(showEvent, showLocation);

        myStage.show();
    }
}
