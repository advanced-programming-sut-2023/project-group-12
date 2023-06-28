package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardMenu extends Application {
    private boolean isFromProfile = true;

    public boolean isFromProfile() {
        return isFromProfile;
    }

    public void setFromProfile(boolean fromProfile) {
        isFromProfile = fromProfile;
    }

    private int counter = 0;

    @Override
    public void start(Stage stage) throws Exception {
        // todo remove this
        UserDatabase.loadUsers();
        UserDatabase.setCurrentUser(UserDatabase.getUserByUsername("egwasg"));
        //
        Button back = getBack(stage);
        Pane pane = new Pane(back);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        ArrayList<User> users = sortPlayers();
        VBox vBox = getvBox(width, users,height);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private VBox getvBox(double width, ArrayList<User> users,double height) {
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        int cnt = 0;
        for (User user : users) {
            HBox hBox = new HBox();
            hBox.setSpacing(20);
            Text text = new Text(user.getRank() + ") " + user.getUsername() + " : " + user.getHighScore());
            text.setStyle("-fx-font-size: 20");
            ImageView avatar = null;
            if (user.getAvatar() != null) {
                avatar = new ImageView(user.getAvatar());
                avatar.setOnMouseClicked(event -> {
                    Image image = new Image(user.getAvatar());
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image);
                    clipboard.setContent(content);
                    UserDatabase.getCurrentUser().setAvatar(Clipboard.getSystemClipboard().getUrl());
                });
            } else {
                avatar = new ImageView(getClass().getResource("/Avatars/no_avatar.png").toExternalForm());
                avatar.setOnMouseClicked(event -> {
                    Image image = new Image(getClass().getResource("/Avatars/no_avatar.png").toExternalForm());
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image);
                    clipboard.setContent(content);
                    UserDatabase.getCurrentUser().setAvatar(Clipboard.getSystemClipboard().getUrl());
                });
            }
            avatar.setFitWidth(50);
            avatar.setFitHeight(50);
            hBox.getChildren().addAll(avatar, text);
            vBox.getChildren().add(hBox);
        }

        // Wrap the VBox in a ScrollPane
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(vBox);
        scrollPane.setPrefSize(width - 100, 620);
        VBox wrapper = new VBox(scrollPane);
        wrapper.setLayoutX(50);
        wrapper.setLayoutY(height/2 - 310);
        wrapper.setSpacing(15);
        wrapper.setAlignment(Pos.CENTER);

        return wrapper;
    }

    private Button getBack(Stage stage) {
        Button back = new Button("Back");
        back.setLayoutX(0);
        back.setLayoutY(0);
        back.setPrefSize(100, 20);
        back.setOnMouseClicked(event -> {
            try {
                if (isFromProfile)
                    new ProfileMenu().start(stage);
                //else
                //todo
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return back;
    }

    private ArrayList<User> sortPlayers() {
        return UserDatabase.rankPlayers();
    }
}
