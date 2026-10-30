package org.example;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");
        Directory images = new Directory("images");
        Directory archive = new Directory("archive");

        root.add(new File("readme.txt", 0.5));
        root.add(documents);
        root.add(images);

        documents.add(new File("report.pdf", 2.5));
        documents.add(new File("notes.txt", 1.0));
        documents.add(archive);

        archive.add(new File("old-notes.TXT", 0.25));
        images.add(new File("photo.jpg", 4.0));

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);

        System.out.println(
                "Total size: " + sizeCalculator.getTotalSizeMb() + " MB");

        SearchVisitor search = new SearchVisitor(
                file -> file.getName()
                        .toLowerCase(Locale.ROOT)
                        .endsWith(".txt"));

        root.accept(search);

        System.out.println("Text files:");

        for (File file : search.getMatches()) {
            System.out.println(
                    "- " + file.getName() + " (" + file.getSizeMb() + " MB)");
        }
    }
}