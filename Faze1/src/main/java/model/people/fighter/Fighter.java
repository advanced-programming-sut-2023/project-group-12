package model.people.fighter;

public class Fighter {

    private Type type;
    private int xCoordinate;
    private int yCoordinate;

    public Fighter(int xCoordinate, int yCoordinate, Type type) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = type;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Type getType() {
        return type;
    }
}
