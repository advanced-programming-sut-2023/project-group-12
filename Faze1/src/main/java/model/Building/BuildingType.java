package model.Building;


import model.Kingdom;
import model.ResourceType;
import model.TextureType;

public enum BuildingType {
    //name, class, hitPoint, goldPrice, resourcePrice, resourceCount, workerCount, engineerCount
    SMALL_STONE_GATEHOUSE("Small stone gatehouse", Traps.class, 1000, 0, null, 0, 0, 0, true),//darvaze sangi koochak
    BIG_STONE_GATEHOUSE("big stone gatehouse", Traps.class, 2000, 0, ResourceType.STONE, 20, 0, 0,true),//darvaze sangi bozorg
    KEEP("keep", Building.class, 0, 0, null, 0, 0,0, true),//maghar
    DRAWBRIDGE("drawbridge", Traps.class, 0,0, ResourceType.WOOD, 10, 0,0, true),//pol motaharrek
    LOOKOUT_TOWER("lookout tower", Tower.class, 1300, 0, ResourceType.STONE, 10, 0, 0, true),//borj didebani
    PERIMETER_TOWER("perimeter tower", Tower.class, 1000, 0 , ResourceType.STONE, 10, 0 ,0, true),//borj mohit
    DEFENCE_TURRET("defence turret", Tower.class, 1200, 0, ResourceType.STONE, 15, 0 ,  0, true), //borjak defaei
    SQUARE_TOWER("square tower", Tower.class, 1600, 0, ResourceType.STONE, 35, 0 , 0, true), //borj morabaei
    CIRCLE_TOWER("circle tower", Tower.class,  2000, 0, ResourceType.STONE, 40, 0,  0, true), //borj dayere
    ARMOURY("armoury", Storage.class,500, 0, ResourceType.WOOD, 5, 0,0, true), //aslahe khane
    BARRACK("barrack", UnitCreation.class, 500, 0, ResourceType.STONE, 15, 0,0, true), //SARBAZ KHANE
    MERCENARY_POST("mercenary post", UnitCreation.class, 500, 0, ResourceType.WOOD, 10, 0,0, true), //SARBAZ KHANE MOZDOORAN
    ENGINEER_GUILD("engineer guild", Building.class, 500, 100, ResourceType.WOOD, 10, 0,0, true), //SENF MOHANDESAN
    KILLING_PIT("killing pit", Traps.class, 0, 0, ResourceType.WOOD, 6, 0 ,0,true), //GODAL KOSHTAR
    INN("inn", ProductionCenter.class,300, 100, ResourceType.WOOD, 20, 1, 0, false), //MOSAFER KHANE
    MILL("mill", ProductionCenter.class, 300, 0, ResourceType.WOOD, 20, 3, 0, false), //ASIYAB
    IRON_MINE("iron mine", ProductionCenter.class, 100, 0, ResourceType.WOOD, 20, 2, 0,false), //MADAN AHAN
    MARKET("market", ProductionCenter.class, 300, 0, ResourceType.WOOD, 5, 1, 0, false), //FORUSHGAH
    OX_TETHER("ox tether", Storage.class, 100, 0, ResourceType.WOOD, 5, 1, 0, false), // AFSAR GAV
    PITCH_RIG("pitch rig", ProductionCenter.class, 100, 0, ResourceType.WOOD, 20, 1, 0,false), //DACAL GHIR
    QUARRY("quarry", ProductionCenter.class, 300, 0, ResourceType.WOOD, 20, 3, 0, false), //MADAN SANG
    STOCKPILE("stockpile", Storage.class, 500, 0, null, 0, 0, 0, false), //ANBAR
    WOODCUTTER("woodcutter", ProductionCenter.class,100, 0, ResourceType.WOOD, 3,  1, 0, false), //CHOOB BOR
    HOVEL("hovel", ProductionCenter.class, 100, 0, ResourceType.WOOD, 6, 0 ,0, false), //KHANE
    CHURCH("church",ProductionCenter.class, 800, 250, null, 0, 0, 0, false), //KELISA
    CATHEDRAL("cathedral", ProductionCenter.class,1200, 1000, null, 0,0,0, false), //KELISA JAME
    ARMOURER("armourer", ProductionCenter.class,300, 100, ResourceType.WOOD, 20, 1, 0, false), //ZEREH SAZI
    BLACKSMITH("blacksmith", ProductionCenter.class, 300, 100, ResourceType.WOOD, 20, 1, 0, false), //SAKHTEMAN AHANGARY
    FLETCHER( "fletcher",ProductionCenter.class, 300, 100, ResourceType.WOOD, 20, 1, 0, false), //KAMAN SAZI
    POLE_TURNER("pole_turner",  ProductionCenter.class, 300, 100, ResourceType.WOOD, 10, 1, 0,false), //NEYZE SAZI
    OIL_SMELTER("oil smelter", ProductionCenter.class,300, 100, ResourceType.STONE, 10, 0, 1, true), //KARKHANE ZOB
    PITCH_DITCH("pitch ditch", ProductionCenter.class, 300, 0, ResourceType.PITCH, 2, 0, 0, true), //KHANDAGH GHIR  ** 2 PITCH PER 5 SQUARE
    CAGED_WAR_DOGES("caged war dogs", Traps.class, 100, 100, ResourceType.WOOD, 10, 0, 0, true), //GHAFASE SAG HAYE JANGI
    SIEGE_TENT("siege tent", Building.class, 100, 0, null, 0, 0, 1, true), //CHADOR MOHASERE
    STABLE("stable", ProductionCenter.class, 300,400, ResourceType.WOOD, 20, 0, 0, true), //ESTABL
    APPLE_GARDEN("apple garden", ProductionCenter.class, 100, 0, ResourceType.WOOD, 5, 1,0, false), //BAGH SIB
    DAIRY_PRODUCTS("dairy products", ProductionCenter.class, 100, 0, ResourceType.WOOD, 10, 1, 0, false), //TOLIDAT LABANI
    HOPS_FARM("hops farm", ProductionCenter.class, 100, 0, ResourceType.WOOD, 15, 1,0, false), //MAZRAE JO
    HUNTER_POST("hunter post", ProductionCenter.class, 300, 0, ResourceType.WOOD, 5, 1,0, false), //POST SHEKAR
    WHEAT_FARM("wheat farm", ProductionCenter.class, 300, 0, ResourceType.WOOD, 15, 1, 0, false), //MAZRAE GANDOM
    BAKERY("bakery", ProductionCenter.class, 300, 0 ,ResourceType.WOOD, 10, 1, 0, false), //NANVAEI
    BREWERY("brewery",ProductionCenter.class, 300, 0 ,ResourceType.WOOD, 10, 1, 0, false), //ABJO SAZI
    FOOD_STOCKPILE("food stockpile", Storage.class, 500, 0, ResourceType.WOOD, 5, 0,0, false) ,//ANBAR GHAZA

