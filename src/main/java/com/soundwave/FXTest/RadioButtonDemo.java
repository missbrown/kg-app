package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RadioButtonDemo extends Application{
    
    Label response;
    Label prompt;

    RadioButton rbKeyBoard;
    RadioButton rbMouse;
    RadioButton rbTouchScreen;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("Demonstrate Radio Buttons");

        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 0, 10);
        rootNode.setAlignment(Pos.CENTER_LEFT);
        rootNode.setPadding(new Insets(0,0,0,10));

        Scene myScene = new Scene(rootNode, 300, 200);
        myStage.setScene(myScene);

        prompt = new Label("Select an input device");
        response = new Label("");

        rbKeyBoard = new RadioButton("Keyboard");
        rbMouse = new RadioButton("Mouse");
        rbTouchScreen = new RadioButton("Touch Screen");

        ToggleGroup tg = new ToggleGroup();
        rbKeyBoard.setToggleGroup(tg);
        rbMouse.setToggleGroup(tg);
        rbTouchScreen.setToggleGroup(tg);

        rbKeyBoard.setOnAction(event -> {
            if (rbKeyBoard.isSelected()) {
                response.setText("Primary device is keyboard");
            }
        });
        rbMouse.setOnAction(event -> {
            if (rbMouse.isSelected()) {
                response.setText("Primary device is mouse");
            }
        });
        rbTouchScreen.setOnAction(event -> {
            if (rbTouchScreen.isSelected()) {
                response.setText("Primary device is touch screen");
            }
        });

        // select and generate an action event for the button
        rbKeyBoard.fire();

        rootNode.getChildren().addAll(prompt, rbKeyBoard, rbMouse, rbTouchScreen, response);

        myStage.show();
    }

}
