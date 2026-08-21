package org.fullstack.model;

import org.fullstack.constant.LibraryConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * OOP CONCEPT: ENCAPSULATION + COMPOSITION (has-a)
 * ------------------------------------------------
 * A Member HAS-A list of borrowed books (composition).
 *   Member ──has──> List<Book>
 *
 * We do not expose the internal list directly — callers get an unmodifiable copy
 * so nobody outside can break our borrowing rules.
 */
public class Member {

    private final int id;
    private final String name;
    private final int maxBooks;
    private final List<Book> borrowedBooks;

    public Member(int id, String name) {
        this(id, name, LibraryConstants.DEFAULT_MAX_BOOKS_PER_MEMBER);
    }

    public Member(int id, String name, int maxBooks) {
        this.id = id;
        this.name = name;
        this.maxBooks = maxBooks;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    /** Package-private: only classes in org.fullstack.model (e.g. Book) should call this. */
    void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean canBorrowMore() {
        return borrowedBooks.size() < maxBooks;
    }

    public int getBorrowedCount() {
        return borrowedBooks.size();
    }

    /**
     * Returns a read-only view — encapsulation prevents external code from
     * doing borrowedBooks.clear() and corrupting library state.
     */
    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    @Override
    public String toString() {
        return "Member #" + id + ": " + name + " (borrowed " + borrowedBooks.size() + "/" + maxBooks + ")";
    }
}
