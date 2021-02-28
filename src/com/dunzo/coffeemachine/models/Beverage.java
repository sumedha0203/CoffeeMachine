package com.dunzo.coffeemachine.models;

import java.util.List;

/**
 * Beverage POJO
 */
public class Beverage {

    private String name;
    private List<Ingredient> ingredients;

    public Beverage(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
