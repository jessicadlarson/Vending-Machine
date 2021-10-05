package com.techelevator.items;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem {
    public Gum(String slot, String name, BigDecimal price, int count) {
        super(slot, name, price, "Gum", count, "Chew Chew, Yum!");
    }
}
