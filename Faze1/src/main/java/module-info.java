module Faze1 {
    requires com.google.gson;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.fxml;
    requires javafx.controls;

    exports view;
    opens model to com.google.gson;
    exports controller;
    opens controller to javafx.fxml;
    opens view to com.google.gson, javafx.fxml;
    exports Enums;
    opens Enums to com.google.gson, javafx.fxml;
    exports practices;
    opens practices to com.google.gson, javafx.fxml;
    exports view.shop;
    opens view.shop to com.google.gson, javafx.fxml;
    exports view.trade;
    opens view.trade to com.google.gson, javafx.fxml;

}