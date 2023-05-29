package ru.hse.javaprogramming.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    @FXML
    private TextField nameTextField;
    @FXML
    private Button nameButton;

    /**
     * Данный метод инициализирует view.
     * <p>
     * Мы не можем сделать это в конструкторе, потому что в нём поля будут {@code null}:
     * fxml проставит их после вызова конструктора, а потом вызовет метод {@code initialize}
     */
    @FXML
    private void initialize() {
        // Отключаем кнопку добавления при запуске
        nameButton.setDisable(true);

        // Если введенный текст пуст, то отключаем кнопку добавления
        nameButton.disableProperty()
                .bind(nameTextField.textProperty().isEmpty());
        // Если это не сделать, то при запуске курсор будет в поле ввода
        nameButton.setFocusTraversable(false);
    }

    @FXML
    private void onNameButtonClicked() {

    }
}
