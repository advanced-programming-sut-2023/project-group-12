package model.Building;

import model.Kingdom;
import model.Property.*;

public class ProductionCenter extends Building{
//    private int productionRate;
    private int hitPoint;
    private Property product1;
    private Property product2;
//    private int usageRate;
    private Resources source;

    public ProductionCenter(BuildingType buildingType, Kingdom owner, int xPosition, int yPosition) {
        super(buildingType, owner, xPosition, yPosition);

        switch (buildingType) {
            case INN:
                //todo
                break;
            case BLACKSMITH:
                product1 = new Weapon(WeaponType.MACE, 1);
                product2 = new Weapon(WeaponType.SWORD, 1);
                source = buildingType.getResources();
                break;
            case POLE_TURNER:
                product1 = new Weapon(WeaponType.SPEAR, 1);
                product2 = new Weapon(WeaponType.PIKE, 1);
                source = buildingType.getResources();
                break;
            case OX_TETHER:
                //todo
                break;
            default:
                product1 = buildingType.getOutputProperty();
                product2 = null;
                source = buildingType.getResources();
                break;

        }
    }


    public Property getProduct1() {
        return product1;
    }

    public Property getProduct2() {
        return product2;
    }

    public Resources getSource() {
        return source;
    }

    public void run(ProductionCenter building){
        if(source != null) {
            if (building.getOwner().getAllResources().get(source.getResourceType()) < source.getValue()) {
                return;
            }
            building.getOwner().getAllResources().put(source.getResourceType(), building.getOwner().getAllResources().get(source.getResourceType()) - source.getValue());
        }
        if(product1 != null){

        }

    }

}
