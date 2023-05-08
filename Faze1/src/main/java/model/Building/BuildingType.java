package model.Building;


import model.Kingdom;
import model.Property.*;
import model.TextureType;
import model.people.Unit;

public enum BuildingType {
    //name, class, hitPoint, goldPrice, resourcePrice, resourceCount, workerCount, engineerCount
    SMALL_STONE_GATEHOUSE("Small stone gatehouse", Gate.class, 1000, 0, null, 0, 0, 0, true, null, null),//darvaze sangi koochak
    BIG_STONE_GATEHOUSE("big stone gatehouse", Gate.class, 2000, 0, ResourceType.STONE, 20, 0, 0,true, null, null),//darvaze sangi bozorg
    KEEP("keep", Tower.class, 0, 0, null, 0, 0,0, true, null, null),//maghar
    DRAWBRIDGE("drawbridge", Gate.class, 0,0, ResourceType.WOOD, 10, 0,0, true, null, null),//pol motaharrek
    LOOKOUT_TOWER("lookout tower", Tower.class, 1300, 0, ResourceType.STONE, 10, 0, 0, true, null, null),//borj didebani
    PERIMETER_TOWER("perimeter tower", Tower.class, 1000, 0 , ResourceType.STONE, 10, 0 ,0, true, null, null),//borj mohit
    DEFENCE_TURRET("defence turret", Tower.class, 1200, 0, ResourceType.STONE, 15, 0 ,  0, true, null, null), //borjak defaei
    SQUARE_TOWER("square tower", Tower.class, 1600, 0, ResourceType.STONE, 35, 0 , 0, true, null, null), //borj morabaei
    CIRCLE_TOWER("circle tower", Tower.class,  2000, 0, ResourceType.STONE, 40, 0,  0, true, null, null), //borj dayere
    ARMOURY("armoury", Storage.class,500, 0, ResourceType.WOOD, 5, 0,0, true, null, null), //aslahe khane
    BARRACK("barrack", UnitCreation.class, 500, 0, ResourceType.STONE, 15, 0,0, true, null, null), //SARBAZ KHANE
    MERCENARY_POST("mercenary post", UnitCreation.class, 500, 0, ResourceType.WOOD, 10, 0,0, true, null, null), //SARBAZ KHANE MOZDOORAN
    ENGINEER_GUILD("engineer guild", Building.class, 500, 100, ResourceType.WOOD, 10, 0,0, true, null, null), //SENF MOHANDESAN
    KILLING_PIT("killing pit", Traps.class, 0, 0, ResourceType.WOOD, 6, 0 ,0,true, null, null), //GODAL KOSHTAR
    //todo abjoo
    INN("inn", ProductionCenter.class,300, 100, ResourceType.WOOD, 20, 1, 0, false, new Resources(ResourceType.ALE, 1), null), //MOSAFER KHANE
    MILL("mill", ProductionCenter.class, 300, 0, ResourceType.WOOD, 20, 3, 0, false, new Resources(ResourceType.WHEAT, 2), new Resources(ResourceType.FLOUR, 1)), //ASIYAB
    IRON_MINE("iron mine", ProductionCenter.class, 100, 0, ResourceType.WOOD, 20, 2, 0,false, null, new Resources(ResourceType.IRON, 10)), //MADAN AHAN
    MARKET("market", Building.class, 300, 0, ResourceType.WOOD, 5, 1, 0, false, null, null), //FORUSHGAH
    OX_TETHER("ox tether", ProductionCenter.class, 100, 0, ResourceType.WOOD, 5, 1, 0, false, null, null), // AFSAR GAV
    PITCH_RIG("pitch rig", ProductionCenter.class, 100, 0, ResourceType.WOOD, 20, 1, 0,false, null, new Resources(ResourceType.PITCH, 3)), //DACAL GHIR
    QUARRY("quarry", ProductionCenter.class, 300, 0, ResourceType.WOOD, 20, 3, 0, false, null, new Resources(ResourceType.STONE, 10)), //MADAN SANG
    STOCKPILE("stockpile", Storage.class, 500, 0, null, 0, 0, 0, false, null, null), //ANBAR
    WOODCUTTER("woodcutter", ProductionCenter.class,100, 0, ResourceType.WOOD, 3,  1, 0, false, null, new Resources(ResourceType.WOOD, 10)), //CHOOB BOR
    //todo any idea about unemployed human maybe resource
    HOVEL("hovel", UnitCreation.class, 100, 0, ResourceType.WOOD, 6, 0 ,0, false, null, null), //KHANE
    CHURCH("church", UnitCreation.class, 800, 250, null, 0, 0, 0, false, null, null), //KELISA
    CATHEDRAL("cathedral", UnitCreation.class,1200, 1000, null, 0,0,0, false, null, null), //KELISA JAME
    ARMOURER("armourer", ProductionCenter.class,300, 100, ResourceType.WOOD, 20, 1, 0, false, new Resources(ResourceType.IRON, 6), new DefensiveWeapon(DefenseType.METAL_ARMOR, 1)), //ZEREH SAZI
    //todo : what about blackSmith?
    BLACKSMITH("blacksmith", ProductionCenter.class, 300, 100, ResourceType.WOOD, 20, 1, 0, false, new Resources(ResourceType.IRON, 6), null), //SAKHTEMAN AHANGARY
    FLETCHER( "fletcher",ProductionCenter.class, 300, 100, ResourceType.WOOD, 20, 1, 0, false, new Resources(ResourceType.WOOD, 6), new Weapon(WeaponType.BOW, 1)), //KAMAN SAZI
    //todo: again?
    POLE_TURNER("pole_turner",  ProductionCenter.class, 300, 100, ResourceType.WOOD, 10, 1, 0,false, new Resources(ResourceType.IRON, 6), null), //NEYZE SAZI
    OIL_SMELTER("oil smelter", ProductionCenter.class,300, 100, ResourceType.STONE, 10, 0, 1, true, null, new Weapon(WeaponType.PETROLEUM, 5)), //KARKHANE ZOB
    PITCH_DITCH("pitch ditch", Traps.class, 300, 0, ResourceType.PITCH, 2, 0, 0, true, null, null), //KHANDAGH GHIR  ** 2 PITCH PER 5 SQUARE
    CAGED_WAR_DOGES("caged war dogs", Traps.class, 100, 100, ResourceType.WOOD, 10, 0, 0, true, null, null), //GHAFASE SAG HAYE JANGI
    SIEGE_TENT("siege tent", Building.class, 100, 0, null, 0, 0, 1, true, null, null), //CHADOR MOHASERE
    //todo: think about this
    STABLE("stable", ProductionCenter.class, 300,400, ResourceType.WOOD, 20, 0, 0, true, null, null), //ESTABL
    APPLE_GARDEN("apple garden", ProductionCenter.class, 100, 0, ResourceType.WOOD, 5, 1,0, false, null, new Food(FoodType.APPLES, 5)), //BAGH SIB
    DAIRY_PRODUCTS("dairy products", ProductionCenter.class, 100, 0, ResourceType.WOOD, 10, 1, 0, false, null, new Food(FoodType.CHEESE, 5)), //TOLIDAT LABANI

