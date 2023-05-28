module Faze1 {
    requires com.google.gson;
    requires javafx.graphics;

    exports view;
    opens model to com.google.gson;
    exports controller;
    opens controller to javafx.fxml;
    opens view to com.google.gson, javafx.fxml;

}