module com.example.openendedlab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.openendedlab to javafx.fxml;
    exports com.example.openendedlab;
    exports com.example.openendedlab.Model;
    opens com.example.openendedlab.Model to javafx.fxml;
    exports com.example.openendedlab.ShowProducts to javafx.fxml;
    opens com.example.openendedlab.ShowProducts to javafx.fxml;
    exports com.example.openendedlab.AddProduct to javafx.fxml;
    opens com.example.openendedlab.AddProduct to javafx.fxml;
}