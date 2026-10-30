package org.example;

import java.time.LocalDate;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User alice = new User("alice");
        User bob = new User("bob");
        User visitor = new User("visitor");
        LocalDate date = LocalDate.of(2026, 10, 29);

        library.createPublicDocument("welcome", date, "Welcome to the library!");
        library.createProtectedDocument("budget", date, "Budget: 50,000 EUR");
        library.createProtectedDocument("report", date, "Internal project report");

        AccessControlService access = AccessControlService.getInstance();
        access.grantAccess("budget", alice.getUsername());
        access.grantAccess("report", bob.getUsername());

        System.out.println("Public metadata (no user required):");
        library.getDocuments().stream()
                .sorted(Comparator.comparing(LibraryDocument::getId))
                .forEach(document -> System.out.println(
                        document.getId() + " | " + document.getCreationDate()));

        System.out.println("\nContent access:");
        read(library, "welcome", visitor);
        read(library, "welcome", null);
        read(library, "budget", alice);
        read(library, "budget", bob);
        read(library, "report", bob);
        read(library, "report", visitor);
        read(library, "budget", null);

        System.out.println("\nAfter revoking Alice's budget access:");
        access.revokeAccess("budget", alice.getUsername());
        read(library, "budget", alice);
    }

    private static void read(Library library, String id, User user) {
        String username = user == null ? "anonymous" : user.getUsername();
        try {
            String content = library.getDocument(id).getContent(user);
            System.out.println(username + " -> " + id + ": " + content);
        } catch (AccessDeniedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
