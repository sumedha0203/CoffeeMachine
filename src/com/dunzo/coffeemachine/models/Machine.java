package com.dunzo.coffeemachine.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Machine POJO
 */
public class Machine {
    private long outlets;
    private Map<String, Long> inventory = new HashMap<>();

    public long getOutlets() {
        return outlets;
    }

    public void setOutlets(long outlets) {
        this.outlets = outlets;
    }

    public Map<String, Long> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Long> inventory) {
        this.inventory = inventory;
    }


}
