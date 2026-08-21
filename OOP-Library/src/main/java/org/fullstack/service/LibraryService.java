package org.fullstack.service;

import org.fullstack.borrowable.Borrowable;
import org.fullstack.model.Book;
import org.fullstack.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * OOP CONCEPT: COMPOSITION + POLYMORPHISM
 * ---------------------------------------
 * COMPOSITION — LibraryService HAS-A catalog of books and a list of members.
 * POLYMORPHISM — borrow/return work through Borrowable references, not concrete types.
 *
 * This service layer coordinates Book and Member objects; it has no console I/O.
 */
public class LibraryService {

    private final List<Book> catalog;
    private final List<Member> members;

    public LibraryService() {
        this.catalog = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        catalog.add(book);
    }

    public boolean registerMember(Member member) {
        for (Member existing : members) {
            if (existing.getId() == member.getId()) {
                return false;
            }
        }
        members.add(member);
        return true;
    }

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
        Borrowable item = bookOpt.get();

        return item.borrow(member);
    }

    public boolean returnBook(String isbn) {
        Optional<Book> bookOpt = findBookByIsbn(isbn);
        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book not found: " + isbn);
        }

        Borrowable item = bookOpt.get();
        if (item.isAvailable()) {
            return false;
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
}
