package view;

import Enums.Images;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.map.Map;

public class MapView extends Application {
    public int firstCellX = 0;
    public int firstCellY = 0;
    public int zoomLevel = 1;
    public Map currentMap = new Map(100 , 5);
    public GridPane gridPane;
    @Override
    public void start(Stage stage) throws Exception {
        gridPane = new GridPane();
        gridPane.setPrefSize(600,600);
        gridPane.setGridLinesVisible(true);
        createCell();
        Scene scene = new Scene(gridPane);
        scrollMap(scene);
        stage.setScene(scene);
        stage.show();
    }



    private void scrollMap(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode().getName()) {
                    case "Left":
                        System.out.println("left");
                        if(firstCellX > 0){
                            firstCellX--;
                            createCell();
                        }
                        break;
                    case "Right":
                        System.out.println("right");
                        if(firstCellX < currentMap.getDimension()-zoomLevel*30){
                            firstCellX++;
                            createCell();
                        }

                        break;
                    case "Up":
                        System.out.println("up");
                        if(firstCellY > 0){
                            firstCellY--;
                            createCell();
                        }

                        break;
                    case "Down":
                        System.out.println("down");
                        if(firstCellY < currentMap.getDimension()-zoomLevel*30){
                            firstCellY++;
                            createCell();
                        }

                        break;
                    case "1":
                        if(zoomLevel <=2){
                            zoomLevel++;
                            createCell();
                        }
                        break;
                    case "2":
                        if (zoomLevel >= 2){
                            zoomLevel--;
                            createCell();
                        }
                        break;
                }
            }
        });
    }

    private void createCell() {
        gridPane.getChildren().clear();
        for(int i = 0; i < zoomLevel*30; i++) {
            for (int j = 0; j < zoomLevel*30; j++) {
                gridPane.add(currentMap.getMap()[firstCellX + i][firstCellY + j].getPane(), i, j);
            }
        }
    }
}