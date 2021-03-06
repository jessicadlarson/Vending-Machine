package com.techelevator;

public class Chips extends VendingMachineItem {
	private String type = "Chip";
	private String saying = "Crunch Crunch, Yum!";
	
	public Chips(String slot, String name, double price, int count) {
		super(slot, name, price, count);
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public String getSaying() {
		return this.saying;
	}
}
