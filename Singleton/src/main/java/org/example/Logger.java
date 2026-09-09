package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    // the single Logger intance
    private static Logger instance;

    // the file write
    private PrintWriter writer;

    // Default file name
    private String fileName = "log.txt";

    //
    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Error opening log file: " + e.getMessage());
        }
    }

    // Returns the single
    public static  synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Change the log file
    public synchronized void setFileName(String fileName) {
        if (writer != null) {
            writer.close();
        }
        this.fileName = fileName;

        // Open new file
        try {
            writer =  new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Error opening log file: " + e.getMessage());
            writer = null;
        }
    }

    // Log a message
    public synchronized void write(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        } else {
            System.out.println("Logger is not available. Cannot write to log file.");
        }
    }

    // Close the logger
    public synchronized void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}
