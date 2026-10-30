package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public final class Library {
    private final HashMap<String, LibraryDocument> documents = new HashMap<>();

    public LibraryDocument createPublicDocument(String id, LocalDate date, String content) {
        return createDocument(id, date, content, false);
    }

    public LibraryDocument createProtectedDocument(String id, LocalDate date, String content) {
        return createDocument(id, date, content, true);
    }

    private LibraryDocument createDocument(String id, LocalDate date, String content,
                                           boolean protectedContent) {
        if (documents.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate document ID: " + id);
        }
        Document realDocument = new Document(id, date, content);
        LibraryDocument entry = protectedContent ? new DocumentProxy(realDocument) : realDocument;
        documents.put(id, entry);
        return entry;
    }

    public LibraryDocument getDocument(String id) {
        LibraryDocument document = documents.get(id);
        if (document == null) {
            throw new NoSuchElementException("Unknown document ID: " + id);
        }
        return document;
    }

    public List<LibraryDocument> getDocuments() {
        return List.copyOf(documents.values());
    }
}
