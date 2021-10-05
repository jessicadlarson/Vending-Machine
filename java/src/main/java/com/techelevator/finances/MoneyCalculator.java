package com.techelevator.finances;

import java.math.BigDecimal;

public class MoneyCalculator {
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TEN = BigDecimal.TEN;


    private BigDecimal currentBalance;

    public MoneyCalculator(){
        this.currentBalance = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentBalance() {
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
}
