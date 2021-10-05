package com.techelevator.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private String path = "vendingmachine.csv";
    File inputFile = new File(path);

    public List<String[]> readFile() {

        List<String[]> parsedList = new ArrayList<>();

        try (Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile())) {
            while (inputScanner.hasNextLine()){
                String line = inputScanner.nextLine();
                String[] eachWord = line.split("\\|");
                parsedList.add(eachWord);
            }
        } catch (FileNotFoundException e) {
            System.out.println(path + " was not found.");
        }
        return parsedList;
    }
}
