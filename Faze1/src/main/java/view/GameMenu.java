package view;

import Commands.GameMenuCommands;
import controller.GameController.GameMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    public void run(Scanner scanner) {
        String input, output;
        GameMenuController controller = new GameMenuController();
        Matcher chooseColor, chooseKeep, changeColor, changeKeep, dropBuilding, selectBuilding, createUnit, setFoodRate,
                setTaxRate, showTaxRate, setFearRate, showPopularityFactors, showPopularity, showFoodList, showFoodRate,
                dropUnit, repair, selectUnit, moveUnit, patrolUnit, setMode, groundAttack, airAttack, pourOil, digTunnel,
                buildEquipment, disbandUnit, goToShopMenu, goToTradeMenu;
        while (true) {
            input = scanner.nextLine();
            chooseColor = GameMenuCommands.getMatcher(input, GameMenuCommands.CHOOSE_COLOR);
            chooseKeep = GameMenuCommands.getMatcher(input, GameMenuCommands.CHOOSE_KEEP);
            changeColor = GameMenuCommands.getMatcher(input, GameMenuCommands.CHANGE_COLOR);
            changeKeep = GameMenuCommands.getMatcher(input, GameMenuCommands.CHANGE_KEEP);
            dropBuilding = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_BUILDING);
            selectBuilding = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_BUILDING);
            createUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.CREATE_UNIT);
            setFoodRate = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_FOOD_RATE);
            setTaxRate = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_TAX_RATE);
            showTaxRate = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_TAX_RATE);
            setFearRate = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_FEAR_RATE);
            showPopularityFactors = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_POPULARITY_FACTORS);
            showPopularity = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_POPULARITY);
            showFoodList = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_FOOD_LIST);
            showFoodRate = GameMenuCommands.getMatcher(input, GameMenuCommands.SHOW_FOOD_RATE);
            dropUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_UNIT);
            repair = GameMenuCommands.getMatcher(input, GameMenuCommands.REPAIR);
            selectUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT);
            moveUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_UNIT);
            patrolUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.PATROL_UNIT);
            setMode = GameMenuCommands.getMatcher(input, GameMenuCommands.SET_MODE);
            groundAttack = GameMenuCommands.getMatcher(input, GameMenuCommands.GROUND_ATTACK);
            airAttack = GameMenuCommands.getMatcher(input, GameMenuCommands.AIR_ATTACK);
            pourOil = GameMenuCommands.getMatcher(input, GameMenuCommands.POUR_OIL);
            digTunnel = GameMenuCommands.getMatcher(input, GameMenuCommands.DIG_TUNNEL);
            buildEquipment = GameMenuCommands.getMatcher(input, GameMenuCommands.BUILD_EQUIPMENT);
            disbandUnit = GameMenuCommands.getMatcher(input, GameMenuCommands.DISBAND_UNIT);
            goToShopMenu = GameMenuCommands.getMatcher(input, GameMenuCommands.GO_TO_SHOP_MENU);
            goToTradeMenu = GameMenuCommands.getMatcher(input, GameMenuCommands.GO_TO_TRADE_MENU);

            if (chooseColor.find()) {
                output = controller.chooseColor(chooseColor.group("color"));
                System.out.println(output);
            } else if (chooseKeep.find()) {
                output = controller.chooseKeep((chooseKeep.group("keepNumber")));
                System.out.println(output);
            } else if (changeColor.find()) {
                output = controller.changeColor(changeColor.group("color"));
                System.out.println(output);
            } else if (changeKeep.find()) {
                output = controller.changeKeep((changeKeep.group("keepNumber")));
                System.out.println(output);
            } else if (dropBuilding.find()) {
                output = controller.dropBuilding((dropBuilding.group("x")), (dropBuilding.group("y")), dropBuilding.group("type"));
                System.out.println(output);
            } else if (selectBuilding.find()) {
                output = controller.selectBuilding((selectBuilding.group("x")), (selectBuilding.group("y")));
                System.out.println(output);
            } else if (createUnit.find()) {
                output = controller.createUnit(createUnit.group("type"), (createUnit.group("count")));
                System.out.println(output);
            } else if (setFoodRate.find()) {
                output = controller.setFoodRate(setFoodRate.group("number"));
                System.out.println(output);
            } else if (setTaxRate.find()) {
                output = controller.setTaxRate(setTaxRate.group("number"));
                System.out.println(output);
            } else if (showTaxRate.find()) {
                output = controller.showTaxRate();
                System.out.println(output);
            } else if (setFearRate.find()) {
                output = controller.setFearRate(setFearRate.group("number"));
                System.out.println(output);
            } else if (showPopularityFactors.find()) {
                output = controller.showPopularityFactors();
                System.out.println(output);
            } else if (showPopularity.find()) {
                output = controller.showPopularity();
                System.out.println(output);
            } else if (showFoodList.find()) {
                output = controller.showFoodList();
                System.out.println(output);
            } else if (showFoodRate.find()) {
                output = controller.showFoodRate();
                System.out.println(output);
            } else if (dropUnit.find()) {
                output = controller.dropUnit(dropUnit.group("xCoordinate"), dropUnit.group("yCoordinate"), dropUnit.group("type"), dropUnit.group("count"));
                System.out.println(output);
            }
            else if (repair.find()) {
                output = controller.repair();
                System.out.println(output);
            }
            else if (selectUnit.find()) {
                output = controller.selectUnit(selectUnit.group("xCoordinate"), selectUnit.group("yCoordinate"),selectUnit.group("type"));
                System.out.println(output);
            }
            else if (selectBuilding.find()) {
                output = controller.selectBuilding(selectBuilding.group("xCoordinate"), selectBuilding.group("yCoordinate"));
                System.out.println(output);
            }
            else if (moveUnit.find()) {
                output = controller.moveUnit(moveUnit.group("xCoordinate"), moveUnit.group("yCoordinate"));
                System.out.println(output);
            }
            else if (patrolUnit.find()) {
                output = controller.patrolUnit(patrolUnit.group("x1Coordinate"), patrolUnit.group("y1Coordinate"),patrolUnit.group("x2Coordinate"), patrolUnit.group("y2Coordinate"));
                System.out.println(output);
            }
            else if (setMode.find()) {
                output = controller.setMode(setMode.group("xCoordinate"), setMode.group("yCoordinate"),setMode.group("mode"), setMode.group("type"));
                System.out.println(output);
            }
            else if (groundAttack.find()) {
                output = controller.groundAttack(groundAttack.group("xCoordinate"), groundAttack.group("yCoordinate"));
                System.out.println(output);
            }
            else if (airAttack.find()) {
                output = controller.airAttack(airAttack.group("xCoordinate"), airAttack.group("yCoordinate"));
                System.out.println(output);
            }
            else if (pourOil.find()) {
                output = controller.pourOil(pourOil.group("direction"));
                System.out.println(output);
            }
            else if (digTunnel.find()) {
                output = controller.digTunnel(digTunnel.group("xCoordinate"), digTunnel.group("yCoordinate"));
                System.out.println(output);
            }
            else if (buildEquipment.find()) {
                output = controller.buildEquipment(buildEquipment.group("equipmentName"));
                System.out.println(output);
            }
            else if (disbandUnit.find()) {
                output = controller.disbandUnit();
                System.out.println(output);
            } else if (goToShopMenu.find()) {
                ShopMenu menu = new ShopMenu();
                menu.run(scanner, controller.getNewGame());
                System.out.println("now you are in the game menu");
            } else if (goToTradeMenu.find()) {
                TradeMenu menu = new TradeMenu();
                menu.run(scanner, controller.getNewGame());
                System.out.println("now you are in the game menu");
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
