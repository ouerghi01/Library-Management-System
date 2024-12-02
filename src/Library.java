package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Users.Role;
import src.Users.User;

public class Library {
    private List<Book> books; // List of books in the library
    private String address;
    @SuppressWarnings("unused")
    private final User admin; // Admin of the library
    // List of users abonned to the library
    private Map<User, List<Book>> users_with_books;

    public Library(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Library address cannot be null or empty");
        }
        this.books = new ArrayList<>();
        this.address = address;
        this.admin = new User("admin", "admin@", "123123", Role.ADMIN);
        this.users_with_books = new HashMap<>();

    }

    public void userBorrowBook(User user, Book book) {
        if (user != null && book != null && this.books.contains(book) && !book.getIsBorrowed()) {
            user.borrowBook(book);
            book.setIsBorrowed(true);
            for (Book b : this.books) {
                if (b.getTitle().equals(book.getTitle())) {
                    b.setIsBorrowed(true);
                    break;
                }
            }
            users_with_books.put(user, user.getBorrowedBooks());
            System.out.println("User borrowed the book");
        }
    }

    public void userReturnBook(User user, Book book) {
        if (user != null && book != null && this.books.contains(book) && book.getIsBorrowed()) {
            user.returnBook(book);
            for (Book b : this.books) {
                if (b.getTitle().equals(book.getTitle())) {
                    b.setIsBorrowed(true);
                    break;
                }
            }
            users_with_books.put(user, user.getBorrowedBooks());
            System.out.println("User returned the book");
        }
    }

    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
            System.out.println("Book added to the library");
        }
    }

    public Book findBook(String query) {
        for (Book book : books) {
            if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getAddress() {
        return address;
    }

}
