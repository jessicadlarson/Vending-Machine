package com.techelevator.fileio;

import com.techelevator.items.VendingMachineItem;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SalesReport {
    private String salesFilePath = "salesreport.txt";
    private File salesFile = new File(salesFilePath);
    private String totalFilePath = "totalsales.txt";
    private File totalFile = new File(totalFilePath);
    private LocalDateTime timeStamp = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private TreeMap<String, Integer> fileMap = new TreeMap<>();
    private FileReader fileReader = new FileReader();
    private BigDecimal total = BigDecimal.ZERO;
    Map<String, Integer> allTimeSalesMap = new HashMap<>();

    public void createSalesFile(){
        try {
            salesFile.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void createTotalFile(){
        try {
            totalFile.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void readFromFile(Map<String, Integer> salesMap)  {

        List<String[]> lastSalesReportList = fileReader.readFile(salesFilePath);
        if(lastSalesReportList.size() != 0){
            for(String[] line : lastSalesReportList){
                String key = line[0];
                Integer priorValue = Integer.parseInt(line[1]);

                Integer newValue = salesMap.get(key);
                Integer totalValue = priorValue + newValue;

                allTimeSalesMap.put(key, totalValue);

            }
        }

    }

    public void determineBalance(BigDecimal currentTotal){
        List<String[]> lastTotalList = fileReader.readFile(totalFilePath);
        if(lastTotalList.size() != 0){
            for(String[] line : lastTotalList){
                String withoutDollarSign = line[0].substring(1);
                Double d = Double.parseDouble(withoutDollarSign);
                BigDecimal previousTotal = new BigDecimal(d);
                total = previousTotal.add(currentTotal);

            }
        }
    }

    public void writeToFiles(){
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(salesFile, false))){
            for(Map.Entry<String, Integer> item : allTimeSalesMap.entrySet()){
                writer.append(item.getKey() + "|" + item.getValue() + "\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("Can not open file for writing.");
        }


        try(PrintWriter writer = new PrintWriter(new FileOutputStream(totalFile, false))){
            writer.append(currencyFormat(total));
        } catch (FileNotFoundException e){
            System.out.println("Can not open file for writing.");
        }

    }

    public void runSalesReport(Map<String, Integer> salesMap, BigDecimal currentTotal){
        createSalesFile();
        createTotalFile();
        readFromFile(salesMap);
        determineBalance(currentTotal);
        writeToFiles();

    }

    public void printSalesReportToConsole(Map<String, Integer> salesMap){
        for(Map.Entry<String, Integer> item : salesMap.entrySet()){
            System.out.println(item.getKey() + "|" + item.getValue());
        }
        System.out.println("TOTAL SALES: " + currencyFormat(total));
    }

    public String currencyFormat(BigDecimal amount){
        return NumberFormat.getCurrencyInstance().format(amount);
    }

}
