module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsapi;
    requires freetts;


    opens main to javafx.fxml;
    exports main;
}