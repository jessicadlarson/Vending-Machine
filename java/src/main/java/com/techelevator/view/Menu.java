package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}

		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public BigDecimal displayFeedMoneyOptions(){
		out.println();
		System.out.println("Please choose amount: $1, $2, $5, $10");
		while(in.hasNextLine()){
			String userInput = in.nextLine();
			if(userInput.equals("1")){
				return BigDecimal.ONE;
			} else if(userInput.equals("2")){
				return new BigDecimal(2);
			} else if(userInput.equals("5")){
				return new BigDecimal(5);
			} else if(userInput.equals("10")){
				return BigDecimal.TEN;
			} else {
				out.println("Please enter a valid amount of money (without $).");
				out.flush();
			}
		}
		return BigDecimal.ZERO;
	}
}
