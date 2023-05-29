module com.example.minihwsayhellojavafx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.minihwsayhellojavafx2 to javafx.fxml;
    exports com.example.minihwsayhellojavafx2;
}