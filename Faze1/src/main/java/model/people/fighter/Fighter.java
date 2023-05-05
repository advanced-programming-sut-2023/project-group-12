package model.people.fighter;

public class Fighter {

    private UnitType unitType;
    private int xCoordinate;
    private int yCoordinate;

    public Fighter(int xCoordinate, int yCoordinate, UnitType unitType) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.unitType = unitType;
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

    public UnitType getType() {
        return unitType;
    }
}
