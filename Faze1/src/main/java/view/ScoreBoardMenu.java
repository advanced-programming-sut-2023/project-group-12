package view;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardMenu extends Application {
    private boolean isFromProfile;

    public boolean isFromProfile() {
        return isFromProfile;
    }

    public void setFromProfile(boolean fromProfile) {
        isFromProfile = fromProfile;
    }

    private int counter = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Button back = getBack(stage);
        Pane pane = new Pane(back);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
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
