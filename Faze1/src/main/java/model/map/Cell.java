package model.map;


import Enums.BuildingImages;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Building.Building;
import model.people.Unit;

import java.util.ArrayList;

public class Cell {

    private Pane pane;
    private final int xCoordinate;
    private final int yCoordinate;
    private TextureType TextureType = model.map.TextureType.EARTH;
    private final boolean isPassable;
    private Tree tree = null;
    private boolean isSick = false;

    private Building building = null;
    private int height = 0;
    private ArrayList<Unit> units = new ArrayList<>();
    private boolean isInThePath = false;
    private Cell father = null;

    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        pane = new Pane();
        Image image = getTextureType().getImage();
        pane.getChildren().add(new ImageView(image));
        isPassable = getTextureType().isPassable();
    }

    public void setFather(Cell father) {
        this.father = father;
    }

    public Cell getFather() {
        return father;
    }

    public boolean isInThePath() {
        return isInThePath;
    }

    public void setInThePath(boolean inThePath) {
        this.isInThePath = inThePath;
    }

    public void setTextureType(model.map.TextureType textureType) {
        TextureType = textureType;
        setPane();
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
        setPane();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
        setPane();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void addUnits(Unit unit) {
        units.add(unit);
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TextureType getTextureType() {
        return TextureType;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
    public Pane getPane() {
        return pane;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public ImageView getImage() {
        return new ImageView(getTextureType().getImage());
    }

    public Image getTheImage() {
        return getTextureType().getImage();
    }

    public void setPane() {
        pane.getChildren().clear();
        pane.getChildren().add(new ImageView(getTextureType().getImage()));
        if (tree != null) {
            ImageView treeView = new ImageView(tree.getImage());
            pane.getChildren().add(treeView);
            treeView.setFitWidth(30);
            treeView.setFitHeight(30);
        }
        if (building != null) {
            ImageView buildingView = new ImageView(BuildingImages.getBuildingImageByBuildingType(building.getBuildingType()));
            buildingView.setFitHeight(30);
            buildingView.setFitWidth(30);
            pane.getChildren().add(buildingView);
        }
    }


}
