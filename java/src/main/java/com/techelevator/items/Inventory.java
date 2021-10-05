package com.techelevator.items;

import com.sun.source.tree.Tree;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {
    // Constants (based on Indexes)
    private static final int SLOT_LOCATION = 0;
    private static final int PRODUCT_NAME = 1;
    private static final int PRICE = 2;
    private static final int TYPE = 3;
    private static final int STARTING_COUNT = 5;

    public TreeMap<String, VendingMachineItem> getInventory(List<String[]> lines){
        TreeMap<String, VendingMachineItem> inventory = new TreeMap<>();
        for(String[] line : lines){
            if(line[TYPE].equals("Chip")){
                BigDecimal price = new BigDecimal(line[PRICE]);
                Chips chip = new Chips(line[SLOT_LOCATION], line[PRODUCT_NAME], price, STARTING_COUNT);
                inventory.put(line[SLOT_LOCATION], chip);
            } else if(line[TYPE].equals("Candy")){
                BigDecimal price = new BigDecimal(line[PRICE]);
                Candy candy = new Candy(line[SLOT_LOCATION], line[PRODUCT_NAME], price, STARTING_COUNT);
                inventory.put(line[SLOT_LOCATION], candy);
            } else if(line[TYPE].equals("Drink")){
                BigDecimal price = new BigDecimal(line[PRICE]);
                Beverages drink = new Beverages(line[SLOT_LOCATION], line[PRODUCT_NAME], price, STARTING_COUNT);
                inventory.put(line[SLOT_LOCATION], drink);
            } else if(line[TYPE].equals("Gum")){
                BigDecimal price = new BigDecimal(line[PRICE]);
                Gum gum = new Gum(line[SLOT_LOCATION], line[PRODUCT_NAME], price, STARTING_COUNT);
                inventory.put(line[SLOT_LOCATION], gum);
            }
        }

        return inventory;
    }

}
