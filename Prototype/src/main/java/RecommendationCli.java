import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class RecommendationCli {
    private final Scanner in = new Scanner(System.in);
    private final List<Recommendation> catalog = new ArrayList<>();

    public RecommendationCli() {
        catalog.add(sample("Young adult fantasy readers",
                new Book("J. K. Rowling", "Harry Potter and the Philosopher's Stone", "Fantasy", 1997),
                new Book("Ursula K. Le Guin", "A Wizard of Earthsea", "Fantasy", 1968)));
        catalog.add(sample("Readers who enjoy classic literature",
                new Book("Jane Austen", "Pride and Prejudice", "Classic", 1813),
                new Book("Mary Shelley", "Frankenstein", "Gothic", 1818)));
        catalog.add(sample("Readers interested in science",
                new Book("Carl Sagan", "Cosmos", "Science", 1980),
                new Book("Stephen Hawking", "A Brief History of Time", "Science", 1988)));
    }

    private Recommendation sample(String audience, Book... books) {
        Recommendation r = new Recommendation(audience);
        for (Book b : books) r.addBook(b);
        return r;
    }

    public static void main(String[] args) { new RecommendationCli().run(); }

    private void run() {
        System.out.println("Book Recommendations — Prototype demo");
        boolean running = true;
        while (running) {
            System.out.println("\n1) View lists\n2) View one list\n3) Clone and edit a list"
                    + "\n4) Create a list\n0) Exit");
            switch (readInt("Choose: ")) {
                case 1 -> showLists();
                case 2 -> { showLists(); Recommendation r = choose(); if (r != null) showBooks(r); }
                case 3 -> cloneList();
                case 4 -> createList();
                case 0 -> running = false;
                default -> System.out.println("Choose one of the listed options.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void showLists() {
        System.out.println("\nRecommendations:");
        for (int i = 0; i < catalog.size(); i++) System.out.println((i + 1) + ") " + catalog.get(i));
    }

    private Recommendation choose() {
        int n = readInt("List number (0 cancels): ");
        if (n == 0) return null;
        if (n < 1 || n > catalog.size()) {
            System.out.println("No recommendation has that number.");
            return null;
        }
        return catalog.get(n - 1);
    }

    private void cloneList() {
        showLists();
        Recommendation source = choose();
        if (source == null) return;
        Recommendation copy = source.clone();
        System.out.println("Deep copy created.");
        if (edit(copy)) save(copy);
        else System.out.println("Changes discarded.");
    }

    private void createList() {
        Recommendation r = new Recommendation(readText("Target audience: "));
        if (edit(r)) save(r);
        else System.out.println("Changes discarded.");
    }

    private boolean edit(Recommendation r) {
        while (true) {
            System.out.println("\nEditing: " + r.getTargetAudience() + " (" + r.getBooks().size() + " books)");
            System.out.println("1) List books\n2) Add book\n3) Remove book"
                    + "\n4) Change audience\n5) Save\n0) Cancel");
            switch (readInt("Choose: ")) {
                case 1 -> showBooks(r);
                case 2 -> addBook(r);
                case 3 -> removeBook(r);
                case 4 -> r.setTargetAudience(readText("New audience: "));
                case 5 -> { return true; }
                case 0 -> { return false; }
                default -> System.out.println("Choose one of the listed options.");
            }
        }
    }

    private void addBook(Recommendation r) {
        try {
            String title = readText("Title: ");
            String author = readText("Author: ");
            String genre = readText("Genre: ");
            int year = readInt("Publication year: ");
            r.addBook(new Book(author, title, genre, year));
            System.out.println("Book added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add book: " + e.getMessage());
        }
    }

    private void removeBook(Recommendation r) {
        showBooks(r);
        if (r.getBooks().isEmpty()) return;
        int n = readInt("Book number to remove (0 cancels): ");
        if (n == 0) return;
        if (r.removeBook(n - 1)) System.out.println("Book removed.");
        else System.out.println("No book has that number.");
    }

    private void showBooks(Recommendation r) {
        System.out.println("\n" + r);
        List<Book> books = r.getBooks();
        for (int i = 0; i < books.size(); i++) System.out.println((i + 1) + ") " + books.get(i));
        if (books.isEmpty()) System.out.println("No books yet.");
    }

    private void save(Recommendation r) {
        catalog.add(r);
        System.out.println("Saved as recommendation " + catalog.size() + ".");
    }


    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = in.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Please enter a value.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(in.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a whole number."); }
        }
    }
}
