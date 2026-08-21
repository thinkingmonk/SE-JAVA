package org.fullstack.constant;

/**
 * Central place for fixed values used across the library app.
 * Keeps magic strings and numbers out of business and UI code.
 */
public final class LibraryConstants {

    private LibraryConstants() {
    }

    public static final String APP_TITLE = "=== TCET Library Management System ===";
    public static final String SAMPLE_DATA_LOADED = "Sample books and members loaded.\n";

    public static final int DEFAULT_MAX_BOOKS_PER_MEMBER = 3;

    public static final String CHOOSE_OPTION = "Choose option: ";

    public static final String MENU_LIST_ALL_BOOKS = "1. List all books";
    public static final String MENU_LIST_AVAILABLE = "2. List available books";
    public static final String MENU_LIST_MEMBERS = "3. List members";
    public static final String MENU_ADD_BOOK = "4. Add book";
    public static final String MENU_REGISTER_MEMBER = "5. Register member";
    public static final String MENU_BORROW = "6. Borrow book";
    public static final String MENU_RETURN = "7. Return book";
    public static final String MENU_MEMBER_BORROWED = "8. Show member's borrowed books";
    public static final String MENU_EXIT = "9. Exit";

    public static final String PROMPT_TITLE = "Title: ";
    public static final String PROMPT_ISBN = "ISBN: ";
    public static final String PROMPT_MEMBER_ID = "Member ID: ";
    public static final String PROMPT_NAME = "Name: ";
    public static final String PROMPT_BOOK_ISBN = "Book ISBN: ";

    public static final String HEADING_ALL_BOOKS = "--- All books ---";
    public static final String HEADING_AVAILABLE = "--- Available books ---";
    public static final String HEADING_MEMBERS = "--- Members ---";

    public static final String MSG_INVALID_OPTION = "Invalid option. Try 1–9.";
    public static final String MSG_NO_BOOKS_AVAILABLE = "No books available right now.";
    public static final String MSG_ISBN_EXISTS = "A book with that ISBN already exists.";
    public static final String MSG_BOOK_ADDED = "Added: ";
    public static final String MSG_MEMBER_REGISTERED = "Registered: ";
    public static final String MSG_MEMBER_ID_EXISTS = "Member ID %d already exists.";
    public static final String MSG_BORROW_SUCCESS = "Borrow successful.";
    public static final String MSG_BORROW_FAILED = "Borrow failed — book unavailable or member limit reached.";
    public static final String MSG_RETURN_SUCCESS = "Return successful.";
    public static final String MSG_NOT_BORROWED = "That book was not borrowed.";
    public static final String MSG_MEMBER_NOT_FOUND = "Member not found: ";
    public static final String MSG_NO_BOOKS_BORROWED = "No books borrowed.";
    public static final String MSG_ERROR_PREFIX = "Error: ";
    public static final String MSG_GOODBYE = "Goodbye!";
    public static final String MSG_TOTAL_BOOKS = "Total books ever created: ";
}
