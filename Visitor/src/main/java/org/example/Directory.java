package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Directory(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getName() { return name; }

    // Build a tree: do not add an ancestor or reuse an element under two parents.
    public void add(FileSystemElement element) {
        children.add(Objects.requireNonNull(element, "element"));
    }

    public List<FileSystemElement> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(FileSystemElement visitor) {

    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
