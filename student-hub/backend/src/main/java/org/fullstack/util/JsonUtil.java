package org.fullstack.util;

/**
 * Tiny JSON helpers for teaching — not production-grade parsing.
 */
public final class JsonUtil {

    private JsonUtil() {
    }

    public static String readString(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int start = json.indexOf(pattern);
        if (start < 0) {
            return "";
        }

        start += pattern.length();
        StringBuilder value = new StringBuilder();

        for (int i = start; i < json.length(); i++) {
            char ch = json.charAt(i);

            if (ch == '\\' && i + 1 < json.length()) {
                char next = json.charAt(i + 1);
                if (next == 'n') {
                    value.append('\n');
                } else if (next == '"') {
                    value.append('"');
                } else if (next == '\\') {
                    value.append('\\');
                } else {
                    value.append(next);
                }
                i++;
                continue;
            }

            if (ch == '"') {
                break;
            }

            value.append(ch);
        }

        return value.toString().trim();
    }
}
