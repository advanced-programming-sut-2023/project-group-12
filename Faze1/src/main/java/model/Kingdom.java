package model;

import model.Building.Building;
import model.Building.Storage;
import model.Equipment.Equipment;
import model.Property.*;
import model.people.Unit;
import model.people.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {

    private int population;

    private int unemployed;

    private ArrayList<Trade> trades = new ArrayList<>();

    private ArrayList<Trade> tradesHistory = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    private ArrayList<Soldier> engineers = new ArrayList<Soldier>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();

    private ArrayList<Storage> stables = new ArrayList<Storage>();

    private ArrayList<Storage> weapons = new ArrayList<>();

    private ArrayList<Storage> defensiveWeapons = new ArrayList<>();

    private ArrayList<Equipment> equipments = new ArrayList<>();

    private ArrayList<Storage> stockPiles = new ArrayList<>();

    private ArrayList<Storage> foodStockPiles = new ArrayList<>();

    private User owner;

    private double gold;
    private int fearRate;
    private int taxRate;
    private int foodRate;
    private int popularity;

    public int getPopularity() {
        return popularity;
    }

    public Kingdom(User owner){
        this.owner = owner;
        gold = 500;
        fearRate = 0;
        taxRate = 0;
        popularity = 0;
        foodRate = 0;
        population = 15;
        unemployed = 15;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public double getGold() {
        return gold;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public int getPopulation() {
        return population;
    }

    public void addPopulation(int amount){
        population += amount;
    }

    public void addEngineer(Soldier engineer){
        engineers.add(engineer);
    }

    public void addSoldier(Soldier soldier){
        soldiers.add(soldier);
    }

    public void addUnit(Unit unit){
        units.add(unit);
    }

    public int getAllHorses(){
        int total = 0;
        for(Storage storage : stables){
            total += storage.getStored();
        }
        return total;
    }



    public void addGold(double amount){
        gold += amount;
    }

    public ArrayList<Soldier> getEngineers() {
        return engineers;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public ArrayList<Storage> getDefensiveWeapons() {
        return defensiveWeapons;
    }

    public ArrayList<Storage> getStables() {
        return stables;
    }

    public ArrayList<Storage> getWeapons() {
        return weapons;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public int getUnemployed() {
        return unemployed;
    }

    public void decreaseWeapons(Weapon weapon) {
        for (Storage storage: weapons) {
            if(weapon.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((Weapon)property).getWeaponType() == weapon.getWeaponType()) {
                        property.addValue(-Math.min(weapon.getValue(), property.getValue()));
                        weapon.addValue(-property.getValue());
                    }
                }
            }

        }
    }

    public void decreaseDefensiveWeapons(DefensiveWeapon defensiveWeapon) {
        for (Storage storage: defensiveWeapons) {
            if(defensiveWeapon.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((DefensiveWeapon)property).getDefenseType() == defensiveWeapon.getDefenseType()) {
                        property.addValue(-defensiveWeapon.getValue());
                        defensiveWeapon.addValue(-property.getValue());

                    }
                }
            }

        }
    }


    public int getNumberOfProperties(Property property) {
        if(property instanceof Resources){
            return getNumberOfResources(((Resources)property));
        }
        if(property instanceof Weapon){
            return getNumberOfWeapon(((Weapon)property));
        }
        if(property instanceof DefensiveWeapon) {
            return getNumberOfDefensiveWeapon(((DefensiveWeapon) property));
        }
        if (property instanceof Food){
            return getNumberOfFood(((Food)property));
        }
        return -2;
    }

    private int getNumberOfResources(Resources resource) {
        int amount = 0;
        for (Storage storage: stockPiles) {
            for (Property property : storage.getBalance()){
                if(((Resources)property).getResourceType() == resource.getResourceType()){
                    amount += property.getValue();
                }
            }
        }
        return amount;
    }

    private int getNumberOfWeapon(Weapon weapon) {
        int amount = 0;
        for (Storage storage: weapons) {
            for (Property property : storage.getBalance()){
                if(((Weapon)property).getWeaponType() == weapon.getWeaponType()){
                    amount += property.getValue();
                }
            }
        }
        return amount;

    }

    private int getNumberOfDefensiveWeapon(DefensiveWeapon defensiveWeapon) {
        int amount = 0;
        for (Storage storage: defensiveWeapons) {
            for (Property property : storage.getBalance()){
                if(((DefensiveWeapon)property).getDefenseType() == defensiveWeapon.getDefenseType()){
                    amount += property.getValue();
                }
            }
        }
        return amount;
    }

    private int getNumberOfFood(Food food) {
        int amount  = 0;
        for (Storage storage: foodStockPiles) {
            for (Property property : storage.getBalance()){
                if(((Food)property).getType() == food.getType()){
                    amount += property.getValue();
                }
            }
        }
        return amount;
    }

    public void addToProperty(Property property){
        if(property instanceof Resources){
            addToResources((Resources)property);
        }
        if(property instanceof Weapon){
            addToWeapon((Weapon)property);
        }
        if(property instanceof DefensiveWeapon) {
            addToDefensiveWeapon((DefensiveWeapon)property);
        }
        if (property instanceof Food){
            addToFood((Food)property);
        }
    }

    private void addToFood(Food food) {
        for (Storage storage: foodStockPiles){
            if(food.getValue() != 0) {
                for (Property property : storage.getBalance()) {
                    if (((Food) property).getType() == food.getType()) {
                        property.addValue(Math.min(storage.getCapacity() - storage.getStored(), food.getValue()));
                        food.addValue(-Math.min(storage.getCapacity() - storage.getStored(), food.getValue()));
                    }
                }
            }
        }
    }

    private void addToDefensiveWeapon(DefensiveWeapon defensiveWeapon) {
        for (Storage storage: defensiveWeapons){
            if(defensiveWeapon.getValue() != 0) {
                for (Property property : storage.getBalance()) {
                    if (((DefensiveWeapon) property).getDefenseType() == defensiveWeapon.getDefenseType()) {
                        property.addValue(Math.min(storage.getCapacity() - storage.getStored(), defensiveWeapon.getValue()));
                        defensiveWeapon.addValue(-Math.min(storage.getCapacity() - storage.getStored(), defensiveWeapon.getValue()));
                    }
                }
            }
        }
    }

    private void addToWeapon(Weapon weapon) {
        for (Storage storage: weapons){
            if(weapon.getValue() != 0) {
                for (Property property : storage.getBalance()) {
                    if (((Weapon) property).getWeaponType() == weapon.getWeaponType()) {
                        property.addValue(Math.min(storage.getCapacity() - storage.getStored(), weapon.getValue()));
                        weapon.addValue(-Math.min(storage.getCapacity() - storage.getStored(), weapon.getValue()));
                    }
                }
            }
        }
    }

    private void addToResources(Resources resource){
        for (Storage storage: stockPiles){
            if(resource.getValue() != 0) {
                for (Property property : storage.getBalance()) {
                    if (((Resources) property).getResourceType() == resource.getResourceType()) {
                        property.addValue(Math.min(storage.getCapacity() - storage.getStored(), resource.getValue()));
                        resource.addValue(-Math.min(storage.getCapacity() - storage.getStored(), resource.getValue()));
                    }
                }
            }
        }
    }

    //todo: pay resources
    public void spendProperties(Property property){
        if(property instanceof Resources){
            spendResources((Resources) property);
        }
        if(property instanceof Weapon){
            spendWeapon((Weapon) property);
        }
        if(property instanceof DefensiveWeapon) {
            spendDefensiveWeapon((DefensiveWeapon) property);
        }
        if (property instanceof Food){
            spendFood((Food) property);
        }
    }

    private void spendFood(Food food) {
        for (Storage storage: foodStockPiles) {
            if(food.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((Food)property).getType() == food.getType()) {
                        int keepResource = food.getValue();
                        food.addValue(-Math.min(property.getValue(), food.getValue()));
                        property.addValue(-Math.min(property.getValue(), keepResource));
                    }
                }
            }

        }
    }

    private void spendDefensiveWeapon(DefensiveWeapon defensiveWeapon) {
        for (Storage storage: defensiveWeapons) {
            if(defensiveWeapon.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((DefensiveWeapon)property).getDefenseType() == defensiveWeapon.getDefenseType()) {
                        int keepResource = defensiveWeapon.getValue();
                        defensiveWeapon.addValue(-Math.min(property.getValue(), defensiveWeapon.getValue()));
                        property.addValue(-Math.min(property.getValue(), keepResource));
                    }
                }
            }

        }
    }

    private void spendWeapon(Weapon weapon) {
        for (Storage storage: weapons) {
            if(weapon.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((Weapon)property).getWeaponType() == weapon.getWeaponType()) {
                        int keepResource = weapon.getValue();
                        weapon.addValue(-Math.min(property.getValue(), weapon.getValue()));
                        property.addValue(-Math.min(property.getValue(), keepResource));
                    }
                }
            }

        }
    }

    private void spendResources(Resources resource) {
        for (Storage storage: stockPiles) {
            if(resource.getValue() > 0) {
                for(Property property: storage.getBalance()) {
                    if(((Resources)property).getResourceType() == resource.getResourceType()) {
                        int keepResource = resource.getValue();
                        resource.addValue(-Math.min(property.getValue(), resource.getValue()));
                        property.addValue(-Math.min(property.getValue(), keepResource));
                    }
                }
            }

        }
    }

    public Property getPropertyByName(String name) {
        for (FoodType foodType : FoodType.values()) {
            if (foodType.name().toLowerCase().equals(name))
                for (Storage storage : getFoodStockPiles())
                    if (storage.getFoodByFoodType(foodType) != null)
                        return storage.getFoodByFoodType(foodType);
        }

        for (WeaponType weaponType : WeaponType.values()) {
            if (weaponType.name().toLowerCase().equals(name))
                for (Storage storage : getWeapons())
                    if (storage.getWeaponByWeaponType(weaponType) != null)
                        return storage.getWeaponByWeaponType(weaponType);
        }

        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name().toLowerCase().equals(name))
                for (Storage storage : getStockPiles())
                    if (storage.getResourcesByResourceType(resourceType) != null)
                        return storage.getResourcesByResourceType(resourceType);
        }

        return null;
    }

    public ArrayList<Trade> getTradesHistory() {
        return tradesHistory;
    }

    public void setTradesHistory(Trade trades) {
        this.tradesHistory.add(trades);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Trade trades) {
        this.trades.add(trades);
    }

    public ArrayList<Storage> getFoodStockPiles() {
        return foodStockPiles;
    }

    public ArrayList<Storage> getStockPiles() {
        return stockPiles;
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }
}
