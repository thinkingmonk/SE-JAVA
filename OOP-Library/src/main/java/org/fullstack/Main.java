package org.fullstack;

import org.fullstack.model.Book;
import org.fullstack.model.Member;

import java.util.List;
import java.util.Scanner;

/**
 * OOP CONCEPT: ENTRY POINT + OBJECTS IN ACTION
 * --------------------------------------------
 * main() is static — the JVM calls it without creating a Main object first.
 *
 * Here we:
 * 1. Create a Library object (the system that manages books and members).
 * 2. Create Book and Member objects and hand them to the library.
 * 3. Call methods on objects — each object carries its own state.
 *
 * Run: ./gradlew run   (or green play button on Main in IntelliJ)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.seedSampleData();

        System.out.println("=== TCET Library Management System ===");
        System.out.println("Sample books and members loaded.\n");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listAllBooks(library);
                case "2" -> listAvailableBooks(library);
                case "3" -> listMembers(library);
                case "4" -> addBook(scanner, library);
                case "5" -> registerMember(scanner, library);
                case "6" -> borrowBook(scanner, library);
                case "7" -> returnBook(scanner, library);
                case "8" -> showMemberBorrowedBooks(scanner, library);
                case "9" -> {
                    System.out.println("Total books ever created: " + Book.getTotalBooksCreated());
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try 1–9.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. List all books");
        System.out.println("2. List available books");
        System.out.println("3. List members");
        System.out.println("4. Add book");
        System.out.println("5. Register member");
        System.out.println("6. Borrow book");
        System.out.println("7. Return book");
        System.out.println("8. Show member's borrowed books");
        System.out.println("9. Exit");
    }

    private static void listAllBooks(Library library) {
        System.out.println("--- All books ---");
        for (Book book : library.getAllBooks()) {
            System.out.println(book);
        }
    }

    private static void listAvailableBooks(Library library) {
        System.out.println("--- Available books ---");
        List<Book> available = library.getAvailableBooks();
        if (available.isEmpty()) {
            System.out.println("No books available right now.");
            return;
        }
        for (Book book : available) {
            System.out.println(book);
        }
    }

    private static void listMembers(Library library) {
        System.out.println("--- Members ---");
        for (Member member : library.getAllMembers()) {
            System.out.println(member);
        }
    }

    private static void addBook(Scanner scanner, Library library) {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        if (library.findBookByIsbn(isbn).isPresent()) {
            System.out.println("A book with that ISBN already exists.");
            return;
        }

        // OOP: new Book(...) creates an object and calls the Book constructor.
        Book book = new Book(title, isbn);
        library.addBook(book);
        System.out.println("Added: " + book);
    }

    private static void registerMember(Scanner scanner, Library library) {
        System.out.print("Member ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        Member member = new Member(id, name);
        if (library.registerMember(member)) {
            System.out.println("Registered: " + member);
        } else {
            System.out.println("Member ID " + id + " already exists.");
        }
    }

    private static void borrowBook(Scanner scanner, Library library) {
        System.out.print("Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();

        try {
            boolean success = library.borrowBook(memberId, isbn);
            if (success) {
                System.out.println("Borrow successful.");
            } else {
                System.out.println("Borrow failed — book unavailable or member limit reached.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook(Scanner scanner, Library library) {
        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();

        try {
            boolean success = library.returnBook(isbn);
            if (success) {
                System.out.println("Return successful.");
            } else {
                System.out.println("That book was not borrowed.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showMemberBorrowedBooks(Scanner scanner, Library library) {
        System.out.print("Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine().trim());

        library.findMemberById(memberId).ifPresentOrElse(member -> {
            System.out.println("--- Borrowed by " + member.getName() + " ---");
            if (member.getBorrowedBooks().isEmpty()) {
                System.out.println("No books borrowed.");
            } else {
                for (Book book : member.getBorrowedBooks()) {
                    System.out.println(book.getTitle() + " [" + book.getIsbn() + "]");
                }
            }
        }, () -> System.out.println("Member not found: " + memberId));
    }
}
