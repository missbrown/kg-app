package com.soundwave.FXTest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CheckBoxDemo extends Application {

    CheckBox cbKeyBoard;
    CheckBox cbMouse;
    CheckBox cbTouchScreen;

    Label response;
    Label selected;

    String inputDevices = "";

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage myStage) {

        // Create a Label to display the state of the CheckBox
        Label heading = new Label("select input devices");
        response = new Label("no devices selected");
        selected = new Label("supported devices: ");

        cbKeyBoard = new CheckBox("Keyboard");
        cbMouse = new CheckBox("Mouse");
        cbTouchScreen = new CheckBox("Touch Screen");

        // Add an event listener to the CheckBox
        cbKeyBoard.setOnAction(event -> {
            if (cbKeyBoard.isSelected()) {
                response.setText("Keyboard is selected");
            } else {
                response.setText("Keyboard is not selected");
            }
            showAll();
        });

        cbMouse.setOnAction(event -> {
            if (cbMouse.isSelected()) {
                response.setText("Mouse is selected");
            } else {
                response.setText("Mouse is not selected");
            }
            showAll();
        });

        cbTouchScreen.setOnAction(event -> {
            if (cbTouchScreen.isSelected()) {
                response.setText("Touch Screen is selected");
            } else {
                response.setText("Touch Screen is not selected");
            }
            showAll();
        });

        // Create a layout and add the CheckBox and Label
        FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 0, 10);
        rootNode.setAlignment(Pos.CENTER_LEFT);
        rootNode.setPadding(new Insets(0, 0, 0, 10));
        rootNode.getChildren().addAll(heading, cbKeyBoard, cbMouse, cbTouchScreen, response, selected);

        // Create a Scene and set it on the Stage
        Scene scene = new Scene(rootNode, 300, 200);
        myStage.setScene(scene);
        myStage.setTitle("CheckBox Demo");
        myStage.show();
    }

    public void showAll() {
        inputDevices = "";
        if (cbKeyBoard.isSelected()) {
            inputDevices += "Keyboard ";
        }
        if (cbMouse.isSelected()) {
            inputDevices += "Mouse ";
        }
        if (cbTouchScreen.isSelected()) {
            inputDevices += "Touch Screen ";
        }
        if(inputDevices.equals("")) {
            inputDevices = "<None>";
        }
        selected.setText("Supported devices: " + inputDevices);
    }
}
