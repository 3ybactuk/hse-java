package com.example.minihwsayhellojavafx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button nameButton;

    @FXML
    private void initialize() {
        nameButton.setDisable(true);
        nameButton.disableProperty()
                .bind(nameTextField.textProperty().isEmpty());
        nameTextField.setFocusTraversable(false);
    }

    @FXML
    private void onNameButtonClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello, " + nameTextField.getText() + "!", ButtonType.OK);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public void aboutClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Автор программы: Никита Шубин БПИ211", ButtonType.OK);
        alert.setHeaderText("");
        alert.showAndWait();
    }
}