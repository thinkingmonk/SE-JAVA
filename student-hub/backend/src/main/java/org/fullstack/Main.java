package org.fullstack;

import org.fullstack.server.HttpServerApp;
import org.fullstack.service.AnnouncementService;

/**
 * Entry point for the Student Hub Java API.
 *
 * Run in IntelliJ: green play on Main
 * Or from terminal: ./gradlew run
 */
public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        AnnouncementService service = new AnnouncementService();
        HttpServerApp app = new HttpServerApp(service);

        try {
            app.start(PORT);
        } catch (Exception error) {
            System.err.println("Could not start server on port " + PORT);
            System.err.println(error.getMessage());
        }
    }
}
