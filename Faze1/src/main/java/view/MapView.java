package view;

import Enums.BuildingImages;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.map.Cell;
import model.map.Map;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MapView extends Application {

    public Text populationText;
    public Text coinText;

    public double stageWidth;
    public double stageHeight;


    public HBox barMenu;
    public GridPane miniMap;
    Background barMenuBackground = new Background(setBackGround());
    public HBox buildingSelection;
    public Circle church;
    public Circle resourceBuilding;
    public Circle foodBuilding;
    public Circle militaryBuilding;
    public Circle buildBuilding;

    public ArrayList<Pane> selectedPain = new ArrayList<>();
    public Stage stage;
    public Map currentMap = new Map(100, 5);

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
        barMenu = new HBox();

        barMenu.setPrefSize(stageWidth, stageHeight / 5);
        pane.setPrefSize(stageWidth, stageHeight);
        showMap.setPrefSize(30 * currentMap.getDimension(), 30 * currentMap.getDimension());
        scrollPane.setPrefSize(stageWidth, stageHeight * 4 / 5);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        showMap.setGridLinesVisible(true);

        createCell();
        createBarMenu();
//        zoomHandler();
        selectCellsHandler();


        buildingSelection = new HBox();
        buildingSelection.setSpacing(30);
        buildingSelection.setPrefHeight(100);
        buildingSelection.setPrefWidth(978);
        initBuilding(buildingSelection);
        church = new Circle(12);
        foodBuilding = new Circle(12);
        buildBuilding = new Circle(12);
        resourceBuilding = new Circle(12);
        militaryBuilding = new Circle(12);
        ScrollPane buildMenu = new ScrollPane();
        buildMenu.setMaxHeight(182);
        buildMenu.setMinWidth(500);
        buildMenu.setMaxWidth(300);
        buildMenu.setContent(buildingSelection);

        church.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/churchSym.jpeg").toExternalForm())));
        foodBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/farming.png").toExternalForm())));
        buildBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/house.png").toExternalForm())));
        resourceBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/resourceSym.png").toExternalForm())));
        militaryBuilding.setFill(new ImagePattern(new Image(MapMenu.class.getResource("/images/championcaribs.png").toExternalForm())));
        church.setOnMouseClicked(this::clickChurch);
        foodBuilding.setOnMouseClicked(this::clickFoodBuilding);
        buildBuilding.setOnMouseClicked(this::clickBuildBuilding);
        resourceBuilding.setOnMouseClicked(this::clickResource);
        militaryBuilding.setOnMouseClicked(this::clickMilitary);
        VBox vBox = new VBox();
        vBox.getChildren().add(church);
        vBox.getChildren().add(foodBuilding);
        vBox.getChildren().add(buildBuilding);
        vBox.getChildren().add(resourceBuilding);
        vBox.getChildren().add(militaryBuilding);
        barMenu.getChildren().add(buildMenu);
        barMenu.getChildren().add(vBox);
        barMenu.getChildren().add(miniMap);


        scrollPane.setContent(showMap);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


//    private void initMiniMap() {
//        tiles = Game.getMapInGame().getMap();
//        for (int i = 0; i < 100; i++) {
//            for (int j = 0; j < 100; j++) {
//                ImageView imageView = new ImageView(tiles[j][i].getImage());
//                imageView.setFitHeight(0.8);
//                imageView.setFitWidth(0.8);
//                miniMap.add(imageView, j, i);
//            }
//        }
//    }

    private void selectCellsHandler() {
        AtomicReference<Double> firstX = new AtomicReference<>((double) 0);
        AtomicReference<Double> firstY = new AtomicReference<>((double) 0);
        showMap.setOnMousePressed(mouseEvent -> {
            resetSelected();
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

//        showMap.setOnMouseDragged(mouseEvent -> {
//            for (Pane pane : selectedPain) {
//                Cell cell = getCellByPane(pane);
//                if(cell != null){
//                    selectedCells.add(cell);
//                }
//            }
//        });

        showMap.setOnMouseReleased(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            Rectangle rect = new Rectangle(Math.min(firstX.get(), x), Math.min(firstY.get(), y), Math.abs(x - firstX.get()), Math.abs(y - firstY.get()));
            rect.setFill(Color.TRANSPARENT);
            for (Node child : showMap.getChildren()) {
                if (child instanceof Pane) {
                    Bounds bounds = child.getBoundsInParent();
                    if (bounds.intersects(rect.getBoundsInParent())) {
                        Pane pane = (Pane) child;
                        selectedPain.add((Pane) child);
                        getCellByPane(pane).getImage().setOpacity(0.5);
//                        BorderStroke borderStroke = new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2));
//                        Rectangle clip = new Rectangle();
//                        clip.setWidth(pane.getWidth());
//                        clip.setHeight(pane.getHeight());
//                        clip.setLayoutX(pane.getLayoutX());
//                        clip.setLayoutY(pane.getLayoutY());
//                        clip.setFill(Color.TRANSPARENT);
//                        pane.setClip(clip);
//                        pane.setBorder(new Border(borderStroke));
//                        System.out.println("salam1");
//                        getCellByPane((Pane)child).getImageView().setStyle("-fx-border-color: blue;");
                    }
                }
            }

        });
    }

    private void resetSelected() {
        for (Pane pane1 : selectedPain) {
            getCellByPane(pane1).getImage().setOpacity(1);
        }
        selectedPain.clear();
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
                for (int i = 0; i < currentMap.getDimension(); i++) {
                    for (int j = 0; j < currentMap.getDimension(); j++) {
                        int finalJ = j;
                        int finalI = i;
                        translateX.updateAndGet(v -> (double) (v + currentMap.getMap()[finalI][finalJ].getImage().getBoundsInParent().getWidth() / 20));
                        translateY.updateAndGet(v -> (double) (v + currentMap.getMap()[finalI][finalJ].getImage().getBoundsInParent().getHeight() / 20));
                        currentMap.getMap()[i][j].getImage().setScaleX(scale.get());
                        currentMap.getMap()[i][j].getImage().setScaleY(scale.get());
                        currentMap.getMap()[i][j].getImage().setTranslateX(translateX.get());
                        currentMap.getMap()[i][j].getImage().setTranslateY(translateY.get());
                    }
                }

            } else if (delta < 0) {
                // Zoom out
                scale.updateAndGet(v -> (double) (v / 1.1));
                for (int i = 0; i < currentMap.getDimension(); i++) {
                    for (int j = 0; j < currentMap.getDimension(); j++) {
                        int finalI = i;
                        int finalJ = j;
                        translateX.updateAndGet(v -> (double) (v - currentMap.getMap()[finalI][finalJ].getImage().getBoundsInParent().getWidth() / 22));
                        translateY.updateAndGet(v -> (double) (v - currentMap.getMap()[finalI][finalJ].getImage().getBoundsInParent().getHeight() / 22));
                        currentMap.getMap()[i][j].getImage().setScaleX(scale.get());
                        currentMap.getMap()[i][j].getImage().setScaleY(scale.get());
                        currentMap.getMap()[i][j].getImage().setTranslateX(translateX.get());
                        currentMap.getMap()[i][j].getImage().setTranslateY(translateY.get());
                    }
                }

            }

        });
    }

    private void createBarMenu() {
        barMenu.setBackground(barMenuBackground);
        miniMap = new GridPane();
        VBox vBox = new VBox();
        vBox.getChildren().add(scrollPane);
        vBox.getChildren().add(barMenu);
        pane.getChildren().add(vBox);
        setMapBar();
    }


    private void createCell() {
        for (int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                Pane cell = currentMap.getMap()[i][j].getPane();
                showMap.add(cell, i, j);
                dragAndDrop(cell);
                dragEntered(cell);
                dragExited(cell);
                dragOver(cell);
                //TODO: connect info to map
                Tooltip cellTooltip = new Tooltip("this will be cell info");
                Tooltip.install(cell, cellTooltip);
            }
        }
    }

    private void createMiniMap() {
        for (int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                ImageView imageView = new ImageView(currentMap.getMap()[i][j].getTheImage());
                imageView.setFitWidth(1);
                imageView.setFitHeight(1);
                miniMap.add(imageView, i, j);
            }
        }
    }

    private BackgroundImage setBackGround() {
        Image image = new Image(getClass().getResource("/Menus/menu.png").toExternalForm(), stageWidth, stageHeight / 5, false, false);
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
                if (cell.getPane() == pane) {
                    return cell;
                }
            }
        }
        return null;
    }

    private void setMapBar() {
        setPopulationText();
        setCoinText();
        createMiniMap();
        barMenu.getChildren().add(populationText);
        barMenu.getChildren().add(coinText);
    }

    private void setPopulationText() {
        populationText = new Text(String.format("%d/%d", 50, 100));
        populationText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
        populationText.setFill(Color.GREEN);
        populationText.setTranslateY(120);
        populationText.setTranslateX(950);
    }

    private void setCoinText() {
        coinText = new Text(String.format("%d", 50));
        coinText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
        coinText.setFill(Color.RED);
        coinText.setTranslateY(160);
        coinText.setTranslateX(950);
    }

    private void handleDragBuilding(MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getSource();
        Dragboard dragboard = source.startDragAndDrop(TransferMode.COPY);

        if (dragboard.hasString()) {
            String url = dragboard.getString();
            System.out.println(dragboard.getDragViewOffsetX());
        }

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getImage().getUrl());
        dragboard.setContent(content);
        ImageView draggedContent = new ImageView(source.getImage());
        draggedContent.setFitWidth(source.getFitWidth());
        draggedContent.setFitHeight(source.getFitWidth());
        dragboard.setDragView(draggedContent.snapshot(null, null));

        mouseEvent.consume();
    }

    private void initBuilding(HBox building) {
        for (Image image : BuildingImages.getMilitaryBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragEntered(dragEvent -> {
                System.out.println(dragEvent.getX());
                System.out.println(dragEvent.getY());
                System.out.println(dragEvent.getScreenX());
                System.out.println(dragEvent.getScreenY());
                System.out.println(LocalTime.now());
                System.out.println("11111111111111111");
            });
            imageView.setOnDragExited(dragEvent -> {
                System.out.println(dragEvent.getX());
                System.out.println(dragEvent.getY());
                System.out.println(dragEvent.getScreenX());
                System.out.println(dragEvent.getScreenY());
                System.out.println(LocalTime.now());
                System.out.println("22222222222222222");
            });
            Tooltip tooltip = new Tooltip(BuildingImages.getMilitaryBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            building.getChildren().add(imageView);
        }
    }


    public void clickMilitary(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getMilitaryBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            Tooltip tooltip = new Tooltip(BuildingImages.getMilitaryBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickBuildBuilding(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getBuildBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            Tooltip tooltip = new Tooltip(BuildingImages.getBuildBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickFoodBuilding(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getFoodBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            Tooltip tooltip = new Tooltip(BuildingImages.getFoodBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickResource(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getSourceBuilding().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            Tooltip tooltip = new Tooltip(BuildingImages.getSourceBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            buildingSelection.getChildren().add(imageView);
        }
    }

    public void clickChurch(MouseEvent mouseEvent) {
        buildingSelection.getChildren().clear();
        for (Image image : BuildingImages.getChurches().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setOnDragDetected(this::handleDragBuilding);
            Tooltip tooltip = new Tooltip(BuildingImages.getChurches().get(image));
            Tooltip.install(imageView, tooltip);
            buildingSelection.getChildren().add(imageView);
        }
    }

    private void dragEntered(Pane pane) {
        pane.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString()) {
                    pane.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
            }
        });
    }

    private void dragExited(Pane pane) {
        pane.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString())
                    pane.setStyle("");
            }
        });
    }

    private void dragOver(Pane pane) {
        pane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getDragboard().hasString())
                    dragEvent.acceptTransferModes(TransferMode.COPY);

            }
        });
    }


    private void dragAndDrop(Pane pane) {
        pane.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            dragEvent.setDropCompleted(true);
            System.out.println(dragboard.getString());
            //TODO : DROP BUILDING ON MAP
        });
    }
}