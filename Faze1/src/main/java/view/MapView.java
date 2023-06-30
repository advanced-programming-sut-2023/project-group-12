package view;

import Enums.BuildingImages;
import controller.GameController.GameMenuController;
import controller.mapmenu.MapMenuController;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
import model.Building.Building;
import model.Building.BuildingType;
import model.Game;
import model.UserDatabase;
import model.map.Cell;
import model.map.Map;


import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class MapView extends Application {

    public Text populationText;
    public Text coinText;

    public double stageWidth;
    public double stageHeight;


    public HBox barMenu;
    public GridPane miniMap;
    public HBox buildingSelection;
    public Circle church;
    public Circle resourceBuilding;
    public Circle foodBuilding;
    public Circle militaryBuilding;
    public Circle buildBuilding;

    public ArrayList<Pane> selectedPain = new ArrayList<>();
    public Stage stage;
    public Map currentMap = new Map(100, 5);
    private final Game game;

    public ScrollPane scrollPane;
    public Pane pane;

    public GridPane showMap;
    private BuildingType selectedBuilding;

    public MapView(Game game) {
        this.game = game;
        this.currentMap = game.getCurrentMap();
    }

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
                int finalJ = j;
                int finalI = i;
                pane.setOnMouseClicked(mouseEvent -> {
                    if (selectedBuilding != null) {
                        (new Alert(Alert.AlertType.INFORMATION, (new GameMenuController(game)).dropBuilding(String.valueOf(finalI), String.valueOf(finalJ), selectedBuilding.getBuildingName()))).show();
                    }
                    selectedBuilding = null;
                });
                MapMenuController controller = new MapMenuController(game.getCurrentMap());
                Tooltip cellTooltip = new Tooltip(controller.showDetail(String.valueOf(i + 1), String.valueOf(j + 1)));
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
            Tooltip tooltip = new Tooltip(BuildingImages.getMilitaryBuilding().get(image));
            Tooltip.install(imageView, tooltip);
            building.getChildren().add(imageView);
            imageView.setOnMouseClicked(mouseEvent -> {
                String buildingName = BuildingImages.getNameOfBuildingByImage(image.getUrl());
                this.selectedBuilding = BuildingImages.getBuildingTypeByName(buildingName);
            });
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


    private void dragAndDrop(Pane thisPane) {
        thisPane.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            dragEvent.setDropCompleted(true);
            Cell cell = getCellByPane(thisPane);
            game.dropBuilding(cell.getxCoordinate(), cell.getyCoordinate(),
                    Objects.requireNonNull(BuildingImages.getBuildingTypeByName(dragboard.getUrl())));
        });
    }
}