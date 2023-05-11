package controller.GameController;

import model.Kingdom;
import model.Property.Food;
import model.Property.FoodType;

public class KingdomController {
    private Kingdom currentKingdom;

    public String setFoodRate(String number) throws NumberFormatException {
        // does the popularity change every month or just once ?
        int rate;
        try {
            rate = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return "Input is not a number";
        }
        if (rate < -2 || rate > 2) {
            return "Invalid rate";
        }
        Food food = new Food(FoodType.APPLES, rate);
        int popularity = currentKingdom.getPopularity();
        if (currentKingdom.getNumberOfProperties(food) < (1 / 2) * currentKingdom.getPopulation() && rate > -2) {
            currentKingdom.setFoodRate(-2);
            currentKingdom.setPopularity(popularity + 4 * (-2));
            return "Not enough food";
        }
        currentKingdom.setFoodRate(rate);
        currentKingdom.setPopularity(popularity + 4 * rate);
        // food will decrease monthly
        return "Food rate set successfully";
    }
    public String setTaxRate(String number) throws NumberFormatException {
        int rate;
        try {
            rate = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return "Input is not a number";
        }
        if (rate < -3 || rate > 8) {
            return "Invalid rate";
        }
        int popularity = currentKingdom.getPopularity();
        if (currentKingdom.getGold() == 0) {
            currentKingdom.setTaxRate(0);
            currentKingdom.setPopularity(popularity + 1);
            return "You don't have enough gold, come back later";
        }
        currentKingdom.setTaxRate(rate);
        // gold will reduce monthly
        if (rate <= 0) {
            currentKingdom.setPopularity(popularity + rate *(-2) + 1);
        }
        else if (rate <= 4) {
            currentKingdom.setPopularity(popularity + (-2) * rate);
        }
        else {
            currentKingdom.setPopularity(popularity + (-4) * rate + 8);
        }
        return "Tax rate set successfully";
    }
    public String showTaxRate () {
        return "Your tax rate is: " + currentKingdom.getTaxRate();
    }
    // religion is all about buildings
    public String setFearRate (String number) {//positive fear -> negative popularity
        int rate;
        try {
            rate = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return "Input is not a number";
        }
        if (rate < -5 || rate > 5) {
            return "Invalid rate";
        }
        int popularity = currentKingdom.getPopularity();
        currentKingdom.setFearRate(rate);
        currentKingdom.setPopularity(popularity - rate);
    }
}
