package model.Property;

public class Property {
    private int value;
    public Property(int value) {
        this.value = value;
    }

    public void addValue(int amount){
        value += amount;
    }

    public int getValue() {
        return value;
    }



}
