package view;

import Enums.TextureType;
import Enums.Tree;
import javafx.scene.layout.Pane;
import model.Game;
import model.UserDatabase;
import model.map.Map;

import java.util.Objects;

public class CreateMapMenuController {
    private Map cuurrentMap;
    private static CreateMapMenuController menuController = null;

    public static CreateMapMenuController getInstance(Map map) {
        if (menuController == null)
            menuController = new CreateMapMenuController();
        menuController.cuurrentMap = map;
        return menuController;
    }

    public int getMapDimension() {
        return cuurrentMap.getDimension();
    }

    public void setCurrentMap() {
        UserDatabase.setCurrentMap(cuurrentMap);
    }

    public Pane getCellPane(int i, int j) {
        return cuurrentMap.getMap()[i][j].getPane();
    }

    public void setTexture(String string, int i, int j) {
        cuurrentMap.getMap()[i][j].setTextureType(Objects.requireNonNull(getTextureByName(string)));
    }

    public Tree getTreeByName(String string) {
        for (Tree tree : Tree.values()) {
            if (tree.getImage().getUrl().equals(string))
                return tree;
        }
        return null;
    }

    public TextureType getTextureByName(String string) {
        for (TextureType textureType : TextureType.values()) {
            if (textureType.getImage().getUrl().equals(string))
                return textureType;
        }
        return null;
    }

    public void setTree(String string, int i, int j) {
        cuurrentMap.getMap()[i][j].setTree(getTreeByName(string));
    }
}
