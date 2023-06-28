package model.map;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Building.Building;
import model.people.Unit;

import java.util.ArrayList;

public class Cell {

    private Pane pane;
    private ImageView imageView;
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
        imageView = new ImageView();
        Image image ;
        if(xCoordinate % 2 == 0 && yCoordinate % 2 == 0) {
            image = new Image(getClass().getResource("/MapImages/Plain1.jpg").toExternalForm());
        }
        else{
            image = new Image(getClass().getResource("/MapImages/Sea1.jpg").toExternalForm());
        }
        imageView.setImage(image);
        pane.getChildren().add(imageView);


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
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
