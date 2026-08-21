package org.fullstack.util;

import org.fullstack.constant.LibraryConstants;
import org.fullstack.model.Book;
import org.fullstack.model.Member;

import java.util.List;

/** Console output helpers — presentation only, no business rules. */
public final class DisplayHelper {

    private DisplayHelper() {
    }

    public static void printMenu() {
        System.out.println(LibraryConstants.MENU_LIST_ALL_BOOKS);
        System.out.println(LibraryConstants.MENU_LIST_AVAILABLE);
        System.out.println(LibraryConstants.MENU_LIST_MEMBERS);
        System.out.println(LibraryConstants.MENU_ADD_BOOK);
        System.out.println(LibraryConstants.MENU_REGISTER_MEMBER);
        System.out.println(LibraryConstants.MENU_BORROW);
        System.out.println(LibraryConstants.MENU_RETURN);
        System.out.println(LibraryConstants.MENU_MEMBER_BORROWED);
        System.out.println(LibraryConstants.MENU_EXIT);
    }

    public static void printBooks(String heading, List<Book> books) {
        System.out.println(heading);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void printMembers(List<Member> members) {
        System.out.println(LibraryConstants.HEADING_MEMBERS);
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public static void printBorrowedBooks(Member member) {
        System.out.println("--- Borrowed by " + member.getName() + " ---");
        if (member.getBorrowedBooks().isEmpty()) {
            System.out.println(LibraryConstants.MSG_NO_BOOKS_BORROWED);
            return;
        }
        for (Book book : member.getBorrowedBooks()) {
            System.out.println(book.getTitle() + " [" + book.getIsbn() + "]");
        }
    }
}