    HOPS_FARM("hops farm", ProductionCenter.class, 100, 0, ResourceType.WOOD, 15, 1,0, false, null, new Resources(ResourceType.BARELY, 5)), //MAZRAE JO
    HUNTER_POST("hunter post", ProductionCenter.class, 300, 0, ResourceType.WOOD, 5, 1,0, false, null, new Food(FoodType.MEAT, 5)), //POST SHEKAR
    WHEAT_FARM("wheat farm", ProductionCenter.class, 300, 0, ResourceType.WOOD, 15, 1, 0, false, null, new Resources(ResourceType.WHEAT, 10)), //MAZRAE GANDOM
    BAKERY("bakery", ProductionCenter.class, 300, 0 ,ResourceType.WOOD, 10, 1, 0, false, new Resources(ResourceType.FLOUR, 7), new Food(FoodType.BREAD, 2)), //NANVAEI
    BREWERY("brewery",ProductionCenter.class, 300, 0 ,ResourceType.WOOD, 10, 1, 0, false, new Resources(ResourceType.BARELY, 6), new Resources(ResourceType.ALE, 4)), //ABJO SAZI
    FOOD_STOCKPILE("food stockpile", Storage.class, 500, 0, ResourceType.WOOD, 5, 0,0, false, null, null) ,//ANBAR GHAZA

    WALL("wall", Wall.class, 200, 0, ResourceType.STONE, 10, 0, 0,  false, null, null),

    STAIR("stair", Wall.class, 50, 0, ResourceType.STONE, 10, 0,0,false,null,null),
    TREE("tree", ProductionCenter.class, 100, 0, null, 0, 0,0, false, null,null)
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

    private Resources resources;

    private Property outputProperty;

    BuildingType(String buildingName, Class<?> BuildingClass, int hitPoint,int goldPrice, ResourceType resourcePrice, int resourceCount, int workerCount, int engineerCount, boolean isPartOfCastle, Resources resources, Property outputProperty ) {this.buildingName = buildingName;
        this.BuildingClass = BuildingClass;
        this.hitPoint = hitPoint;
        this.goldPrice = goldPrice;
        this.resourcePrice = resourcePrice;
        this.resourceCount = resourceCount;
        this.workerCount = workerCount;
        this.engineerCount = engineerCount;
        this.isPartOfCastle = isPartOfCastle;
        this.resources = resources;
        this.outputProperty = outputProperty;
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

    public Property getOutputProperty() {
        return outputProperty;
    }

    public Resources getResources() {
        return resources;
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
