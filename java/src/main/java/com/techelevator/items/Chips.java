package com.techelevator.items;

import java.math.BigDecimal;

public class Chips extends VendingMachineItem {
    public Chips(String slot, String name, BigDecimal price, int count) {
        super(slot, name, price, "Chip", count, "Crunch Crunch, Yum!");
    }
}
