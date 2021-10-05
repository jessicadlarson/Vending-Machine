package com.techelevator.items;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem {
    public Candy(String slot, String name, BigDecimal price, int count) {
        super(slot, name, price, "Candy", count, "Munch Munch, Yum!");
    }
}
