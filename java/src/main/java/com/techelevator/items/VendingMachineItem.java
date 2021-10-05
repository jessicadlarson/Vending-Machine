package com.techelevator.items;

import java.math.BigDecimal;

public abstract class VendingMachineItem {
    private String slot;
    private String name;
    private BigDecimal price;
    private String type;
    private int count;
    private String saying;


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSlot() {
        return slot;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getSaying() {
        return saying;
    }

    public VendingMachineItem(String slot, String name, BigDecimal price, String type, int count, String saying){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
        this.count = count;
        this.saying = saying;
    }

    public void decreaseCount(){
        count-=1;
    }


}
