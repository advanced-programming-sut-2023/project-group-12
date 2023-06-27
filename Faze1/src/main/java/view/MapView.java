package view;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.map.Cell;
import model.map.Map;


import java.util.ArrayList;
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
        selectCellsHandler();

        scrollPane.setContent(showMap);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void selectCellsHandler() {
        AtomicReference<Double> firstX = new AtomicReference<>((double) 0);
        AtomicReference<Double> firstY = new AtomicReference<>((double) 0);

        ArrayList<Pane> selectedPain = new ArrayList<Pane>();
        ArrayList<Cell> selectedCells = new ArrayList<Cell>();
        showMap.setOnMousePressed(mouseEvent -> {
            firstX.set(mouseEvent.getX());
            firstY.set(mouseEvent.getY());
//            System.out.println(x + " " + y);
//            for (Node child : showMap.getChildren()) {
//                if(child instanceof Pane){
//                    Bounds bounds = child.getBoundsInParent();
//                    if(bounds.intersects(mouseEvent.getX(), mouseEvent.getY(), 0, 0)){
//                        selectedPain.add((Pane) child);
//                    }
//                }
//            }
        });

        showMap.setOnMouseDragged(mouseEvent -> {
            for (Pane pane : selectedPain) {
                Cell cell = getCellByPane(pane);
                if(cell != null){
                    selectedCells.add(cell);
                }
            }
        });

        showMap.setOnMouseReleased(mouseEvent -> {
            System.out.println("salam");
            double dblX = mouseEvent.getX();
            double dblY = mouseEvent.getY();
            Rectangle rect = new Rectangle(firstX.get(), firstY.get(), Math.abs( dblX -firstX.get()), Math.abs( dblY - firstY.get()));
            rect.setFill(Color.TRANSPARENT);
//            pane.getChildren().add(rect);
            for (Node child : showMap.getChildren()) {
                if(child instanceof Pane){
                    Bounds bounds = child.getBoundsInParent();
                    if(bounds.intersects(rect.getBoundsInParent())){
                        selectedPain.add((Pane) child);
                        ((Pane)child).setStyle("-fx-border-color: red;");
                    }
                }
            }

        });
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
    public Cell getCellByPane(Pane pane) {
        for (Cell[] cells : currentMap.getMap()) {
            for (Cell cell : cells) {
                if(cell.getPane() == pane){
                    return cell;
                }
            }
        }
        return null;
    }
}