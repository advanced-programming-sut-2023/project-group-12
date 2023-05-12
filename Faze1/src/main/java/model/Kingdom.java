package model;

import model.Building.Building;
import model.Building.Storage;
import model.Property.*;
import model.people.Unit;
import model.people.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {

    public static Kingdom yetKingdom;

    private int population;

    private int unemployed;
    private ArrayList<Building> buildings = new ArrayList<Building>();

    private ArrayList<Soldier> engineers = new ArrayList<Soldier>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();

    private ArrayList<Storage> stables = new ArrayList<Storage>();

    private ArrayList<Storage> weapons = new ArrayList<>();

    private ArrayList<Storage> defensiveWeapons = new ArrayList<>();

    private ArrayList<Storage> stockPiles = new ArrayList<>();

    private ArrayList<Storage> foodStockPiles = new ArrayList<>();
    private User owner;
    private int gold;
    private int fearRate;
    private int texRate;
    private int foodRate;





    public void setPopularity(int popularity) {

    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getTaxRate() {
        return texRate;
    }

    public void setTaxRate(int texRate) {
        this.texRate = texRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getGold() {
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

    public int getAllWeaponByWeaponType(WeaponType weaponType){
        int total = 0;
        for (Storage storage: weapons) {
            for(int i=0; i < storage.getBalance().size(); i++){
                if(((Weapon)storage.getBalance().get(i)).getWeaponType() == weaponType){
                    total += storage.getBalance().get(i).getValue();
                }
            }

        }
        return total;
    }

    public int getAllDefensiveWeaponByDefenseType(DefenseType defenseType){
        int total = 0;
        for(Storage storage: defensiveWeapons){
            for(int i=0; i < storage.getBalance().size(); i++){
                if(((DefensiveWeapon)storage.getBalance().get(i)).getDefenseType() == defenseType){
                    total += storage.getBalance().get(i).getValue();
                }
            }
        }
        return total;
    }

    public void addGold(int amount){
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
        for (Property myProperty: allProperties) {
            if(myProperty instanceof Food){
                if(((Food) myProperty).getType() == food.getType()){
                    myProperty.addValue(food.getValue());
                }
            }
        }
    }

    private void addToDefensiveWeapon(DefensiveWeapon defensiveWeapon) {
        for (Property myProperty: allProperties) {
            if(myProperty instanceof DefensiveWeapon){
                if(((DefensiveWeapon) myProperty).getDefenseType() == defensiveWeapon.getDefenseType()){
                    myProperty.addValue(defensiveWeapon.getValue());
                }
            }
        }
    }

    private void addToWeapon(Weapon weapon) {
        for (Property myProperty: allProperties) {
            if(myProperty instanceof Weapon){
                if(((Weapon) myProperty).getWeaponType() == weapon.getWeaponType()){
                    myProperty.addValue(weapon.getValue());
                }
            }
        }
    }

    private void addToResources(Resources resource){
        for (Property myProperty: allProperties) {
            if(myProperty instanceof Resources){
                if(((Resources) myProperty).getResourceType() == resource.getResourceType()){
                    myProperty.addValue(resource.getValue());
                }
            }
        }
    }

    public ArrayList<Storage> getFoodStockPiles() {
        return foodStockPiles;
    }

    public ArrayList<Storage> getStockPiles() {
        return stockPiles;
    }

    public Property getPropertyByName(String name) {
        for (FoodType foodType : FoodType.values()) {
            if (foodType.name().toLowerCase().equals(name))
                for (Storage storage : Kingdom.yetKingdom.getFoodStockPiles())
                    if (storage.getFoodByFoodType(foodType) != null)
                        return storage.getFoodByFoodType(foodType);
        }

        for (WeaponType weaponType : WeaponType.values()) {
            if (weaponType.name().toLowerCase().equals(name))
                for (Storage storage : Kingdom.yetKingdom.getWeapons())
                    if (storage.getWeaponByWeaponType(weaponType) != null)
                        return storage.getWeaponByWeaponType(weaponType);
        }

        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name().toLowerCase().equals(name))
                for (Storage storage : Kingdom.yetKingdom.getStockPiles())
                    if (storage.getResourcesByResourceType(resourceType) != null)
                        return storage.getResourcesByResourceType(resourceType);
        }

        return null;
    }
}
