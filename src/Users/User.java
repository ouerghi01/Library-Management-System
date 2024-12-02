package src.Users;

import java.util.ArrayList;
import java.util.List;

import src.Book;

public class User {
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<Book> borrowedBooks;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        } else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }
        this.email = email;
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        } else if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        this.password = password;
        borrowedBooks = new ArrayList<>();
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        } else if (role != Role.ADMIN && role != Role.USER) {
            throw new IllegalArgumentException("Role must be either ADMIN or USER");
        }
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}