package model;

public class Cost {
    private int gold;
    private int wood;
    private int iron;
    private int stone;
    private int pitch; // todo : is it the correct word?

    public Cost(int gold, int wood, int iron, int stone, int pitch) {
        this.gold = gold;
        this.wood = wood;
        this.iron = iron;
        this.stone = stone;
        this.pitch = pitch;
    }

    public int getGold() {
        return gold;
    }

    public int getWood() {
        return wood;
    }

    public int getIron() {
        return iron;
    }

    public int getStone() {
        return stone;
    }

    public int getPitch() {
        return pitch;
    }
}
