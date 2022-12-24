module com.example.currencyfx {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;

    requires javafx.controls;
    requires gson;


    opens com.example.currencyfx to javafx.fxml;
    exports com.example.currencyfx;
}