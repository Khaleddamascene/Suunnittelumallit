package org.example;

import java.time.LocalDate;
import java.util.Objects;

public final class DocumentProxy implements LibraryDocument {
    private final Document document;
    private final AccessControlService accessControl = AccessControlService.getInstance();

    DocumentProxy(Document document) {
        this.document = Objects.requireNonNull(document, "document");
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (user == null || !accessControl.isAllowed(getId(), user.getUsername())) {
            throw new AccessDeniedException("Access denied to document " + getId()
                    + " for " + (user == null ? "anonymous" : user.getUsername()));
        }
        return document.getContent(user);
    }
}
