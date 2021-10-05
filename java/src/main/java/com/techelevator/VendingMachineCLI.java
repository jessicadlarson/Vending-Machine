package com.techelevator;

import com.techelevator.fileio.FileReader;
import com.techelevator.items.Inventory;
import com.techelevator.items.VendingMachineItem;
import com.techelevator.view.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private Menu menu;
	private Inventory inventory = new Inventory();
	private TreeMap<String, VendingMachineItem> inventoryMap;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		FileReader fileReader = new FileReader();
		inventoryMap = inventory.getInventory(fileReader.readFile());

	}

	public void run() {
		boolean isRunning = true;
		while (isRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayVendingMachineItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			} else {
				isRunning = false;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	// Display Vending Machine Items
	public void displayVendingMachineItems(){
		for(Map.Entry<String, VendingMachineItem> item : inventoryMap.entrySet()){
			System.out.println(item.getKey() + "|" + item.getValue().getName() + "|" + item.getValue().getPrice() + "|" + item.getValue().getCount());
		}

	}
}
