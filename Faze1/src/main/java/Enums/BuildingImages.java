package Enums;

public enum BuildingImages {
    BARRACKS("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Barracks.png"),
    Barracks2("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\barracks2.png"),
    Barracks3("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\barracks3.png"),
    BARRACKS_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Barracks-menu.png"),
    CASTLE("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\castle.png"),
    CASTLE1("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\castle1.png"),
    CASTLE2("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\castle2.png"),
    CASTLE3("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\castle3.png"),
    CASTLE4("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\castle4.png"),
    COLLECTION26("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\collection26.png"),
    COLLECTION27("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\collection27.png"),
    COLLECTION29("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\collection29.png"),
    FARM("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Farm.png"),
    FARM2("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\farm2.png"),
    FARM_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Farm-menu.png"),
    MARKET("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Market.png"),
    MARKET_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Market-menu.png"),
    MOSQUE("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\mosque.png"),
    PALACE("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Palace.png"),
    PORT("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Port.png"),
    PORT_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Port-menu.png"),
    QUARRY("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Quarry.png"),
    QUARRY2("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Quarry2.png"),
    QUARRY_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\Quarry-menu.png"),
    WOODCUTTER("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\WoodCutter.png"),
    WOODCUTTER_MENU("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\WoodCutter-menu.png"),
    WORKSHOP("D:\\matin\\secondSemester\\AP\\project\\Faze1\\src\\main\\resources\\buildings\\buildings\\workshop.png"),

;
    private String address;

    BuildingImages (String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
