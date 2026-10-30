package org.example;

import java.time.LocalDate;

public interface LibraryDocument {
    String getId();
    LocalDate getCreationDate();
    String getContent(User user) throws AccessDeniedException;
}
