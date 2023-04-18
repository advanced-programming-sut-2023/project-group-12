package model.Building;

import model.Cell;

import java.util.HashMap;

public class Building {

    protected HashMap<String,Integer> costs;
    //TODO: change 5 integers for costs to a hashmap
    {
        costs.put("gold",0);
        costs.put("wood",0);
        costs.put("stone",0);
        costs.put("iron",0);
        costs.put("bitumen",0);
    }
    protected int hitPoint;
    protected int initialHitPoint;
    // TODO: make initial... final!
    private Cell location;
    private String type;
    private int workersNeeded;
    protected String compatibleCellTexture;
    protected boolean repairable;
    public void repair () {

    }
}
