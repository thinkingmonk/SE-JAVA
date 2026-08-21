package org.fullstack.seed;

import org.fullstack.model.Book;
import org.fullstack.model.Member;
import org.fullstack.service.LibraryService;

/** Loads demo catalog and members so the app is usable immediately on startup. */
public final class SampleDataSeeder {

    private SampleDataSeeder() {
    }

    public static void seed(LibraryService library) {
        library.addBook(new Book("Java Basics", "ISBN-001"));
        library.addBook(new Book("OOP in Java", "ISBN-002"));
        library.addBook(new Book("Data Structures", "ISBN-003"));

        library.registerMember(new Member(101, "Riya"));
        library.registerMember(new Member(102, "Aman"));
    }
}
