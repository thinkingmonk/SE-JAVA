package org.fullstack.model;

import org.fullstack.borrowable.Borrowable;

/**
 * OOP CONCEPTS: CLASS, OBJECT, ENCAPSULATION, IMPLEMENTS (INTERFACE)
 * ------------------------------------------------------------------
 * - CLASS  → blueprint for books (fields + methods).
 * - OBJECT → each "new Book(...)" creates a separate book in memory.
 * - ENCAPSULATION → fields are private; outside code must use public methods.
 * - IMPLEMENTS Borrowable → Book agrees to provide all methods from the interface.
 *
 * "Book IS-A Borrowable" (it fulfills the borrowable contract).
 */
public class Book implements Borrowable {

    // OOP: STATIC field — ONE copy shared by ALL Book objects (belongs to the class).
    // Every time we construct a Book, this counter goes up.
    private static int totalBooksCreated = 0;

    // OOP: INSTANCE fields — each Book object has its own copy of these values.
    private final String title;
    private final String isbn;
    private boolean available;
    private Member borrowedBy;

    /**
     * OOP: CONSTRUCTOR — runs automatically when you write new Book(...).
     * "this" refers to the object currently being created.
     */
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.available = true;
        this.borrowedBy = null;
        totalBooksCreated++;
    }

    // OOP: STATIC method — call via Book.getTotalBooksCreated(), no object needed.
    public static int getTotalBooksCreated() {
        return totalBooksCreated;
    }

    /**
     * OOP: @Override — we replace the interface method with our own logic.
     * The compiler checks that the method signature matches Borrowable.borrow(Member).
     */
    @Override
    public boolean borrow(Member member) {
        // Guard clauses keep invalid states out (encapsulation protects rules).
        if (!available) {
            return false;
        }
        if (!member.canBorrowMore()) {
            return false;
        }

        available = false;
        borrowedBy = member;
        member.addBorrowedBook(this);
        return true;
    }

    @Override
    public void returnItem() {
        if (borrowedBy != null) {
            borrowedBy.removeBorrowedBook(this);
            borrowedBy = null;
        }
        available = true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    /** Extra behavior specific to Book — not part of the Borrowable interface. */
    public String getBorrowerName() {
        return borrowedBy == null ? "—" : borrowedBy.getName();
    }

    @Override
    public String toString() {
        String status = available ? "available" : "borrowed by " + getBorrowerName();
        return title + " [" + isbn + "] — " + status;
    }
}