    TREE("tree", ProductionCenter.class, 100, 0, null, 0, 0,0, false)
    ;
    private String buildingName;

    private Class <?> BuildingClass;

    private int hitPoint;

    private int goldPrice;

    private ResourceType resourcePrice;

    private int resourceCount;

    private int workerCount;

    private int engineerCount;

    private boolean isPartOfCastle;

    BuildingType(String buildingName, Class<?> BuildingClass, int hitPoint,int goldPrice, ResourceType resourcePrice, int resourceCount, int workerCount, int engineerCount, boolean isPartOfCastle) {
        this.buildingName = buildingName;
        this.BuildingClass = BuildingClass;
        this.hitPoint = hitPoint;
        this.goldPrice = goldPrice;
        this.resourcePrice = resourcePrice;
        this.resourceCount = resourceCount;
        this.workerCount = workerCount;
        this.engineerCount = engineerCount;
        this.isPartOfCastle = isPartOfCastle;
    }

    public static BuildingType getBuildingTypeByName(String type){
        BuildingType buildingType = null;
        for(BuildingType building : BuildingType.values())
            if (building.buildingName.equals(type))
                buildingType = building;
        return buildingType;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Class<?> getBuildingClass() {
        return BuildingClass;
    }

    public int getEngineerCount() {
        return engineerCount;
    }

    public int getGoldPrice() {
        return goldPrice;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public ResourceType getResourcePrice() {
        return resourcePrice;
    }

    public String getBuildingName() {
        return buildingName;
    }
    public boolean getIsPartOfCastle(){
        return isPartOfCastle;
    }

    public static Building getBuildingByBuildingType(BuildingType buildingType, Kingdom currentKingdom){
        Building building;
        if(buildingType.getBuildingClass() == Tower.class){
            building = new Tower(buildingType, currentKingdom);
        } else if (buildingType.getBuildingClass() == Storage.class) {
            building = new Storage(buildingType, currentKingdom);
        } else if (buildingType.getBuildingClass() == ProductionCenter.class) {
            building = new ProductionCenter(buildingType, currentKingdom);
        }else if (buildingType.getBuildingClass() == Wall.class){
            building = new Wall(buildingType, currentKingdom);
        }else if (buildingType.getBuildingClass() == Traps.class){
            building = new Traps(buildingType, currentKingdom);
        }else {
            building = new Building(buildingType, currentKingdom);
        }
        return building;
    }

    public static boolean isBuildingMatchTexture(BuildingType buildingType, TextureType textureType){
        if(buildingType == QUARRY)
            return textureType == TextureType.STONE;
        else if(buildingType == PITCH_RIG)
            return textureType == TextureType.PLAIN;
        else if(buildingType == IRON_MINE)
            return textureType == TextureType.IRON;
        else if(buildingType == OIL_SMELTER)
            return textureType == TextureType.OIL;
        else if(textureType.isWateryLand())
            return false;
        else if(textureType == TextureType.EARTH || textureType == TextureType.GRASS ||
                textureType == TextureType.MEADOW)
            return true;
        else if(buildingType == APPLE_GARDEN || buildingType == HOPS_FARM ||
                buildingType == WHEAT_FARM || buildingType == MILL)
            return false;
        else if(buildingType == TREE)
            return textureType == TextureType.DENSE_MEADOW;
        else if(buildingType.getBuildingClass() == Tower.class)
            return textureType != TextureType.ROCK;
        else return textureType == TextureType.GRAVEL || textureType == TextureType.STONE ||
                    textureType == TextureType.DENSE_MEADOW;
    }

}
