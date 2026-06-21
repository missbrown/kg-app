package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ToggleButtonDemo extends Application {
    

    ToggleButton tbToggle;
    Label response;
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Demonstrate a Toggle Button");

        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 200, 100);
        myStage.setScene(myScene);

        response = new Label("Push the button");
        tbToggle = new ToggleButton("Toggle");

        tbToggle.setOnAction(event -> {
            if (tbToggle.isSelected()) {
                tbToggle.setText("On");
                response.setText("The toggle button is up");
            } else {
                tbToggle.setText("Off");
                response.setText("The toggle button is down");
            }
        });

        rootNode.getChildren().addAll(tbToggle, response);

        myStage.show();
    }
    
}
