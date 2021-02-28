package com.dunzo.coffeemachine.service;

import com.dunzo.coffeemachine.models.Beverage;
import com.dunzo.coffeemachine.models.Machine;

public interface BeverageService {

    /**
     * to disperse the drinks
     *
     * @param beverage beverage
     * @param machine machine
     */
    boolean disperseBeverage(Beverage beverage, Machine machine);

    /**
     * to check quantity of Ingredients
     *
     * @param machine machine
     */
    void checkLowIngredients(Machine machine);

    /**
     * to refill the ingredients
     *
     * @param machine machine
     * @param name name
     * @param refillQuantity refillQuantity
     */
    void refillIngredients(Machine machine, String name, long refillQuantity);
}
