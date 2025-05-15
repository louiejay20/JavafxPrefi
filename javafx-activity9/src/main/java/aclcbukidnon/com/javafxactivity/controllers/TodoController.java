package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    private final ObservableList<String> todoItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the ListView
        todoList.setItems(todoItems);

        // Set up cell factory for editing
        todoList.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };

            // Double-click to edit
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !cell.isEmpty()) {
                    editTodoItem(cell.getItem());
                }
            });

            return cell;
        });

        // Add sample items
        todoItems.addAll("Buy groceries", "Finish homework", "Call mom");

        // Set up keyboard shortcuts
        todoList.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String selected = todoList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                editTodoItem(selected);
            }
        } else if (event.getCode() == KeyCode.DELETE) {
            onDeleteClick();
        }
    }

    @FXML
    private void onCreateClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Todo");
        dialog.setHeaderText("Enter your new todo item:");
        dialog.setContentText("Todo:");

        dialog.showAndWait().ifPresent(newTodo -> {
            if (!newTodo.trim().isEmpty()) {
                todoItems.add(newTodo.trim());
                todoList.scrollTo(todoItems.size() - 1);
                todoList.getSelectionModel().select(todoItems.size() - 1);
            }
        });
    }

    @FXML
    private void onDeleteClick() {
        int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedItem = todoItems.get(selectedIndex);

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Delete Todo");
            confirm.setHeaderText("Delete selected todo?");
            confirm.setContentText("Are you sure you want to delete:\n\"" + selectedItem + "\"?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    todoItems.remove(selectedIndex);
                    // Select the next item or previous if at end
                    if (!todoItems.isEmpty()) {
                        int newSelection = Math.min(selectedIndex, todoItems.size() - 1);
                        todoList.getSelectionModel().select(newSelection);
                    }
                }
            });
        } else {
            showAlert("No Selection", "Please select a todo to delete.");
        }
    }

    private void editTodoItem(String currentValue) {
        int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) return;

        TextInputDialog dialog = new TextInputDialog(currentValue);
        dialog.setTitle("Edit Todo");
        dialog.setHeaderText("Edit your todo item:");
        dialog.setContentText("Todo:");

        dialog.showAndWait().ifPresent(updatedText -> {
            if (!updatedText.trim().isEmpty() && !updatedText.trim().equals(currentValue)) {
                todoItems.set(selectedIndex, updatedText.trim());
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}