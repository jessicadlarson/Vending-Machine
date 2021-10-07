package com.techelevator.items;

import com.techelevator.VendingMachineCLI;
import com.techelevator.fileio.FileReader;
import com.techelevator.fileio.LogWriter;
import com.techelevator.fileio.SalesReport;
import com.techelevator.finances.MoneyCalculator;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {

    private Inventory inventory = new Inventory();
    private TreeMap<String, VendingMachineItem> inventoryMap;
    private MoneyCalculator moneyCalculator;
    private LogWriter logWriter;
    private Map<String, Integer> salesMap = new HashMap<>();
    private SalesReport salesReport = new SalesReport();
    private BigDecimal currentTotalSales = BigDecimal.ZERO;

    public TreeMap<String, VendingMachineItem> getInventoryMap(){
        return this.inventoryMap;
    }

    public VendingMachine(){
        FileReader fileReader = new FileReader();
        inventoryMap = inventory.getInventory(fileReader.readFile("vendingmachine.csv"));
        moneyCalculator = new MoneyCalculator();
        logWriter = new LogWriter();
    }

    public void displayVendingMachineItems() {
        for (Map.Entry<String, VendingMachineItem> item : inventoryMap.entrySet()) {
            String count = item.getValue().getCount() == 0 ? "SOLD OUT" : String.valueOf(item.getValue().getCount());
            System.out.println(item.getKey() + "|" + item.getValue().getName() + "|" + item.getValue().getPrice() + "|" + count);

        }


    }

    public void printCurrentMoneyProvided() {
        System.out.println();
        System.out.println("Current Money Provided: " + currencyFormat(moneyCalculator.getCurrentBalance()));
    }

    public String currencyFormat(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }


    public void feedMoney(BigDecimal feedMoneyOption) {
        BigDecimal amountToFeed = feedMoneyOption;
        moneyCalculator.addMoney(amountToFeed);
        logWriter.writeToFile("FEED MONEY: " + currencyFormat(amountToFeed) + " " + currencyFormat(moneyCalculator.getCurrentBalance()));
    }

    public void productSelection(String productSelected) {
        if (inventoryMap.containsKey(productSelected)) {
            if (inventoryMap.get(productSelected).getCount() == 0) {
                System.out.println("Item selected is sold out.");
            } else {
                if(validatedMoney(inventoryMap.get(productSelected).getPrice())){
                    dispenseItem(productSelected);
                }
                else{
                    System.out.println("Please provide enough money.");
                }
            }

        } else {
            System.out.println("The product code given does not exist.");
        }
    }

    public void dispenseItem(String productSelected) {
        String saying = inventoryMap.get(productSelected).getSaying();
        String name = inventoryMap.get(productSelected).getName();
        String cost = currencyFormat(inventoryMap.get(productSelected).getPrice());
        currentTotalSales = currentTotalSales.add(inventoryMap.get(productSelected).getPrice());
        String moneyRemaining = currencyFormat(moneyCalculator.purchase(inventoryMap.get(productSelected).getPrice()));
        inventoryMap.get(productSelected).decreaseCount();
        System.out.println("Purchased: " + name + "|" + cost);
        System.out.println(saying);
        System.out.println("Money remaining: " + moneyRemaining);
        addToSalesMap(productSelected);
        logWriter.writeToFile(name + " " + productSelected + " " + cost + " " + moneyRemaining);

    }

    public void makeChange() {
        String previousBalance = currencyFormat(moneyCalculator.getCurrentBalance());
        System.out.println(moneyCalculator.makeChange());
        String currentBalance = currencyFormat(moneyCalculator.getCurrentBalance());

        logWriter.writeToFile("GIVE CHANGE: " + previousBalance + " " + currentBalance);
    }

    public void populateSalesMap(){
        for (Map.Entry<String, VendingMachineItem> item : inventoryMap.entrySet()) {
            salesMap.put(item.getValue().getName(), 0);
        }
    }

    public void addToSalesMap(String productSelected) {
        String name = inventoryMap.get(productSelected).getName();
        if (salesMap.containsKey(name)) {
            salesMap.put(name, salesMap.get(name) + 1);

        }

    }

    public void runSalesReport(){
        salesReport.runSalesReport(salesMap, currentTotalSales);
        currentTotalSales = BigDecimal.ZERO;

    }

    public void printSalesReportToConsole(){
        salesReport.printSalesReportToConsole(salesMap);

    }

    public boolean validatedMoney(BigDecimal price){
        if(moneyCalculator.getCurrentBalance().compareTo(price) >= 0 ){
            return true;
        }
        return false;
    }

}
