package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
    @FXML
    private Label displayLabel;

    private String currentInput = "";
    private String firstOperand = "";
    private String operator = "";
    private boolean startNewInput = true;

    @FXML
    public void initialize() {
        displayLabel.setText("0");
    }

    // Handle number button presses (0-9)
    @FXML
    private void handleNumberButton(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();

        if (startNewInput) {
            currentInput = number;
            startNewInput = false;
        } else {
            currentInput += number;
        }

        displayLabel.setText(currentInput);
    }

    // Handle operator button presses (+, -, *, /)
    @FXML
    private void handleOperatorButton(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String newOperator = button.getText();

        if (!currentInput.isEmpty()) {
            if (!firstOperand.isEmpty() && !operator.isEmpty() && !startNewInput) {
                calculateResult();
            }
            firstOperand = currentInput;
            operator = newOperator;
            startNewInput = true;
        } else if (!firstOperand.isEmpty()) {
            operator = newOperator;
        }
    }

    // Handle equals button (=)
    @FXML
    private void handleEqualsButton() {
        if (!firstOperand.isEmpty() && !operator.isEmpty() && !startNewInput) {
            calculateResult();
            operator = "";
        }
    }

    // Handle clear button (CLEAR)
    @FXML
    private void handleClearButton() {
        currentInput = "";
        firstOperand = "";
        operator = "";
        startNewInput = true;
        displayLabel.setText("0");
    }

    // Handle backspace button (BCKSPC)
    @FXML
    private void handleBackspaceButton() {
        if (!startNewInput && !currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.isEmpty()) {
                currentInput = "0";
                startNewInput = true;
            }
            displayLabel.setText(currentInput);
        }
    }

    // Handle decimal point button (.)
    @FXML
    private void handleDecimalButton() {
        if (startNewInput) {
            currentInput = "0.";
            startNewInput = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
        displayLabel.setText(currentInput);
    }

    // Perform the actual calculation
    private void calculateResult() {
        try {
            double num1 = Double.parseDouble(firstOperand);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        displayLabel.setText("Error");
                        return;
                    }
                    break;
            }

            // Format the result to remove trailing .0 if it's an integer
            if (result == (int) result) {
                currentInput = String.valueOf((int) result);
            } else {
                currentInput = String.valueOf(result);
            }

            firstOperand = currentInput;
            displayLabel.setText(currentInput);
            startNewInput = true;
        } catch (NumberFormatException e) {
            displayLabel.setText("Error");
        }
    }
}