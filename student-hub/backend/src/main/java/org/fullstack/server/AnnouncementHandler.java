package org.fullstack.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.fullstack.model.Announcement;
import org.fullstack.service.AnnouncementService;
import org.fullstack.util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AnnouncementHandler implements HttpHandler {

    private final AnnouncementService service;

    public AnnouncementHandler(AnnouncementService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        addCorsHeaders(exchange);

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
            return;
        }

        try {
            if ("GET".equalsIgnoreCase(method) && "/api/announcements".equals(path)) {
                handleGetAll(exchange);
                return;
            }

            if ("POST".equalsIgnoreCase(method) && "/api/announcements".equals(path)) {
                handleCreate(exchange);
                return;
            }

            if ("DELETE".equalsIgnoreCase(method) && path.startsWith("/api/announcements/")) {
                handleDelete(exchange, path);
                return;
            }

            sendJson(exchange, 404, "{\"error\":\"Not found\"}");
        } catch (Exception error) {
            sendJson(exchange, 500, "{\"error\":\"Server error\"}");
        }
    }

    private void handleGetAll(HttpExchange exchange) throws IOException {
        sendJson(exchange, 200, service.toJsonArray());
    }

    private void handleCreate(HttpExchange exchange) throws IOException {
        String body = readBody(exchange.getRequestBody());
        String author = JsonUtil.readString(body, "author");
        String title = JsonUtil.readString(body, "title");
        String message = JsonUtil.readString(body, "body");

        if (author.isEmpty() || title.isEmpty() || message.isEmpty()) {
            sendJson(exchange, 400, "{\"error\":\"author, title and body are required\"}");
            return;
        }

        Announcement created = service.add(author, title, message);
        sendJson(exchange, 201, created.toJson());
    }

    private void handleDelete(HttpExchange exchange, String path) throws IOException {
        String idPart = path.substring("/api/announcements/".length());
        long id;

        try {
            id = Long.parseLong(idPart);
        } catch (NumberFormatException error) {
            sendJson(exchange, 400, "{\"error\":\"Invalid id\"}");
            return;
        }

        boolean removed = service.delete(id);
        if (!removed) {
            sendJson(exchange, 404, "{\"error\":\"Announcement not found\"}");
            return;
        }

        sendJson(exchange, 200, "{\"ok\":true}");
    }

    private static String readBody(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void sendJson(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    private static void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }
}
