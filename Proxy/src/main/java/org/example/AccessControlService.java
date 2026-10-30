package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class AccessControlService {
    private static final AccessControlService INSTANCE = new AccessControlService();
    private final Set<Permission> permissions = new HashSet<>();

    private record Permission(String documentId, String username) { }

    private AccessControlService() { }

    public static AccessControlService getInstance() {
        return INSTANCE;
    }

    public boolean isAllowed(String documentId, String username) {
        return permissions.contains(new Permission(documentId, username));
    }

    public void grantAccess(String documentId, String username) {
        permissions.add(new Permission(
                Objects.requireNonNull(documentId, "documentId"),
                Objects.requireNonNull(username, "username")));
    }

    public void revokeAccess(String documentId, String username) {
        permissions.remove(new Permission(documentId, username));
    }
}
