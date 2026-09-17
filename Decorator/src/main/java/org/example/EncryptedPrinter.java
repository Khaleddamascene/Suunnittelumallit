package org.example;

public class EncryptedPrinter extends PrinterDecorator {

    public EncryptedPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            encrypted.append((char) (c + 3));
        }

        super.print(encrypted.toString());
    }
}
