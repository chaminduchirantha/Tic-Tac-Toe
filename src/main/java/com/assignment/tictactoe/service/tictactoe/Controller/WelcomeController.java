package com.assignment.tictactoe.service.tictactoe.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button btnStart;

    @FXML
    private AnchorPane welcomeAnchorPane;

    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        welcomeAnchorPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/boardUi.fxml"));
        welcomeAnchorPane.getChildren().add(load);
    }
}
