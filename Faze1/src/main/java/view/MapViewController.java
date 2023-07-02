package view;

import controller.GameController.BuildingController;
import controller.GameController.GameMenuController;
import controller.mapmenu.MapMenuController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;
import model.Kingdom;
import model.map.Cell;

import java.util.ArrayList;

public class MapViewController {
    private static MapViewController controller = null;
    private Game game;

    public static synchronized MapViewController getInstance(Game game) {
        if (controller == null)
            controller = new MapViewController();
        controller.game = game;
        return controller;
    }

    public int getCurrentKingdomFoodRate() {
        return game.getCurrentKingdom().getFoodRate();
    }

    public int getCurrentKingdomTaxRate() {
        return game.getCurrentKingdom().getTaxRate();
    }

    public int getCurrentKingdomReligiousPeople() {
        return game.getCurrentKingdom().getReligiousPeople();
    }

    public int getCurrentKingdomPopulation() {
        return game.getCurrentKingdom().getPopulation();
    }

    public int getCurrentKingdomFearRate() {
        return game.getCurrentKingdom().getFearRate();
    }

    public int getCurrentMapDimension() {
        return game.getCurrentMap().getDimension();
    }

    public Pane getCellPane(int x, int y) {
        return game.getCurrentMap().getMap()[x][y].getPane();
    }


    public String getCellDetail(int i, int j) {
        MapMenuController mapController = new MapMenuController(game.getCurrentMap());
        return mapController.showDetail(String.valueOf(i + 1), String.valueOf(j + 1));
    }

    public String getDropBuildingAlert(int i, int j, String buildingName) {
        return (new GameMenuController(game)).dropBuilding(String.valueOf(i), String.valueOf(j), buildingName);
    }

    public ImageView getCellImageByPane(Pane pane) {
        return getCellByPane(pane).getImage();
    }

    private Cell getCellByPane(Pane pane) {
        for (Cell[] cells : game.getCurrentMap().getMap()) {
            for (Cell cell : cells) {
                if (cell.getPane() == pane) {
                    return cell;
                }
            }
        }
        return null;
    }

    public Image getCellImage(int i, int j) {
        return game.getCurrentMap().getMap()[i][j].getTheImage();
    }

    public int getCellXCoordinateByPane(Pane pane) {
        return getCellByPane(pane).getxCoordinate();
    }

    public int getCellYCoordinateByPane(Pane pane) {
        return getCellByPane(pane).getyCoordinate();
    }

    public String getPaneBuildingName(Pane pane) {
        return getCellByPane(pane).getBuilding().getBuildingType().getBuildingName();
    }

    public void setBuildingNullByPane(Pane pane) {
        getCellByPane(pane).setBuilding(null);
    }

    public void minusNumberOfWorkers() {
        game.getCurrentKingdom().setNumberOfWorkers(game.getCurrentKingdom().getNumberOfWorkers() - 1);
    }

    public void goToPauseMenu(Stage stage) throws Exception {
        (new PauseMenu(game, stage)).start(stage);
    }

    public String getGameBriefing() {
        String text = "";
        for (Kingdom kingdom : game.getPlayers()) {
            text += "kingdom name = " + kingdom.getOwner().getUsername() + "\n" +
                    "kingdom gold = " + kingdom.getGold() + "\n";
        }
        return text;
    }

    public double getCurrentKingdomGold() {
        return game.getCurrentKingdom().getGold();
    }

    public int getNumberOfWorkers() {
        return game.getCurrentKingdom().getNumberOfWorkers();
    }

    public int getCurrentKingdomPopularity() {
        return game.getCurrentKingdom().getPopularity();
    }

    public String getDetailText(ArrayList<Pane> selectedPain) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (Pane pane1 : selectedPain)
            cells.add(getCellByPane(pane1));
        return (new MapMenuController(game.getCurrentMap())).showDetails(cells, game.getCurrentKingdom());
    }

    public String getBuildingControllerRepairText() {
        return (new BuildingController(game)).repair();
    }
}
