package model.people;

import model.Kingdom;

abstract public class Unit {

    private Kingdom homeland;
    private int life;

    public Unit(Kingdom homeland, int life) {
        this.homeland = homeland;
        this.life = life;
    }
}
