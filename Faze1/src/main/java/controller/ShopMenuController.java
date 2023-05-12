package controller;

import model.Kingdom;
import model.Property.*;

public class ShopMenuController {
    public static String buyOrSell (String action, String name, int amount) {

        Property property = Kingdom.yetKingdom.getPropertyByName(name);

        if (property != null) {
            if (action.equals("buy")) {
                if (property instanceof Food) {
                    if (((Food) property).getType().getBuyPrice() * amount <= Kingdom.yetKingdom.getGold()) {
                        property.addValue(amount);
                        Kingdom.yetKingdom.addGold(-1 * ((Food) property).getType().getBuyPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Weapon) {
                    if (((Weapon) property).getWeaponType().getBuyPrice() * amount <= Kingdom.yetKingdom.getGold()) {
                        property.addValue(amount);
                        Kingdom.yetKingdom.addGold(-1 * ((Weapon) property).getWeaponType().getBuyPrice() * amount);
                        return action + " " + name + " successfully complected";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Resources) {
                    if (((Resources) property).getResourceType().getBuyPrice() * amount <= Kingdom.yetKingdom.getGold()) {
                        property.addValue(amount);
                        Kingdom.yetKingdom.addGold(-1 * ((Resources) property).getResourceType().getBuyPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
            } else if (action.equals("sell")) {
                if (property instanceof Food) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Kingdom.yetKingdom.addGold(((Food) property).getType().getSellPrice() * amount);
                        return action + " " + name + " successfully completed";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Weapon) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Kingdom.yetKingdom.addGold(((Weapon) property).getWeaponType().getSellPrice() * amount);
                        return action + " " + name + " successfully complected";
                    } else
                        return "Out of money";
                }
                else if (property instanceof Resources) {
                    if (amount <= property.getValue()) {
                        property.addValue(-1 * amount);
                        Kingdom.yetKingdom.addGold(((Resources) property).getResourceType().getSellPrice() * amount);
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
