package controller;

import model.Game;
import model.Kingdom;
import model.Property.*;

public class ShopMenuController {
    public static String buyOrSell (String action, String name, int amount) {

        Property property = Game.getYetGame().getCurrentKingdom().getPropertyByName(name);

        if (property != null) {
            if (action.equals("buy")) {
                if (property instanceof Food) {
                    if (((Food) property).getType().getBuyPrice() * amount <= Game.getYetGame().getCurrentKingdom().getGold()) {
                        property.addValue(amount);
                        Game.getYetGame().getCurrentKingdom().addGold(-1 * ((Food) property).getType().getBuyPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Weapon) {
                    if (((Weapon) property).getWeaponType().getBuyPrice() * amount <= Game.getYetGame().getCurrentKingdom().getGold()) {
                        property.addValue(amount);
                        Game.getYetGame().getCurrentKingdom().addGold(-1 * ((Weapon) property).getWeaponType().getBuyPrice() * amount);
                        return action + " " + name + " successfully complected";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Resources) {
                    if (((Resources) property).getResourceType().getBuyPrice() * amount <= Game.getYetGame().getCurrentKingdom().getGold()) {
                        property.addValue(amount);
                        Game.getYetGame().getCurrentKingdom().addGold(-1 * ((Resources) property).getResourceType().getBuyPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
            } else if (action.equals("sell")) {
                if (property instanceof Food) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Game.getYetGame().getCurrentKingdom().addGold(((Food) property).getType().getSellPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Weapon) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Game.getYetGame().getCurrentKingdom().addGold(((Weapon) property).getWeaponType().getSellPrice() * amount);
                        return action + " " + name + " successfully complected";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Resources) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Game.getYetGame().getCurrentKingdom().addGold(((Resources) property).getResourceType().getSellPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
            }
            return "";
        }

        return "Wrong name";
    }
    public static String showPriceList() {
        System.out.println("Foods :");
        for (FoodType foodType : FoodType.values()) {
            System.out.println("\t" + foodType.name().toLowerCase() +
                    " : buy price = " + foodType.getBuyPrice() + ", sell price = " + foodType.getSellPrice());
        }

        System.out.println("Resources :");
        for (ResourceType resourceType : ResourceType.values()) {
            System.out.println("\t" + resourceType.name().toLowerCase() +
                    " : buy price = " + resourceType.getBuyPrice() + ", sell price = " + resourceType.getSellPrice());
        }

        System.out.println("Weapons :");
        for (WeaponType weaponType : WeaponType.values()) {
            System.out.println("\t" + weaponType.name().toLowerCase() +
                    " : buy price = " + weaponType.getBuyPrice() + ", sell price = " + weaponType.getSellPrice());
        }
        return "";
    }


}
