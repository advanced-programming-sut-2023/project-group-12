package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MapView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(stage.getWidth(), stage.getHeight());
        gridPane.setGridLinesVisible(true);
        crateImage(gridPane);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    private void crateImage(GridPane gridPane) {
        Image image = new Image(Images.PLAIN1.getAddress());
        Image image1 = new Image(Images.SEA1.getAddress());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 5 == 0 && j% 5 == 0) {
                    ImageView imageView = new ImageView(image1);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    imageView.setLayoutY(30 * i);
                    imageView.setLayoutX(30 * j);
                    gridPane.add(imageView, i, j);
                }
                else {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    imageView.setLayoutY(30 * i);
                    imageView.setLayoutX(30 * j);
                    gridPane.add(imageView, i, j);
                }
            }
        }
    }
}