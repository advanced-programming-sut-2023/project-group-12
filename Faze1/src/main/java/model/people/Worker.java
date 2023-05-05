package model.people;

import model.Building.Building;
import model.Kingdom;
import model.people.Unit;

public class Worker extends Unit {
    private Building workPlace;

    public Worker(Kingdom homeland, int hitPoint) {
        super(homeland, hitPoint);
        workPlace = null;
    }

    public void setWorkPlace(Building workPlace) {
        this.workPlace = workPlace;
    }

    public Building getWorkPlace() {
        return workPlace;
    }
}
