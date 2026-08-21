package org.fullstack;

import org.fullstack.constant.LibraryConstants;
import org.fullstack.seed.SampleDataSeeder;
import org.fullstack.service.LibraryService;
import org.fullstack.ui.LibraryMenu;

import java.util.Scanner;

/**
 * OOP CONCEPT: ENTRY POINT + OBJECTS IN ACTION
 * --------------------------------------------
 * main() is static — the JVM calls it without creating a Main object first.
 *
 * This class only wires dependencies and starts the app.
 * UI lives in ui/, business rules in service/, models in model/.
 *
 * Run: ./gradlew run   (or green play button on Main in IntelliJ)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService library = new LibraryService();
        SampleDataSeeder.seed(library);

        System.out.println(LibraryConstants.APP_TITLE);
        System.out.println(LibraryConstants.SAMPLE_DATA_LOADED);

        new LibraryMenu(scanner, library).run();

        scanner.close();
    }
}
