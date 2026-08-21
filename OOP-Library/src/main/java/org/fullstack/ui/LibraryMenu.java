package org.fullstack.ui;

import org.fullstack.constant.LibraryConstants;
import org.fullstack.model.Book;
import org.fullstack.model.Member;
import org.fullstack.service.LibraryService;
import org.fullstack.util.DisplayHelper;
import org.fullstack.util.InputHelper;

import java.util.List;
import java.util.Scanner;

/** Console menu — reads user input and delegates work to LibraryService. */
public class LibraryMenu {

    private final Scanner scanner;
    private final LibraryService library;

    public LibraryMenu(Scanner scanner, LibraryService library) {
        this.scanner = scanner;
        this.library = library;
    }

    public void run() {
        boolean running = true;
        while (running) {
            DisplayHelper.printMenu();
            String choice = InputHelper.readLine(scanner, LibraryConstants.CHOOSE_OPTION);

            switch (choice) {
                case "1" -> listAllBooks();
                case "2" -> listAvailableBooks();
                case "3" -> DisplayHelper.printMembers(library.getAllMembers());
                case "4" -> addBook();
                case "5" -> registerMember();
                case "6" -> borrowBook();
                case "7" -> returnBook();
                case "8" -> showMemberBorrowedBooks();
                case "9" -> {
                    System.out.println(LibraryConstants.MSG_TOTAL_BOOKS + Book.getTotalBooksCreated());
                    System.out.println(LibraryConstants.MSG_GOODBYE);
                    running = false;
                }
                default -> System.out.println(LibraryConstants.MSG_INVALID_OPTION);
            }
            System.out.println();
        }
    }

    private void listAllBooks() {
        DisplayHelper.printBooks(LibraryConstants.HEADING_ALL_BOOKS, library.getAllBooks());
    }

    private void listAvailableBooks() {
        List<Book> available = library.getAvailableBooks();
        if (available.isEmpty()) {
            System.out.println(LibraryConstants.MSG_NO_BOOKS_AVAILABLE);
            return;
        }
        DisplayHelper.printBooks(LibraryConstants.HEADING_AVAILABLE, available);
    }

    private void addBook() {
        String title = InputHelper.readLine(scanner, LibraryConstants.PROMPT_TITLE);
        String isbn = InputHelper.readLine(scanner, LibraryConstants.PROMPT_ISBN);

        if (library.findBookByIsbn(isbn).isPresent()) {
            System.out.println(LibraryConstants.MSG_ISBN_EXISTS);
            return;
        }

        Book book = new Book(title, isbn);
        library.addBook(book);
        System.out.println(LibraryConstants.MSG_BOOK_ADDED + book);
    }

    private void registerMember() {
        int id = InputHelper.readInt(scanner, LibraryConstants.PROMPT_MEMBER_ID);
        String name = InputHelper.readLine(scanner, LibraryConstants.PROMPT_NAME);

        Member member = new Member(id, name);
        if (library.registerMember(member)) {
            System.out.println(LibraryConstants.MSG_MEMBER_REGISTERED + member);
        } else {
            System.out.printf(LibraryConstants.MSG_MEMBER_ID_EXISTS + "%n", id);
        }
    }

    private void borrowBook() {
        int memberId = InputHelper.readInt(scanner, LibraryConstants.PROMPT_MEMBER_ID);
        String isbn = InputHelper.readLine(scanner, LibraryConstants.PROMPT_BOOK_ISBN);

        try {
            boolean success = library.borrowBook(memberId, isbn);
            System.out.println(success ? LibraryConstants.MSG_BORROW_SUCCESS : LibraryConstants.MSG_BORROW_FAILED);
        } catch (IllegalArgumentException e) {
            System.out.println(LibraryConstants.MSG_ERROR_PREFIX + e.getMessage());
        }
    }

    private void returnBook() {
        String isbn = InputHelper.readLine(scanner, LibraryConstants.PROMPT_BOOK_ISBN);

        try {
            boolean success = library.returnBook(isbn);
            System.out.println(success ? LibraryConstants.MSG_RETURN_SUCCESS : LibraryConstants.MSG_NOT_BORROWED);
        } catch (IllegalArgumentException e) {
            System.out.println(LibraryConstants.MSG_ERROR_PREFIX + e.getMessage());
        }
    }

    private void showMemberBorrowedBooks() {
        int memberId = InputHelper.readInt(scanner, LibraryConstants.PROMPT_MEMBER_ID);

        library.findMemberById(memberId).ifPresentOrElse(
                DisplayHelper::printBorrowedBooks,
                () -> System.out.println(LibraryConstants.MSG_MEMBER_NOT_FOUND + memberId)
        );
    }
}
