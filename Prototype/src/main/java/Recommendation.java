import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Recommendation implements Prototype<Recommendation> {
    private String targetAudience;
    private final List<Book> books;

    public Recommendation(String targetAudience) {
        this(targetAudience, new ArrayList<>());
    }

    private Recommendation(String targetAudience, List<Book> books) {
        setTargetAudience(targetAudience);
        this.books = books;
    }

    public String getTargetAudience() { return targetAudience; }

    public void setTargetAudience(String audience) {
        Objects.requireNonNull(audience, "Audience cannot be null.");
        if (audience.isBlank()) throw new IllegalArgumentException("Audience cannot be blank.");
        targetAudience = audience.trim();
    }

    public List<Book> getBooks() { return List.copyOf(books); }
    public void addBook(Book book) { books.add(Objects.requireNonNull(book)); }

    public boolean removeBook(int index) {
        if (index < 0 || index >= books.size()) return false;
        books.remove(index);
        return true;
    }

    @Override public Recommendation clone() {
        List<Book> copies = new ArrayList<>(books.size());
        for (Book book : books) copies.add(book.clone());
        return new Recommendation(targetAudience, copies);
    }

    @Override public String toString() {
        return "Recommendation for " + targetAudience + " (" + books.size() + " books)";
    }
}
