package com.techelevator.fileio;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    File file = new File("log.txt");
    LocalDateTime timeStamp = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public void createFile(){
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void writeToFile(String message){
        createFile();
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))){
            writer.append(formatter.format((timeStamp)) + " " + message + "\n");
        } catch (FileNotFoundException e){
            System.out.println("Can not open file for writing.");
        }
    }

}
