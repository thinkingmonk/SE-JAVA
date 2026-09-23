package org.fullstack.service;

import org.fullstack.model.Announcement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * In-memory store for announcements.
 * In a real app this would talk to a database.
 */
public class AnnouncementService {

    private final List<Announcement> announcements = new CopyOnWriteArrayList<>();

    public AnnouncementService() {
        announcements.add(Announcement.create(
                "Faculty",
                "Welcome to TCET Student Hub",
                "This board connects your HTML/CSS/JS frontend to a basic Java backend."
        ));
        announcements.add(Announcement.create(
                "Lab Team",
                "Git collaboration tip",
                "Create a branch per feature, open a Pull Request, and merge after review."
        ));
    }

    public List<Announcement> findAll() {
        List<Announcement> copy = new ArrayList<>(announcements);
        copy.sort(Comparator.comparing(Announcement::getCreatedAt).reversed());
        return copy;
    }

    public Announcement add(String author, String title, String body) {
        Announcement announcement = Announcement.create(author, title, body);
        announcements.add(announcement);
        return announcement;
    }

    public boolean delete(long id) {
        Optional<Announcement> match = announcements.stream()
                .filter(item -> item.getId() == id)
                .findFirst();

        if (match.isEmpty()) {
            return false;
        }

        announcements.remove(match.get());
        return true;
    }

    public String toJsonArray() {
        StringBuilder builder = new StringBuilder("[");
        List<Announcement> all = findAll();

        for (int i = 0; i < all.size(); i++) {
            builder.append(all.get(i).toJson());
            if (i < all.size() - 1) {
                builder.append(",");
            }
        }

        builder.append("]");
        return builder.toString();
    }
}
