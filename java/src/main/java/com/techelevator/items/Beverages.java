package com.techelevator.items;

import java.math.BigDecimal;

public class Beverages extends VendingMachineItem {
    public Beverages(String slot, String name, BigDecimal price, int count) {
        super(slot, name, price, "Drink", count, "Glug Glug, Yum!");
    }
}
