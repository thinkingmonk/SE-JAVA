package org.fullstack.server;

import com.sun.net.httpserver.HttpServer;
import org.fullstack.service.AnnouncementService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApp {

    private final AnnouncementService service;
    private HttpServer server;

    public HttpServerApp(AnnouncementService service) {
        this.service = service;
    }

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/announcements", new AnnouncementHandler(service));
        server.createContext("/api/announcements/", new AnnouncementHandler(service));
        server.setExecutor(null);
        server.start();

        System.out.println("Student Hub API running at http://localhost:" + port);
        System.out.println("GET    /api/announcements");
        System.out.println("POST   /api/announcements");
        System.out.println("DELETE /api/announcements/{id}");
        System.out.println("Press Ctrl+C to stop.");
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }
}
