package com.dunzo.coffeemachine.models;

/**
 * Ingredient POJO
 */
public class Ingredient {
    private String name;
    private long quantity;

    public Ingredient(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
