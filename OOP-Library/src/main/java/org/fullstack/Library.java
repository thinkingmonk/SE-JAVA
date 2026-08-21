package org.fullstack;

import org.fullstack.borrowable.Borrowable;
import org.fullstack.model.Book;
import org.fullstack.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * OOP CONCEPT: COMPOSITION + POLYMORPHISM
 * ---------------------------------------
 * COMPOSITION — Library HAS-A catalog of books and a list of members.
 *   Library ──has──> List<Book>
 *   Library ──has──> List<Member>
 *
 * POLYMORPHISM — methods accept/loop over Borrowable references.
 *   Borrowable item = book;   // a Book IS-A Borrowable
 *   item.borrow(member);      // correct overridden method runs at runtime
 *
 * This class is the "service layer": it coordinates Book and Member objects.
 */
public class Library {

    // Composition: the library owns its catalog (books stay inside this list).
    private final List<Book> catalog;
    private final List<Member> members;

    public Library() {
        this.catalog = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    /** Add a new book object to the catalog. */
    public void addBook(Book book) {
        catalog.add(book);
    }

    /** Register a member. Returns false if ID already exists. */
    public boolean registerMember(Member member) {
        for (Member existing : members) {
            if (existing.getId() == member.getId()) {
                return false;
            }
        }
        members.add(member);
        return true;
    }

    /**
     * OOP: POLYMORPHISM in action.
     * We find a Book, but the borrow logic lives on the Borrowable interface contract.
     */
    public boolean borrowBook(int memberId, String isbn) {
        Optional<Member> memberOpt = findMemberById(memberId);
        Optional<Book> bookOpt = findBookByIsbn(isbn);

        if (memberOpt.isEmpty()) {
            throw new IllegalArgumentException("Member not found: " + memberId);
        }
        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }

        Member member = memberOpt.get();
        Borrowable item = bookOpt.get(); // upcasting: Book → Borrowable

        return item.borrow(member);
    }

    public boolean returnBook(String isbn) {
        Optional<Book> bookOpt = findBookByIsbn(isbn);
        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }

        Borrowable item = bookOpt.get();
        if (item.isAvailable()) {
            return false; // nothing to return
        }

        item.returnItem();
        return true;
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : catalog) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getAllBooks() {
        return List.copyOf(catalog);
    }

    public List<Member> getAllMembers() {
        return List.copyOf(members);
    }

    public Optional<Member> findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public Optional<Book> findBookByIsbn(String isbn) {
        for (Book book : catalog) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    /** Demo helper — pre-loads sample data so Main is shorter. */
    public void seedSampleData() {
        addBook(new Book("Java Basics", "ISBN-001"));
        addBook(new Book("OOP in Java", "ISBN-002"));
        addBook(new Book("Data Structures", "ISBN-003"));

        registerMember(new Member(101, "Riya"));
        registerMember(new Member(102, "Aman"));
    }
}
