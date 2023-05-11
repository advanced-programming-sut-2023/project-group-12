package model;

import model.Building.Building;
import model.Building.Storage;
import model.Property.*;
import model.people.Unit;
import model.people.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {
    private int population;

    private int unemployed;
    private ArrayList<Building> buildings = new ArrayList<Building>();

    private ArrayList<Soldier> engineers = new ArrayList<Soldier>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    private ArrayList<Soldier> soldiers = new ArrayList<Soldier>();

    private ArrayList<Storage> stables = new ArrayList<Storage>();

    private ArrayList<Storage> weapons = new ArrayList<>();

    private ArrayList<Storage> defensiveWeapons = new ArrayList<>();
    private User owner;

    private int gold;
    private int fearRate;
    private int texRate;
    private int foodRate;
    private ArrayList<Property> allProperties = new ArrayList<>();
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
            total += storage.getAllBalance();
        }
        return total;
    }

    public int getAllWeaponByWeaponType(WeaponType weaponType){
        int total = 0;
        for (Storage storage: weapons) {
            total += storage.getBalance().get(weaponType);
        }
        return total;
    }

    public int getAllDefensiveWeaponByDefenseType(DefenseType defenseType){
        int total = 0;
        for(Storage storage: defensiveWeapons){
            total += storage.getBalance().get(defenseType);
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

    public void decreaseWeapons(WeaponType weaponType ,int amount) {
        for (Storage storage: weapons) {
            if(amount > 0) {
                amount -= storage.getBalance().get(weaponType);
                storage.getBalance().put(new Weapon(weaponType, 1), Math.max(0, storage.getBalance().get(weaponType) - amount));
            }

        }
    }

    public void decreaseDefensiveWeapons(DefenseType defensiveType ,int amount) {
        for (Storage storage: defensiveWeapons) {
            if(amount > 0) {
                amount -= storage.getBalance().get(defensiveType);
                storage.getBalance().put(new DefensiveWeapon(defensiveType, 1), Math.max(0, storage.getBalance().get(defensiveType) - amount));
            }

        }
    }
}
