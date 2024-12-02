package src;

import java.util.Scanner;

import src.Users.Role;
import src.Users.User;
import src.Users.Users;

public class App {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Please enter the library address:");
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();

        // Create a new library and user collection
        Library library = new Library(address);
        Users users = new Users();

        boolean exit = false;

        // Main menu loop
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Books");
            System.out.println("2. Register Users");
            System.out.println("3. View Library Books");
            System.out.println("4. View Users");
            System.out.println("5. Borrow or Return a Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBooks(scanner, library);
                    break;
                case 2:
                    registerUsers(scanner, users);
                    break;
                case 3:
                    viewBooks(library);
                    break;
                case 4:
                    viewUsers(users);
                    break;
                case 5:
                    manageBookTransaction(scanner, library, users);
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Close scanner
        scanner.close();
    }

    private static void addBooks(Scanner scanner, Library library) {
        System.out.print("How many books do you want to add? ");
        int NbrBooks = scanner.nextInt();
        for (int i = 0; i < NbrBooks; i++) {
            System.out.print("Enter the title of the book: ");
            String title = scanner.next();
            System.out.print("Enter the author of the book: ");
            String author = scanner.next();
            System.out.print("Enter the price of the book: ");
            int price = scanner.nextInt();
            Book book = new Book(title, author, price);
            library.addBook(book);
        }
        System.out.println("Books added successfully!");
    }

    private static void registerUsers(Scanner scanner, Users users) {
        System.out.print("How many users do you want to register? ");
        int NbrUsers = scanner.nextInt();
        for (int i = 0; i < NbrUsers; i++) {
            System.out.print("Enter the name of the user: ");
            String name = scanner.next();
            System.out.print("Enter the email of the user: ");
            String email = scanner.next();
            System.out.print("Enter the password of the user: ");
            String password = scanner.next();
            System.out.print("Enter the role of the user (e.g., ADMIN, MEMBER): ");
            String role = scanner.next();
            User user = new User(name, email, password, Role.valueOf(role.toUpperCase()));
            users.addUser(user);
        }
        System.out.println("Users registered successfully!");
    }

    private static void viewBooks(Library library) {
        var books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    private static void viewUsers(Users users) {
        var userList = users.getUsers();
        if (userList.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("Registered Users:");
            for (User user : userList) {
                System.out.println(
                        "- Name: " + user.getName() + ", Email: " + user.getEmail() + ", Role: " + user.getRole());
            }
        }
    }

    private static void manageBookTransaction(Scanner scanner, Library library, Users users) {
        System.out.print("Enter the name or email of the user: ");
        String query = scanner.next();
        User user_logged = users.findUser(query);

        if (user_logged != null) {
            System.out.println("User found: " + user_logged.getName());
            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowTitle = scanner.next();
                    Book bookToBorrow = library.findBook(borrowTitle);
                    if (bookToBorrow != null) {
                        library.userBorrowBook(user_logged, bookToBorrow);
                        System.out.println("Book borrowed successfully!");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.next();
                    Book bookToReturn = user_logged.getBorrowedBooks().stream()
                            .filter(book -> book.getTitle().equals(returnTitle)).findFirst().orElse(null);
                    if (bookToReturn != null) {
                        library.userReturnBook(user_logged, bookToReturn);
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("You have not borrowed this book.");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
