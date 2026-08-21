package org.fullstack.util;

import java.util.Scanner;

/** Reusable console input helpers — keeps Scanner logic out of menu handlers. */
public final class InputHelper {

    private InputHelper() {
    }

    public static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(Scanner scanner, String prompt) {
        return Integer.parseInt(readLine(scanner, prompt));
    }
}
