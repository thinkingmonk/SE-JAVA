# Full-Stack Mini Project — HTML, CSS, JavaScript, Java & Git Collaboration

One integrated lab covering **static site development**, **front-end + Java back-end integration**, and **team collaboration on GitHub** (branches, merging, pull requests).

Same teaching flow as [html-css.md](./html-css.md) and [javascript-git-session.md](./javascript-git-session.md): short idea → example → exercise with solution.

**Reference project:** [student-hub/](./student-hub/) — TCET Student Hub (announcement board).

**Prerequisites:** Skim [html-css.md](./html-css.md) (sections 1–16), [javascript-git-session.md](./javascript-git-session.md) (sections 1–7), and [git-github.md](./git-github.md). Java setup: [setup.md](./setup.md).

**Tools:** VS Code + [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer), Chrome DevTools, IntelliJ IDEA, [Git](https://git-scm.com/downloads).

---

## Session plan (~3.5 hours)

| Block | Time | Topics |
|-------|------|--------|
| **Hour 1** | 0:00–1:00 | Architecture, static site (HTML5 + CSS3), semantic layout |
| **Hour 2** | 1:00–2:00 | JavaScript: `fetch`, DOM, events, offline fallback (`localStorage`) |
| **Hour 3** | 2:00–3:00 | Java `HttpServer` API, JSON, CORS, front-end ↔ back-end integration |
| **Step 6** | ~30 min | Git collaboration — branches, PRs, merge ([build flow](#11-build-the-student-hub-step-by-step) only) |

Git is taught inside the **step-by-step build** (Step 6), not as a separate lecture hour. Full reference: [git-github.md](./git-github.md).

**Project folder layout:**

```
student-hub/
  frontend/
    index.html
    style.css
    script.js
  backend/
    build.gradle
    settings.gradle
    src/main/java/org/fullstack/
      Main.java
      model/Announcement.java
      service/AnnouncementService.java
      server/HttpServerApp.java
      server/AnnouncementHandler.java
      util/JsonUtil.java
  .gitignore
```

---

## Table of contents

### Architecture & static site
1. [How the pieces fit together](#1-how-the-pieces-fit-together)
2. [HTML5 structure for the hub](#2-html5-structure-for-the-hub)
3. [CSS layout: Flexbox + Grid](#3-css-layout-flexbox--grid)
4. [CSS variables and dark theme](#4-css-variables-and-dark-theme)

### JavaScript & API
5. [Connecting to the Java API with fetch](#5-connecting-to-the-java-api-with-fetch)
6. [DOM rendering from JSON data](#6-dom-rendering-from-json-data)
7. [Offline fallback with localStorage](#7-offline-fallback-with-localstorage)

### Java backend
8. [Java HttpServer basics](#8-java-httpserver-basics)
9. [REST-style endpoints](#9-rest-style-endpoints)
10. [CORS — why the browser blocks you](#10-cors--why-the-browser-blocks-you)

### Mini project
11. [Build the Student Hub step by step](#11-build-the-student-hub-step-by-step) (includes Git collaboration in Step 6)
12. [Test checklist & assignment hand-in](#12-test-checklist--assignment-hand-in)

---

# Hour 1 — Static site (0:00–1:00)

## 1. How the pieces fit together

A **full-stack** app has at least two layers:

| Layer | Technology | Job in Student Hub |
|-------|------------|-------------------|
| **Front-end** | HTML, CSS, JavaScript | Page layout, forms, live updates |
| **Back-end** | Java | Store announcements, expose API |
| **Version control** | Git + GitHub | Team collaboration, history, review |

```
Browser (Live Server :5500)
    │
    │  fetch("http://localhost:8080/api/announcements")
    ▼
Java HttpServer (:8080)
    │
    ▼
AnnouncementService (in-memory list)
```

**Static site** = HTML/CSS/JS files served as-is (no server logic). Live Server is enough for the front-end alone.

**Integrated site** = JavaScript calls the Java API. Both must run:

1. `./gradlew run` in `backend/` (port **8080**)
2. Live Server on `frontend/index.html` (port **5500** or similar)

The status badge in the UI shows **Offline mode** (localStorage) or **Java API connected**.

### Exercise

**Task:** In one sentence each, explain the job of HTML, CSS, JavaScript, and Java in this project.

**Solution:**

- **HTML** — Structure: header, form, announcement list.
- **CSS** — Layout (grid), colors, dark theme, card styling.
- **JavaScript** — Form submit, `fetch` to API, render list, offline fallback.
- **Java** — HTTP server, business logic, JSON responses.

---

## 2. HTML5 structure for the hub

Follow the same skeleton as [html-css.md §2](./html-css.md#2-html5-document-structure). Key semantic tags:

| Tag | Use in Student Hub |
|-----|-------------------|
| `<header>` | Title, API status, theme button |
| `<main>` | Form + feed (primary content) |
| `<section>` | Group form vs announcement list |
| `<form>` | Post new announcement |
| `<footer>` | Credits / hints |

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TCET Student Hub</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header class="header">…</header>
    <main class="container layout">…</main>
    <footer class="footer">…</footer>
    <script src="script.js"></script>
</body>
</html>
```

**Form fields** (see [html-css.md §8 — Forms](./html-css.md#8-forms)):

```html
<form id="announcementForm">
    <label for="author">Your name</label>
    <input id="author" type="text" required maxlength="40">

    <label for="title">Title</label>
    <input id="title" type="text" required maxlength="80">

    <label for="body">Message</label>
    <textarea id="body" rows="4" required maxlength="500"></textarea>

    <button type="submit">Publish</button>
</form>
```

**Accessibility tips**

- Pair every `<input>` with `<label for="…">`.
- Use `aria-live="polite"` on the list so screen readers hear new posts.
- Put `role="status"` on the API connection badge.

### Exercise

**Task:** Add a `<p class="tagline">` under the `<h1>` with text: `Announcements from your class`.

**Solution:**

```html
<h1>TCET Student Hub</h1>
<p class="tagline">Announcements from your class</p>
```

---

## 3. CSS layout: Flexbox + Grid

Reuse patterns from [html-css.md §13–14](./html-css.md#13-display-and-flexbox).

**Two-column layout** with CSS Grid:

```css
.layout {
    display: grid;
    grid-template-columns: minmax(280px, 360px) 1fr;
    gap: 1.25rem;
    align-items: start;
}

@media (max-width: 820px) {
    .layout {
        grid-template-columns: 1fr;   /* stack on mobile */
    }
}
```

**Header** uses Flexbox to push the theme button to the right:

```css
.header__inner {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    gap: 1rem;
}
```

**Card** pattern (surface + border + shadow) — same idea as [html-css-js/](./html-css-js/):

```css
.card {
    background: var(--surface);
    border: 1px solid var(--border);
    border-radius: var(--radius);
    box-shadow: var(--shadow);
    padding: 1.25rem;
}
```

### Exercise

**Task:** Make the **Publish** button full width inside the form.

**Solution:**

```css
.btn--primary {
    width: 100%;
}
```

---

## 4. CSS variables and dark theme

Define tokens in `:root` (see [html-css.md §15](./html-css.md#15-css-variables)):

```css
:root {
    --bg: #f0f4f8;
    --surface: #ffffff;
    --text: #0f172a;
    --primary: #0d9488;
}

body.dark {
    --bg: #0b1220;
    --surface: #111827;
    --text: #f8fafc;
}
```

Toggle in JavaScript:

```js
themeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});
```

**Status badge** — visual feedback for API connection:

```css
.status--offline {
    background: #fef3c7;
    color: #92400e;
}

.status--online {
    background: rgba(22, 163, 74, 0.12);
    color: #16a34a;
}
```

### Exercise

**Task:** Change `--primary` to `#2563eb` (blue). What parts of the page update?

**Solution:** The **Publish** button and input focus outline — anything using `var(--primary)`.

---

# Hour 2 — JavaScript & API (1:00–2:00)

## 5. Connecting to the Java API with fetch

[`fetch`](https://developer.mozilla.org/en-US/docs/Web/API/fetch) is the modern way to call a server from the browser.

```js
const API_BASE = "http://localhost:8080";

async function fetchAnnouncements() {
    const response = await fetch(`${API_BASE}/api/announcements`);
    if (!response.ok) throw new Error("Bad response");
    return response.json();   // parse JSON body
}
```

| Part | Meaning |
|------|---------|
| `async` / `await` | Wait for network without freezing the page |
| `response.ok` | `true` when status is 200–299 |
| `response.json()` | Parse body as JavaScript object/array |

**POST** — send new announcement:

```js
async function createAnnouncement(payload) {
    const response = await fetch(`${API_BASE}/api/announcements`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error("Create failed");
    return response.json();
}
```

**DELETE:**

```js
await fetch(`${API_BASE}/api/announcements/${id}`, { method: "DELETE" });
```

### Exercise

**Task:** Log the raw response status after a successful GET.

**Solution:**

```js
const response = await fetch(`${API_BASE}/api/announcements`);
console.log(response.status);   // 200
return response.json();
```

---

## 6. DOM rendering from JSON data

The API returns an **array** of objects:

```json
[
  {
    "id": 1710000000000,
    "author": "Riya",
    "title": "Lab deadline",
    "body": "Submit by Friday 5 PM.",
    "createdAt": "2026-03-20T10:30:00Z"
  }
]
```

**Render loop** (same pattern as [javascript-git-session.md §8](./javascript-git-session.md#8-mini-project-task-board)):

```js
function renderAnnouncements() {
    const list = document.getElementById("announcementList");
    list.innerHTML = "";

    for (const item of announcements) {
        list.appendChild(createAnnouncementElement(item));
    }
}

function createAnnouncementElement(item) {
    const li = document.createElement("li");
    li.className = "announcement";

    const title = document.createElement("h3");
    title.textContent = item.title;

    const meta = document.createElement("p");
    meta.textContent = `${item.author} · ${formatDate(item.createdAt)}`;

    const body = document.createElement("p");
    body.textContent = item.body;

    li.appendChild(title);
    li.appendChild(meta);
    li.appendChild(body);
    return li;
}
```

**Form submit** — always `preventDefault` on forms:

```js
form.addEventListener("submit", async function (event) {
    event.preventDefault();
    const author = document.getElementById("author").value.trim();
    const title = document.getElementById("title").value.trim();
    const body = document.getElementById("body").value.trim();
    if (!author || !title || !body) return;

    await createAnnouncement({ author, title, body });
    form.reset();
    await refreshAnnouncements();
});
```

### Exercise

**Task:** Add a delete button that calls `DELETE /api/announcements/{id}` and refreshes the list.

**Solution:** See full implementation in [`student-hub/frontend/script.js`](./student-hub/frontend/script.js) — `createAnnouncementElement` + `deleteAnnouncement`.

---

## 7. Offline fallback with localStorage

If the Java server is **not** running, `fetch` throws. Catch it and use **localStorage**:

```js
async function fetchAnnouncements() {
    try {
        const response = await fetch(`${API_BASE}/api/announcements`);
        if (!response.ok) throw new Error("Bad response");
        setApiStatus(true);
        return response.json();
    } catch {
        setApiStatus(false);
        return loadLocal();
    }
}

function saveLocal(data) {
    localStorage.setItem("tcet-student-hub-announcements", JSON.stringify(data));
}

function loadLocal() {
    const raw = localStorage.getItem("tcet-student-hub-announcements");
    return raw ? JSON.parse(raw) : [];
}
```

| Mode | Data stored | Status badge |
|------|-------------|--------------|
| API up | Java server memory | Java API connected |
| API down | Browser localStorage | Offline mode |

This lets students demo the UI **before** the back-end is finished — important for team collaboration.

### Exercise

**Task:** Open DevTools → Application → Local Storage. Post one announcement with the server **stopped**. What key appears?

**Solution:** Key `tcet-student-hub-announcements` with a JSON array value.

---

# Hour 3 — Java backend (2:00–3:00)

## 8. Java HttpServer basics

Java includes a lightweight HTTP server — no Spring Boot required for this lab.

**Entry point** — [`student-hub/backend/src/main/java/org/fullstack/Main.java`](./student-hub/backend/src/main/java/org/fullstack/Main.java):

```java
public class Main {
    public static void main(String[] args) {
        AnnouncementService service = new AnnouncementService();
        HttpServerApp app = new HttpServerApp(service);
        app.start(8080);
    }
}
```

**Start the server** — [`HttpServerApp.java`](./student-hub/backend/src/main/java/org/fullstack/server/HttpServerApp.java):

```java
HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
server.createContext("/api/announcements", new AnnouncementHandler(service));
server.start();
System.out.println("API running at http://localhost:" + port);
```

**Run:**

```bash
cd student-hub/backend
./gradlew run
```

Or green **Run** on `Main` in IntelliJ.

**Package layout** (same style as [OOP-Library](./OOP-Library/)):

| Package | Responsibility |
|---------|----------------|
| `model` | `Announcement` data class |
| `service` | Business logic, in-memory store |
| `server` | HTTP routing |
| `util` | Small JSON helpers |

### Exercise

**Task:** Change the port to `9090`. What two files must you update?

**Solution:**

1. `Main.java` — `app.start(9090)`
2. `frontend/script.js` — `const API_BASE = "http://localhost:9090"`

---

## 9. REST-style endpoints

| Method | Path | Action |
|--------|------|--------|
| `GET` | `/api/announcements` | List all (JSON array) |
| `POST` | `/api/announcements` | Create one (JSON body) |
| `DELETE` | `/api/announcements/{id}` | Remove by id |

**Handler sketch** — [`AnnouncementHandler.java`](./student-hub/backend/src/main/java/org/fullstack/server/AnnouncementHandler.java):

```java
public void handle(HttpExchange exchange) throws IOException {
    String method = exchange.getRequestMethod();
    String path = exchange.getRequestURI().getPath();

    if ("GET".equalsIgnoreCase(method) && "/api/announcements".equals(path)) {
        sendJson(exchange, 200, service.toJsonArray());
        return;
    }

    if ("POST".equalsIgnoreCase(method) && "/api/announcements".equals(path)) {
        String body = readBody(exchange.getRequestBody());
        String author = JsonUtil.readString(body, "author");
        String title = JsonUtil.readString(body, "title");
        String message = JsonUtil.readString(body, "body");
        Announcement created = service.add(author, title, message);
        sendJson(exchange, 201, created.toJson());
        return;
    }
    // DELETE ...
}
```

**Model** — build JSON manually (no external library):

```java
public String toJson() {
    return "{"
        + "\"id\":" + id + ","
        + "\"author\":\"" + escape(author) + "\","
        + "\"title\":\"" + escape(title) + "\","
        + "\"body\":\"" + escape(body) + "\","
        + "\"createdAt\":\"" + escape(createdAt) + "\""
        + "}";
}
```

**Test with curl** (terminal):

```bash
curl http://localhost:8080/api/announcements

curl -X POST http://localhost:8080/api/announcements \
  -H "Content-Type: application/json" \
  -d '{"author":"Amit","title":"Test","body":"Hello from curl"}'
```

### Exercise

**Task:** POST an announcement via curl. Run GET again — is it in the list?

**Solution:** Yes — newest items appear first (`findAll` sorts by `createdAt` descending).

---

## 10. CORS — why the browser blocks you

The front-end runs on `http://127.0.0.1:5500` (Live Server). The API runs on `http://localhost:8080`. Different **origins** → browser enforces **CORS**.

Without CORS headers, `fetch` fails even if curl works.

**Fix** — add headers on every response:

```java
exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
```

Handle **preflight** `OPTIONS` requests:

```java
if ("OPTIONS".equalsIgnoreCase(method)) {
    exchange.sendResponseHeaders(204, -1);
    exchange.close();
    return;
}
```

| Tool | CORS enforced? |
|------|----------------|
| Browser `fetch` | Yes |
| curl / Postman | No |
| Java server | N/A |

### Exercise

**Task:** Temporarily remove CORS headers. Open the page — what appears in the Console?

**Solution:** A CORS error; status badge shows **Offline mode** and data comes from localStorage.

---

# Mini project — Student Hub

## 11. Build the Student Hub step by step

### Step 1 — Static shell (~20 min)

Create `frontend/index.html` with header, form, empty `<ul id="announcementList">`, footer. Link `style.css`. Verify in browser.

### Step 2 — Style the layout (~20 min)

Add CSS Grid `.layout`, card styles, form fields, responsive breakpoint at 820px. Add dark theme toggle (class on `body`).

### Step 3 — JavaScript offline mode (~25 min)

Implement `loadLocal`, `saveLocal`, `renderAnnouncements`, form submit. Confirm posts work **without** Java running.

### Step 4 — Java API (~30 min)

Create `Announcement`, `AnnouncementService`, `AnnouncementHandler`, `HttpServerApp`, `Main`. Run `./gradlew run`. Test with curl.

### Step 5 — Integration (~20 min)

Add `fetchAnnouncements`, `createAnnouncement`, `deleteAnnouncement` in `script.js`. Set `API_BASE`. Confirm status badge turns green.

### Step 6 — Git collaboration (~30 min)

Finish the app, then push it to GitHub as a team. This is the **only** Git section in this lab.

**6a — Init repo and first push (~10 min)**

One student creates an empty GitHub repo `student-hub`. Everyone else clones it (or the creator pushes first):

```bash
cd student-hub
git init
git add .
git commit -m "Add Student Hub frontend and Java API"
git branch -M main
git remote add origin https://github.com/your-org/student-hub.git
git push -u origin main
```

Use the root [`.gitignore`](./student-hub/.gitignore) — never commit `build/`, `.idea/`, `*.class`.

**6b — Split work by branch (~10 min)**

For a **3-person team**:

| Member | Branch | Owns |
|--------|--------|------|
| Student A | `feature/frontend-layout` | `index.html`, `style.css` |
| Student B | `feature/java-api` | `backend/` |
| Student C | `feature/integration` | `script.js`, testing |

```bash
git switch -c feature/frontend-layout
# edit files
git add frontend/
git commit -m "Add hub layout and responsive grid"
git push -u origin feature/frontend-layout
```

**Rules:** one branch per feature · **pull before push** · small, focused commits · never commit directly to `main` during the lab.

| I want to… | Command |
|------------|---------|
| New branch | `git switch -c branch-name` |
| Stage & commit | `git add path/` then `git commit -m "message"` |
| First push of branch | `git push -u origin branch-name` |
| Update local main | `git switch main && git pull` |

**6c — Pull requests and merge (~10 min)**

1. Push your branch → **Compare & pull request** on GitHub.
2. Teammate reviews → **Merge pull request**.
3. Everyone updates local `main`:

```bash
git switch main
git pull
```

**Suggested merge order:** `feature/frontend-layout` → `feature/java-api` → `feature/integration`.

Before opening a PR, sync with latest `main`:

```bash
git switch feature/integration
git merge origin/main
git push
```

**Merge conflict?** Git marks both versions in the file — edit, remove `<<<<`, `====`, `>>>>` lines, then:

```bash
git add conflicted-file
git commit -m "Resolve merge conflict"
git push
```

More detail: [git-github.md](./git-github.md).

**Full reference source:** [`student-hub/`](./student-hub/).

---

## 12. Test checklist & assignment hand-in

### Functional tests

- [ ] Page loads — form on left, feed on right (desktop)
- [ ] Mobile — columns stack below 820px
- [ ] Theme toggle switches light/dark
- [ ] **Server off:** post works → localStorage; badge = Offline mode
- [ ] **Server on:** badge = Java API connected; sample announcements from Java appear
- [ ] POST creates announcement — visible after refresh
- [ ] DELETE removes one item
- [ ] Empty form / whitespace-only submit ignored

### Git collaboration tests

- [ ] Repo has `main` + at least 2 feature branches
- [ ] At least 1 merged Pull Request
- [ ] `.gitignore` excludes `build/`, `.idea/`, `*.class`
- [ ] Commit messages are clear (not `final`, `update2`)

### Hand-in

1. GitHub repo URL: `https://github.com/your-username/student-hub`
2. README snippet (in PR or comment):

```markdown
## Run instructions
1. Backend: `cd backend && ./gradlew run`
2. Frontend: open `frontend/index.html` with Live Server
3. Demo: post announcement, delete, toggle theme
```

3. Screenshot of green **Java API connected** badge.

### Stretch goals

- [ ] Persist Java data to a `data/announcements.json` file
- [ ] Add `GET /api/health` endpoint returning `{"status":"ok"}`
- [ ] Filter announcements by author (front-end only)
- [ ] GitHub **Issues** for tasks; link issues in PR descriptions

---

## Quick reference

### Front-end

| Topic | Remember |
|-------|----------|
| Layout | CSS Grid for columns; Flexbox for header rows |
| API call | `fetch(url)` → `response.json()` |
| POST body | `JSON.stringify({ author, title, body })` |
| Forms | `event.preventDefault()` |
| Offline | `try/catch` around `fetch` + `localStorage` |

### Back-end

| Topic | Remember |
|-------|----------|
| Server | `com.sun.net.httpserver.HttpServer` |
| Port | Default `8080` — must match `API_BASE` in JS |
| CORS | Required for browser; not for curl |
| JSON | Manual `toJson()` for teaching; libraries later |

### Git collaboration

| Topic | Remember |
|-------|----------|
| Branch per feature | `git switch -c feature/name` |
| Share work | `push` → Pull Request → merge |
| Stay updated | `git pull` on `main` before new branch |
| Conflicts | Edit file, remove markers, `add`, `commit` |
| Never commit | Secrets, `build/`, `.idea/` |

### Debug checklist

1. **CORS error in Console?** Check Java CORS headers and OPTIONS handler.
2. **Status stuck on Offline?** Is `./gradlew run` running? Port match?
3. **`fetch` works in curl but not browser?** CORS — not a Java logic bug.
4. **Merge conflict?** Read both sides; test after resolving.
5. **`getElementById` is null?** Script before HTML, or wrong `id`.

---

## Related material

- HTML/CSS foundation: [html-css.md](./html-css.md)
- JavaScript + Git intro: [javascript-git-session.md](./javascript-git-session.md)
- Git branches & PRs: [git-github.md](./git-github.md)
- Java setup: [setup.md](./setup.md)
- Alternate front-end lab: [js-git-lab/](./js-git-lab/)
- Student card project: [html-css-js/](./html-css-js/)
- Reference implementation: [student-hub/](./student-hub/)
