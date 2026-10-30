package org.example;

public class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalSizeMb;

    @Override
    public void visit(File file) {
        totalSizeMb += file.getSizeMb();
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public double getTotalSizeMb() { return totalSizeMb; }
    public void reset() { totalSizeMb = 0; }
}