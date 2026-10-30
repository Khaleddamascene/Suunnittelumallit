package org.example;

public interface FileSystemElement {
    void accept(FileSystemElement visitor);

    void accept(FileSystemVisitor visitor);
}
