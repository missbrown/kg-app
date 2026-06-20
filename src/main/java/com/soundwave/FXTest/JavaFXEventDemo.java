package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JavaFXEventDemo extends Application {
    Label response;

    @Override
    public void start(Stage myStage) throws Exception {
        myStage.setTitle("introducing Buttons and Events");
        FlowPane rootNode = new FlowPane(10, 10);  
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);
        myStage.setScene(myScene);

        response = new Label("Push a button");
        Button btnFirst = new Button("First");
        Button btnSecond = new Button("Second");

        // handle the action events for the buttons
        btnFirst.setOnAction(e -> response.setText("First button was pressed"));
        btnSecond.setOnAction(e -> response.setText("Second button was pressed"));

        rootNode.getChildren().addAll(btnFirst, btnSecond, response);

        myStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
