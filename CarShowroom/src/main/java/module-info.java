module com.example.carshowroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.carshowroom to javafx.fxml;
    exports com.example.carshowroom;
}