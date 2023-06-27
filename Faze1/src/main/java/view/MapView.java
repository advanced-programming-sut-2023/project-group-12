package view;

import Enums.Images;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.map.Map;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class MapView extends Application {

    public double stageWidth;
    public double stageHeight;
    public Stage stage ;
    public Map currentMap = new Map(100 , 5);

    public ScrollPane scrollPane;
    public Pane pane;
    public Pane bottomMenu;

    public GridPane showMap;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setFullScreen(true);
        Screen screen = Screen.getPrimary();
        stageWidth = screen.getBounds().getWidth();
        stageHeight = screen.getBounds().getHeight();
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);


        pane = new Pane();
        showMap = new GridPane();
        scrollPane = new ScrollPane();
        bottomMenu = new Pane();

        bottomMenu.setPrefSize(stageWidth, stageHeight/5);
        pane.setPrefSize(stageWidth, stageHeight);
        showMap.setPrefSize(30*currentMap.getDimension(), 30*currentMap.getDimension());
        scrollPane.setPrefSize(stageWidth, stageHeight*4/5);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        showMap.setGridLinesVisible(true);

        createCell();
        createBottomMenu();
//        zoomHandler();

        scrollPane.setContent(showMap);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void zoomHandler() {
        AtomicReference<Double> scale = new AtomicReference<>(1.0);
        AtomicReference<Double> translateX = new AtomicReference<>(0.0);
        AtomicReference<Double> translateY = new AtomicReference<>(0.0);

        showMap.setOnScroll(event -> {
            double delta = event.getDeltaY();
            if (delta > 0) {
                // Zoom in
                scale.updateAndGet(v -> (double) (v * 1.1));
                for(int i = 0; i < currentMap.getDimension(); i++) {
                    for (int j = 0; j < currentMap.getDimension(); j++) {
                        int finalJ = j;
                        int finalI = i;
                        translateX.updateAndGet(v -> (double) (v + currentMap.getMap()[finalI][finalJ].getImageView().getBoundsInParent().getWidth() / 20));
                        translateY.updateAndGet(v -> (double) (v + currentMap.getMap()[finalI][finalJ].getImageView().getBoundsInParent().getHeight() / 20));
                        currentMap.getMap()[i][j].getImageView().setScaleX(scale.get());
                        currentMap.getMap()[i][j].getImageView().setScaleY(scale.get());
                        currentMap.getMap()[i][j].getImageView().setTranslateX(translateX.get());
                        currentMap.getMap()[i][j].getImageView().setTranslateY(translateY.get());
                    }
                }

            } else if (delta < 0) {
                // Zoom out
                scale.updateAndGet(v -> (double) (v / 1.1));
                for(int i = 0; i < currentMap.getDimension(); i++) {
                    for (int j = 0; j < currentMap.getDimension(); j++) {
                        int finalI = i;
                        int finalJ = j;
                        translateX.updateAndGet(v -> (double) (v - currentMap.getMap()[finalI][finalJ].getImageView().getBoundsInParent().getWidth() / 22));
                        translateY.updateAndGet(v -> (double) (v - currentMap.getMap()[finalI][finalJ].getImageView().getBoundsInParent().getHeight() / 22));
                        currentMap.getMap()[i][j].getImageView().setScaleX(scale.get());
                        currentMap.getMap()[i][j].getImageView().setScaleY(scale.get());
                        currentMap.getMap()[i][j].getImageView().setTranslateX(translateX.get());
                        currentMap.getMap()[i][j].getImageView().setTranslateY(translateY.get());
                    }
                }

            }

        });
    }

    private void createBottomMenu() {
        Background background = new Background(setBackGround());
        bottomMenu.setBackground(background);
        VBox vBox = new VBox();
        vBox.getChildren().add(scrollPane);
        vBox.getChildren().add(bottomMenu);
        pane.getChildren().add(vBox);
    }




    private void createCell() {
        for(int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                showMap.add(currentMap.getMap()[i][j].getPane(), i, j);
            }
        }
    }
    private BackgroundImage setBackGround() {
        Image image = new Image(getClass().getResource("/Menus/menu.png").toExternalForm(), stageWidth ,stageHeight/5, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}