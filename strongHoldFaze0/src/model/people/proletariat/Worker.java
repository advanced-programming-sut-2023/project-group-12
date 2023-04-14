package model.people.proletariat;

import model.Kingdom;
import model.people.Unit;

public class Worker extends Unit {
    //private Building host;

    public Worker(Kingdom homeland, int life
            //, Building host
    ) {
        super(homeland, life);
        //this.host = host;
    }
}
