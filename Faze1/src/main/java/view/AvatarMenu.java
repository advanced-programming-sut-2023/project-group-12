package view;

import Enums.Avatars;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

import java.awt.*;
import java.io.File;

public class AvatarMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //TEMP
        User user = new User("admin", "admin", "admin", "admin", "admin");
        UserDatabase.setCurrentUser(user);
        Pane pane = new Pane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        RegisterMenu.setBackGround(pane);
        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {
            try {
                new ProfileMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(width / 2 - 100);
        gridPane.setLayoutY(height / 2 - 200);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Circle avatar = new Circle(50);
        avatar.setCenterX(width - 50);
        avatar.setCenterY(50);
        avatar.setFill(new ImagePattern(new Image(UserDatabase.getCurrentUser().getAvatar())));
        //todo: add avatars to gridPane
        addAvatar(gridPane, Avatars.AVATAR1.getAddress(), 0, 0, avatar);
        addAvatar(gridPane, Avatars.AVATAR2.getAddress(), 0, 1, avatar);
        Button addAvatar = new Button("choose avatar from system");
        addAvatar.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose your avatar");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", ".gif");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File initialDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(initialDirectory);
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                avatar.setFill(new ImagePattern(image));
                UserDatabase.getCurrentUser().setAvatar(selectedFile.toURI().toString());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Avatar changed successfully");
                alert.showAndWait();
            }
        });
        ////////////////////////////////////////////////////////////////////////
        Button avatarByDragging = new Button("choose avatar by dragging");
        avatarByDragging.setLayoutX(width / 2 - 100);
        avatarByDragging.setLayoutY(height / 2 + 150);
        avatarByDragging.setOnMouseClicked(event -> {

        });
        addAvatar.setLayoutX(width / 2 - 100);
        addAvatar.setLayoutY(height / 2 + 100);
        pane.getChildren().addAll(back, gridPane, addAvatar, avatar, avatarByDragging);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static void addAvatar(GridPane gridPane, String address, int i, int j, Circle avatar) {
        Image image = new Image(address);
        Circle circle = new Circle();
        circle.setRadius(50);
        circle.setFill(new ImagePattern(image));
        gridPane.add(circle, i, j);
        circle.setOnMouseClicked(event -> {
            UserDatabase.getCurrentUser().setAvatar(address);
            avatar.setFill(new ImagePattern(image));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Avatar changed successfully");
            alert.showAndWait();
        });
    }
}
