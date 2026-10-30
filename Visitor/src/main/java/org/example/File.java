package org.example;

import java.util.Objects;

public class File implements FileSystemElement {
    private final String name;
    private final double sizeMb;

    public File(String name, double sizeMb) {
        this.name = Objects.requireNonNull(name, "name");
        if (!Double.isFinite(sizeMb) || sizeMb < 0) {
            throw new IllegalArgumentException("Size must be finite and non-negative");
        }
        this.sizeMb = sizeMb;
    }

    public String getName() { return name; }
    public double getSizeMb() { return sizeMb; }

    @Override
    public void accept(FileSystemElement visitor) {

    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}