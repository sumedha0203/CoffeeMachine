package com.dunzo.coffeemachine.models;

import java.util.List;

/**
 * Beverage POJO
 */
public class Beverage {

    private String beverage;
    private List<Ingredient> ingredients;

    public Beverage(String beverage, List<Ingredient> ingredients) {
        this.beverage = beverage;
        this.ingredients = ingredients;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
