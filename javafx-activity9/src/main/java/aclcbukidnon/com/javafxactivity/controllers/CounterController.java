package aclcbukidnon.com.javafxactivity.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {

    @FXML
    private Label counterLabel;

    private int count = 0;

    @FXML
    private void handleIncrement(ActionEvent event) {
        count++;
        updateCounterLabel();
    }

    @FXML
    private void handleDecrement(ActionEvent event) {
        count--;
        updateCounterLabel();
    }

    private void updateCounterLabel() {
        counterLabel.setText(String.valueOf(count));
    }
}
