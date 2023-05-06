package model.Property;

public class Food extends Property {
    private FoodType type;

    public Food(FoodType type, int value) {
        super(value);
        this.type = type;
    }

    public FoodType getType() {
        return type;
    }
}

