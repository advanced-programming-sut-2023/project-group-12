package model.people;

import model.Kingdom;

public class Unit {
    private Kingdom homeland;
    private int hitPoint;


    public Unit(Kingdom homeland, int hitPoint) {
        this.homeland = homeland;
        this.hitPoint = hitPoint;
    }
}
