package model.people;

import model.Kingdom;

abstract public class Unit {

    private Kingdom homeland;
    private int hitPoint;

    public Unit(Kingdom homeland, int hitPoint) {
        this.homeland = homeland;
        this.hitPoint = hitPoint;
    }
}
