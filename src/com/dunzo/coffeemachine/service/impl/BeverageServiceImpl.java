package com.dunzo.coffeemachine.service.impl;

import com.dunzo.coffeemachine.models.Beverage;
import com.dunzo.coffeemachine.models.Ingredient;
import com.dunzo.coffeemachine.models.Machine;
import com.dunzo.coffeemachine.service.BeverageService;

import java.util.List;
import java.util.Map;

public class BeverageServiceImpl implements BeverageService {

    /**
     * to prepare and disperse the beverages
     *
     * @param beverage  beverage
     * @param machine machine
     */
    public boolean disperseBeverage(Beverage beverage, Machine machine) {

        List<Ingredient> ingredientsList = beverage.getIngredients();

        //prepares and disperses beverages for the user
        for (Ingredient ingredient : ingredientsList) {
            if (machine.getInventory().containsKey(ingredient.getName())) {
                if (ingredient.getQuantity() > machine.getInventory().get(ingredient.getName())) {
                    System.out.println(beverage.getBeverage() + " cannot be prepared because " + ingredient.getName() + " is not sufficient.");
                    return false;
                }
            } else {
                System.out.println(beverage.getBeverage() + " cannot be prepared because " + ingredient.getName() + " is not available.");
                return false;
            }
        }
        for (Ingredient usedIng : ingredientsList) {
            long updatedQuantity = machine.getInventory().get(usedIng.getName()) - usedIng.getQuantity();
            machine.getInventory().put(usedIng.getName(),updatedQuantity);
        }

        System.out.println(beverage.getBeverage() + " is prepared.");
        return true;
    }

    /**
     * to check quantity of Ingredients
     *
     * @param machine machine
     */
    public void checkLowIngredients(Machine machine) {
        //raises alert if quantity is lower than or equal to 50
        for (Map.Entry<String, Long> pair : machine.getInventory().entrySet()) {
            long quantity = pair.getValue();
            if (quantity <= 50) {
                System.out.println("Warning: " + pair.getKey() + " quantity = " + quantity + " is running low. Please refill.");
            }
        }
    }

    /**
     * to refill the ingredients
     *
     * @param machine machine
     * @param name name
     * @param refillQuantity refillQuantity
     */
    public void refillIngredients(Machine machine, String name, long refillQuantity) {
        long currentQuantity = machine.getInventory().get(name);
        System.out.println("Quantity before refill: " + currentQuantity);
        machine.getInventory().put(name, currentQuantity + refillQuantity);
        System.out.println("Successfully Refilled " + name);
        System.out.println("Quantity after refill: " + machine.getInventory().get(name));
    }


}
