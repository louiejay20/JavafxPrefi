package aclcbukidnon.com.javafxactivity.controllers;


import aclcbukidnon.com.javafxactivity.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {


    @FXML
    private BorderPane rootPane;


    @FXML
    public void initialize() {
        loadPage("counter-view.fxml");
    }


    @FXML
    protected void onTrafficLightClick() {
        loadPage("traffic-light-view.fxml");
    }

    @FXML
    protected void onCounterClick() {
        loadPage("counter-view.fxml");
    }

    @FXML
    protected void onCalculatorClick() {
        loadPage("calculator-view.fxml");

    }

    @FXML
    protected void onTodoClick() {
        loadPage("todo-view.fxml");
    }


    private void loadPage(String fxml) {

        try {
            var root = FXMLLoader.load(Main.class.getResource(fxml));
            assert rootPane != null;
            rootPane.setCenter((Node) root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
