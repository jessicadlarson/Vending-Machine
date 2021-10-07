package com.techelevator;

import com.techelevator.fileio.FileReader;
import com.techelevator.items.VendingMachine;
import com.techelevator.items.VendingMachineItem;
import com.techelevator.view.Menu;
import java.math.BigDecimal;
import java.util.TreeMap;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private static final String FEED_MONEY_ONE = "1";
	private static final String FEED_MONEY_TWO = "2";
	private static final String FEED_MONEY_FIVE = "5";
	private static final String FEED_MONEY_TEN = "10";
	private static final String[] FEED_MONEY_OPTIONS = {FEED_MONEY_ONE, FEED_MONEY_TWO, FEED_MONEY_FIVE, FEED_MONEY_TEN};


	private Menu menu;
	private VendingMachine vendingMachine = new VendingMachine();



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		boolean isRunning = true;
		vendingMachine.populateSalesMap();

		while (isRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.displayVendingMachineItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean isInPurchase = true;
				while (isInPurchase) {
					vendingMachine.printCurrentMoneyProvided();

					String secondChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (secondChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						vendingMachine.feedMoney(getFeedMoneyOption());

					} else if (secondChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						vendingMachine.displayVendingMachineItems();
						vendingMachine.productSelection(getProductCode(vendingMachine.getInventoryMap()));
						vendingMachine.runSalesReport();

					} else {
						vendingMachine.makeChange();
						isInPurchase = false;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				isRunning = false;
			} else {
				vendingMachine.runSalesReport();
				vendingMachine.printSalesReportToConsole();
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}


	public BigDecimal getFeedMoneyOption() {
		System.out.println("Please choose amount: $1, $2, $5, $10");
		String userInput = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);

		if (userInput.equals(FEED_MONEY_ONE)) {
			return BigDecimal.ONE;
		} else if (userInput.equals(FEED_MONEY_TWO)) {
			return new BigDecimal(2);
		} else if (userInput.equals(FEED_MONEY_FIVE)) {
			return new BigDecimal(5);
		} else if (userInput.equals(FEED_MONEY_TEN)) {
			return BigDecimal.TEN;
		}

		return BigDecimal.ZERO;
	}


	public String getProductCode(TreeMap<String, VendingMachineItem> inventoryMap) {
		String[] productCodeOptions = inventoryMap.keySet().toArray(new String[inventoryMap.size()]);
		System.out.println("Please choose a slot number");
		String userInput = (String) menu.getChoiceFromOptions(productCodeOptions);
		return userInput;
	}



	}

