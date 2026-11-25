import java.util.Objects;

public final class Book implements Prototype<Book> {
    private final String author;
    private final String title;
    private final String genre;
    private final int publicationYear;

    public Book(String author, String title, String genre, int publicationYear) {
        this.author = text(author, "Author");
        this.title = text(title, "Title");
        this.genre = text(genre, "Genre");
        if (publicationYear < 0) throw new IllegalArgumentException("Year cannot be negative.");
        this.publicationYear = publicationYear;
    }

    private static String text(String value, String name) {
        Objects.requireNonNull(value, name + " cannot be null.");
        if (value.isBlank()) throw new IllegalArgumentException(name + " cannot be blank.");
        return value.trim();
    }

    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }

    @Override public Book clone() {
        return new Book(author, title, genre, publicationYear);
    }

    @Override public String toString() {
        return title + " — " + author + " (" + genre + ", " + publicationYear + ")";
    }
}
