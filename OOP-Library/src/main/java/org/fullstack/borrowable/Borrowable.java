package org.fullstack.borrowable;

import org.fullstack.model.Member;

/**
 * OOP CONCEPT: INTERFACE
 * ----------------------
 * An interface is a contract — it lists WHAT a class must do, not HOW.
 * Any class that "implements" Borrowable promises to provide these methods.
 *
 * Why use an interface here?
 * - Today we borrow physical books. Tomorrow we might add e-books or magazines.
 * - All borrowable items share the same behavior (borrow / return) but work differently inside.
 * - The Library can treat every item as Borrowable (polymorphism) without caring about the exact type.
 *
 * Interface vs abstract class:
 * - Interface  → "can do" (capability): Payable, Drawable, Borrowable
 * - Abstract   → "is a" (family):     Animal, Operation
 */
public interface Borrowable {

    /**
     * Try to lend this item to a member.
     *
     * @return true if borrow succeeded, false if item is already out or member limit reached
     */
    boolean borrow(Member member);

    /** Mark this item as returned and available again. */
    void returnItem();

    /** Human-readable title shown in menus and listings. */
    String getTitle();

    /** Unique identifier used to find this item in the catalog. */
    String getIsbn();

    /** Whether the item can be borrowed right now. */
    boolean isAvailable();
}
