package src;

public class Book {
    private String title;
    private String author;
    private int price;
    private String genre;
    private Boolean isBorrowed;

    public Book(String title, String author, int price) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty() || price <= 0) {
            throw new IllegalArgumentException(
                    "Book title or author cannot be null or empty and price cannot be less than or equal to 0");
        }
        this.title = title;
        this.author = author;
        this.price = price;
        this.isBorrowed = false;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(Boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}