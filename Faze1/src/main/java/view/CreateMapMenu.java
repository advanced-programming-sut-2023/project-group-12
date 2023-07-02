package view;

import Enums.TextureType;
import Enums.Tree;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;


public class CreateMapMenu extends Application {
    private GridPane map;
    private HBox barMenu;
    private Stage stage;
    CreateMapMenuController menuController;

    public CreateMapMenu(CreateMapMenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setFullScreen(true);
        Screen screen = Screen.getPrimary();
        double stageWidth = screen.getBounds().getWidth();
        double stageHeight = screen.getBounds().getHeight();
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        Pane rootPane = new Pane();
        map = new GridPane();
        ScrollPane scrollPane = new ScrollPane();
        barMenu = new HBox();
        VBox vBox = new VBox();
        vBox.getChildren().add(scrollPane);
        vBox.getChildren().add(barMenu);
        rootPane.getChildren().add(vBox);

        barMenu.setPrefSize(stageWidth, stageHeight / 10);
        rootPane.setPrefSize(stageWidth, stageHeight);
        map.setPrefSize(30 * menuController.getMapDimension(), 30 * menuController.getMapDimension());
        scrollPane.setPrefSize(stageWidth, stageHeight * 9 / 10);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(map);
        map.setGridLinesVisible(true);

        createCell();
        createBarMenu();

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    private void createBarMenu() {
        Button saveButton = new Button("save");
        Button backButton = new Button("back");
        ScrollPane treeScrollPane = new ScrollPane();
        ScrollPane textureScrollPane = new ScrollPane();
        HBox textureSection = new HBox();
        HBox treeSection = new HBox();
        treeScrollPane.setContent(treeSection);
        textureScrollPane.setContent(textureSection);
        textureScrollPane.setMaxWidth(700);
        textureSection.setSpacing(10);
        treeScrollPane.setMaxWidth(700);
        treeSection.setSpacing(10);
        addTextureToTextureSection(treeSection);
        addTreeToMap(textureSection);
        barMenu.setSpacing(10);
        barMenu.getChildren().add(textureScrollPane);
        barMenu.getChildren().add(treeScrollPane);
        barMenu.getChildren().add(saveButton);
        barMenu.getChildren().add(backButton);


        saveButton.setOnMouseClicked(mouseEvent -> {
            menuController.setCurrentMap();
        });
        backButton.setOnMouseClicked(mouseEvent -> {
            try {
                (new MainMenu()).start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void addTreeToMap(HBox treeSection) {
        for (Tree tree : Tree.values()) {
            ImageView imageView = new ImageView(tree.getImage());
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            treeSection.getChildren().add(imageView);
            imageView.setOnDragDetected(this::handleDragTexture);

            Tooltip tooltip = new Tooltip(tree.name());
            Tooltip.install(imageView, tooltip);
        }
    }

    private void addTextureToTextureSection(HBox textureSection) {
        for (TextureType textureType : TextureType.values()) {
            ImageView imageView = new ImageView(textureType.getImage());
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            textureSection.getChildren().add(imageView);
            imageView.setOnDragDetected(this::handleDragTexture);

            Tooltip tooltip = new Tooltip(textureType.name());
            Tooltip.install(imageView, tooltip);
        }
    }

    private void createCell() {
        for (int i = 0; i < menuController.getMapDimension(); i++) {
            for (int j = 0; j < menuController.getMapDimension(); j++) {
                Pane cell = menuController.getCellPane(i, j);
                map.add(cell, i, j);
                dragAndDrop(cell, i, j);
                dragEntered(cell);
                dragExited(cell);
                dragOver(cell);
            }
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


    private void dragAndDrop(Pane pane, int i, int j) {
        pane.setOnDragDropped(dragEvent -> {
            Dragboard dragboard = dragEvent.getDragboard();
            dragEvent.setDropCompleted(true);
            if (menuController.getTextureByName(dragboard.getString()) != null) {
                menuController.setTexture(dragboard.getString(), i, j);
            } else if (menuController.getTreeByName(dragboard.getString()) != null) {
                menuController.setTree(dragboard.getString(), i, j);
            }
        });
    }

    private void handleDragTexture(MouseEvent mouseEvent) {
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
}
