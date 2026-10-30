package org.example;


import java.time.LocalDate;
import java.util.Objects;

class Document implements LibraryDocument {
    private String id;
    private LocalDate creationDate;
    private  String content;

    Document(String id, LocalDate creationDate, String content) {
        this.id = Objects.requireNonNull(id);
        this.creationDate = Objects.requireNonNull(creationDate);
        this.content = Objects.requireNonNull(content);
        if (id.isBlank()) {
            throw new IllegalArgumentException("Document ID must not be blank");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent(User user) {
        return content;
    }
}
