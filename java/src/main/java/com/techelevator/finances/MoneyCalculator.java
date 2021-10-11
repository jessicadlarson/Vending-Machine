package com.techelevator.finances;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MoneyCalculator {
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TEN = BigDecimal.TEN;
    private static final BigDecimal QUARTER = new BigDecimal(.25);
    private static final BigDecimal DIME = new BigDecimal(.1);
    private static final BigDecimal NICKEL = new BigDecimal(.05);


    private BigDecimal currentBalance;

    public MoneyCalculator(){
        this.currentBalance = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal purchase(BigDecimal price){
        currentBalance = currentBalance.subtract(price);
        return currentBalance;
    }

    public BigDecimal addMoney(BigDecimal amount){
        if(amount.equals(ONE)){
            currentBalance = currentBalance.add(ONE);
        } else if(amount.equals(TWO)){
            currentBalance = currentBalance.add(TWO);
        } else if(amount.equals(FIVE)){
            currentBalance = currentBalance.add(FIVE);
        } else if(amount.equals(TEN)){
            currentBalance = currentBalance.add(TEN);
        } else {
            System.out.println("Please enter a valid amount.");
            System.exit(1);
        }

        return currentBalance;
    }

    public String makeChange(){
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        // Current Balance note: currentBalance.compareTo(DIME) will return:
        //    0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
        //    1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
        //    -1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.

        while(currentBalance.compareTo(NICKEL) >= 0){
            if(currentBalance.compareTo(QUARTER) >= 0){
                currentBalance = currentBalance.subtract(QUARTER);
                quarterCount++;
            } else if(currentBalance.compareTo(DIME) >= 0){
                currentBalance = currentBalance.subtract(DIME);
                dimeCount++;
            } else if(currentBalance.compareTo(NICKEL) >= 0){
                currentBalance = currentBalance.subtract(NICKEL);
                nickelCount++;
            }
        }
        currentBalance = BigDecimal.ZERO;
        String changeDue = "Your change is: \n" + "Quarters: " + quarterCount + " Dimes: " + dimeCount + " Nickels: " + nickelCount +
                            "\nYour balance is: " + currencyFormat(currentBalance);

        return changeDue;
    }



    public String currencyFormat(BigDecimal amount){
        return NumberFormat.getCurrencyInstance().format(amount);
    }
}
