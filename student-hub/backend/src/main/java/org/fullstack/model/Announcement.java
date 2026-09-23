package org.fullstack.model;

import java.time.Instant;

/**
 * Simple data class for one announcement.
 * No external JSON library — we build/parse JSON manually for teaching clarity.
 */
public class Announcement {

    private final long id;
    private final String author;
    private final String title;
    private final String body;
    private final String createdAt;

    public Announcement(long id, String author, String title, String body, String createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public static Announcement create(String author, String title, String body) {
        return new Announcement(
                System.currentTimeMillis(),
                author,
                title,
                body,
                Instant.now().toString()
        );
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String toJson() {
        return "{"
                + "\"id\":" + id + ","
                + "\"author\":\"" + escape(author) + "\","
                + "\"title\":\"" + escape(title) + "\","
                + "\"body\":\"" + escape(body) + "\","
                + "\"createdAt\":\"" + escape(createdAt) + "\""
                + "}";
    }

    private static String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }
}
