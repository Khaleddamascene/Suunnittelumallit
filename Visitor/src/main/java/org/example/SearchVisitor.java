package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class SearchVisitor implements  FileSystemVisitor{
    private Predicate<File> criterion;
    private List<File> matches = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion) {
        this.criterion = Objects.requireNonNull(criterion, "Criterion");
    }


    @Override
    public void visit(File file) {
        if (criterion.test(file)) {
            matches.add(file);
        }

    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public List<File> getMatches() {
        return List.copyOf(matches);
    }

    public void reset() {
        matches.clear();
    }
}
