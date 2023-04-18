package model;

import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {

    private ArrayList<Unit> people;

    private int fearRate;
    private int texRate;
    private int foodRate;
    private HashMap<String ,Integer> data;
    {
        data = new HashMap<>();
        data.put("popularity",0);
        data.put("gold",0);
        data.put("food",0);
        data.put("iron",0);
        data.put("stone",0);
        data.put("wood",0);
        data.put("bitumen",0);
    }

    public void setPopularity() {

    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getTexRate() {
        return texRate;
    }

    public void setTexRate(int texRate) {
        this.texRate = texRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }
}
