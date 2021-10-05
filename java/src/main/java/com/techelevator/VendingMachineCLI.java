package com.techelevator;

import com.techelevator.fileio.FileReader;
import com.techelevator.finances.MoneyCalculator;
import com.techelevator.items.Inventory;
import com.techelevator.items.VendingMachineItem;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private Inventory inventory = new Inventory();
	private TreeMap<String, VendingMachineItem> inventoryMap;
	private MoneyCalculator moneyCalculator;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		FileReader fileReader = new FileReader();
		inventoryMap = inventory.getInventory(fileReader.readFile());
		moneyCalculator = new MoneyCalculator();

	}

	public void run() {
		boolean isRunning = true;
		boolean isInPurchase = true;
		while (isRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayVendingMachineItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while(isInPurchase){
					printCurrentMoneyProvided();
					String secondChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if(secondChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
						feedMoney();
					} else if(secondChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){

					} else {

					}
					// do purchase
				}
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
			String count = item.getValue().getCount() == 0 ? "SOLD OUT" : String.valueOf(item.getValue().getCount());
			System.out.println(item.getKey() + "|" + item.getValue().getName() + "|" + item.getValue().getPrice() + "|" + count);

		}

	}

	public void printCurrentMoneyProvided(){
		System.out.println();
		System.out.println("Current Money Provided: " + currencyFormat(moneyCalculator.getCurrentBalance()));
	}

	public void feedMoney(){
		BigDecimal amountToFeed = menu.displayFeedMoneyOptions();
		moneyCalculator.addMoney(amountToFeed);

	}

	public String currencyFormat(BigDecimal amount){
		return NumberFormat.getCurrencyInstance().format(amount);
	}
}
