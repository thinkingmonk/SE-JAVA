# JavaScript & GitHub — 4-Hour Lab Session

One lab covering **JavaScript** (variables, functions, DOM, events) and **Git/GitHub** (repo, commit, push, pull). Same flow as [Java Fundamentals](./fundamentals.md): short idea → example → exercise with solution.

**Prerequisites:** Basic HTML (skeleton, `id`, forms). Skim [html-css.md](./html-css.md) sections 1–8 if needed.

**Tools:** VS Code + [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer), Chrome DevTools (Console), [Git](https://git-scm.com/downloads).

**Reference project:** [js-git-lab/](./js-git-lab/) — Task Board (built in Hour 4).

---

## Session plan (4 hours)

| Block | Time | Topics |
|-------|------|--------|
| **Hour 1** | 0:00–1:00 | JS in the browser, `console.log`, **variables**, types, arrays |
| **Hour 2** | 1:00–2:00 | Operators, conditionals, **functions** |
| **Hour 3** | 2:00–3:00 | **DOM** (select, update, create) + **events** |
| **Hour 4** | 3:00–4:00 | Mini project — **Task Board** → **Git** init, commit, push, pull |

**Starter folder** (create at the start of Hour 1):

```
js-git-lab/
  index.html
  style.css
  script.js
```

Minimal `index.html`:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JS & Git Lab</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1 id="title">JS & Git Lab</h1>
    <script src="script.js"></script>
</body>
</html>
```

Put `<script src="script.js">` **at the end of `body`** so HTML exists before JS runs.

---

## Table of contents

### JavaScript
1. [JavaScript in the browser](#1-javascript-in-the-browser)
2. [Variables](#2-variables)
3. [Data types & arrays](#3-data-types--arrays)
4. [Operators & conditionals](#4-operators--conditionals)
5. [Functions](#5-functions)
6. [The DOM](#6-the-dom)
7. [Events](#7-events)

### Mini project & Git
8. [Mini project: Task Board](#8-mini-project-task-board)
9. [Git vs GitHub](#9-git-vs-github)
10. [Create a repo & first commit](#10-create-a-repo--first-commit)
11. [Push, pull & daily workflow](#11-push-pull--daily-workflow)

---

# Hour 1 — Variables & types (0:00–1:00)

## 1. JavaScript in the browser

| Layer | Language | Job |
|-------|----------|-----|
| Structure | HTML | What is on the page |
| Look | CSS | How it looks |
| **Behavior** | **JavaScript** | Clicks, forms, live updates |

```html
<script src="script.js"></script>   <!-- preferred: external file -->
```

**`console.log`** — print to DevTools Console (F12 → Console). Like `System.out.println` in Java.

```js
console.log("Hello, TCET");
console.log(42);
console.log("Roll", 101, "Riya");
```

### Exercise

**Task:** Log `Hello` and `TCET` on two separate lines.

**Solution:**

```js
console.log("Hello");
console.log("TCET");
```

---

## 2. Variables

A **variable** is a named container for a value.

```js
const college = "TCET";    // cannot reassign — use by default
let score = 90;            // can reassign
score = 95;                // ok
```

| Keyword | Reassign? | When to use |
|---------|-----------|-------------|
| `const` | No | Default — constants, DOM refs, functions |
| `let` | Yes | Counters, values that change |
| `var` | Yes | **Avoid** — legacy |

**Naming:** camelCase — `studentName`, `taskCount`.

**Template literals** (backticks):

```js
const name = "Riya";
const roll = 101;
console.log(`Roll ${roll}: ${name}`);   // Roll 101: Riya
```

### Exercise

**Task:** `const studentName = "Riya"`, `let rollNo = 101`. Log `Roll 101: Riya`.

**Solution:**

```js
const studentName = "Riya";
let rollNo = 101;
console.log(`Roll ${rollNo}: ${studentName}`);
```

---

## 3. Data types & arrays

JS is **dynamically typed** — no type declaration.

```js
let marks = 95;              // number
let title = "Web Lab";       // string
let passed = true;           // boolean
let tasks = ["HTML", "CSS"]; // array — ordered list
```

**Arrays** — you will use these in the Task Board project:

```js
const tasks = ["Study JS", "Push to GitHub"];

tasks[0];           // "Study JS"
tasks.length;       // 2
tasks.push("Lab");  // add at end
tasks.pop();        // remove from end
```

**Loop over an array:**

```js
for (const task of tasks) {
    console.log(task);
}
```

**Objects** — labeled properties:

```js
const student = { name: "Riya", roll: 101 };
student.name;   // "Riya"
```

**Convert types** (important for form input later):

```js
Number("42");    // 42
String(42);      // "42"
```

### Exercise

**Task:** `const nums = [10, 20, 30]`. Use a loop to log the sum (60).

**Solution:**

```js
const nums = [10, 20, 30];
let sum = 0;
for (const n of nums) {
    sum += n;
}
console.log(sum);
```

---

# Hour 2 — Functions (1:00–2:00)

## 4. Operators & conditionals

**Comparison** — use strict equality:

```js
5 === "5";    // false — same type AND value
5 == "5";     // true  — avoid (coerces types)
marks >= 40;
```

**Logical:** `&&` (and), `||` (or), `!` (not).

**`if` / `else`:**

```js
const marks = 72;

if (marks >= 90) {
    console.log("A");
} else if (marks >= 40) {
    console.log("Pass");
} else {
    console.log("Fail");
}
```

**Ternary** — short if/else:

```js
const status = marks >= 40 ? "Pass" : "Fail";
```

### Exercise

**Task:** `const marks = 39`. Log `Pass` or `Fail` (threshold 40).

**Solution:**

```js
const marks = 39;
if (marks >= 40) {
    console.log("Pass");
} else {
    console.log("Fail");
}
```

---

## 5. Functions

A **function** is a reusable block of code with a name.

**Declaration:**

```js
function isPass(marks) {
    return marks >= 40;
}

console.log(isPass(85));   // true
console.log(isPass(30));   // false
```

**Multiple parameters:**

```js
function greet(name, roll) {
    return `Hello, ${name} (roll ${roll})`;
}
```

**Arrow function** (short form):

```js
const double = (n) => n * 2;
const isEmpty = (text) => text.trim().length === 0;
```

**Why functions matter in the lab**

- `isPass(marks)` — business rule in one place
- `renderTasks(tasks)` — draw the list on screen (Hour 4)
- `createTaskItem(text)` — build one `<li>` element (Hour 4)

### Exercise

**Task:** Write `function getInitials(name)` — `"Priya Sharma"` → `"PS"`, `"Aman"` → `"A"`, `""` → `"—"`.

**Solution:**

```js
function getInitials(name) {
    const parts = name.trim().split(/\s+/).filter(Boolean);
    if (parts.length === 0) return "—";
    if (parts.length === 1) return parts[0].charAt(0).toUpperCase();
    return (parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase();
}
```

---

# Hour 3 — DOM & events (2:00–3:00)

## 6. The DOM

When the browser loads HTML, it builds a **DOM tree** — every tag is a **node** JavaScript can read and change.

```
document
  └── html
        └── body
              ├── h1#title
              └── ul#taskList
```

**Select elements:**

```js
document.getElementById("title");           // one id
document.querySelector(".btn");             // first match (CSS selector)
document.querySelectorAll("li");            // all matches (NodeList)
```

**Read & update content:**

```js
const title = document.getElementById("title");
title.textContent = "Task Board";           // safe text (no HTML)
title.innerHTML = "<em>Task Board</em>";    // parses HTML — use carefully
```

**Classes** (pair with CSS):

```js
element.classList.add("done");
element.classList.remove("done");
element.classList.toggle("dark");
element.classList.contains("done");   // true / false
```

**Create & append elements:**

```js
const li = document.createElement("li");
li.textContent = "Learn Git";
li.classList.add("task");

document.getElementById("taskList").appendChild(li);
```

**Remove:**

```js
li.remove();
```

### Exercise

**Task:** HTML has `<p id="msg">Hello</p>`. In JS, change text to `TCET` and add class `highlight`.

**Solution:**

```html
<p id="msg">Hello</p>
```

```js
const msg = document.getElementById("msg");
msg.textContent = "TCET";
msg.classList.add("highlight");
```

```css
.highlight { color: #2563eb; font-weight: bold; }
```

---

## 7. Events

An **event** is something that happens — click, key press, form submit. You **listen** with `addEventListener`.

```js
const btn = document.getElementById("themeBtn");

btn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});
```

**Common events**

| Event | When |
|-------|------|
| `click` | Mouse click or tap |
| `input` | Text field value changed |
| `submit` | Form submitted (Enter or button) |
| `keydown` | Key pressed |

**Forms — stop page reload:**

```html
<form id="taskForm">
    <input id="taskInput" type="text" required>
    <button type="submit">Add</button>
</form>
```

```js
const form = document.getElementById("taskForm");

form.addEventListener("submit", function (event) {
    event.preventDefault();   // do not reload the page

    const input = document.getElementById("taskInput");
    const text = input.value.trim();
    console.log("New task:", text);
    input.value = "";         // clear field
});
```

**Event on dynamically created button** (used in Task Board):

```js
deleteBtn.addEventListener("click", function () {
    li.remove();
});
```

### Exercise

**Task:** Button `id="btn"`. On click, log `Clicked` to the Console.

**Solution:**

```js
document.getElementById("btn").addEventListener("click", function () {
    console.log("Clicked");
});
```

---

# Hour 4 — Mini project & GitHub (3:00–4:00)

Split Hour 4: **~35 min** build Task Board → **~25 min** Git workflow.

---

## 8. Mini project: Task Board

Build a page where users can **add tasks**, **mark done**, and **delete** — no page reload.

Live reference: [`js-git-lab/`](./js-git-lab/) — open `index.html` in a browser.

### What you will build

```
┌─────────────────────────────────────┐
│  TCET Task Board          [Theme]   │
├─────────────────────────────────────┤
│  [ Enter a task...        ] [Add]   │
│  3 tasks · 1 done                   │
├─────────────────────────────────────┤
│  ○ Study JavaScript variables       │
│  ✓ Push project to GitHub      [×]  │
│  ○ Review DOM selectors             │
└─────────────────────────────────────┘
```

### Concepts map

| Topic | Where in project |
|-------|------------------|
| **Variables** | `let tasks = []` — array of `{ id, text, done }` |
| **Functions** | `renderTasks`, `updateStats`, `createTaskElement` |
| **DOM** | `createElement`, `appendChild`, `textContent`, `classList` |
| **Events** | `submit` on form, `click` on done/delete buttons |

### Step 1 — HTML skeleton (~5 min)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Board</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header class="header">
        <h1>TCET Task Board</h1>
        <button type="button" id="themeBtn" class="btn btn--ghost">Theme</button>
    </header>

    <main class="container">
        <form id="taskForm" class="task-form">
            <input id="taskInput" type="text" placeholder="Enter a task…" maxlength="80" required>
            <button type="submit" class="btn btn--primary">Add</button>
        </form>

        <p id="stats" class="stats">0 tasks</p>

        <ul id="taskList" class="task-list" aria-live="polite"></ul>
    </main>

    <script src="script.js"></script>
</body>
</html>
```

### Step 2 — Variables & functions (~10 min)

```js
let tasks = [];   // { id: number, text: string, done: boolean }
let nextId = 1;

function updateStats() {
    const total = tasks.length;
    const done = tasks.filter(function (t) { return t.done; }).length;
    const stats = document.getElementById("stats");

    if (total === 0) {
        stats.textContent = "No tasks yet";
    } else {
        stats.textContent = `${total} task${total === 1 ? "" : "s"} · ${done} done`;
    }
}

function renderTasks() {
    const list = document.getElementById("taskList");
    list.innerHTML = "";   // clear old items

    for (const task of tasks) {
        list.appendChild(createTaskElement(task));
    }

    updateStats();
}
```

### Step 3 — Create one task row (~10 min)

```js
function createTaskElement(task) {
    const li = document.createElement("li");
    li.className = "task" + (task.done ? " task--done" : "");

    const toggleBtn = document.createElement("button");
    toggleBtn.type = "button";
    toggleBtn.className = "task__toggle";
    toggleBtn.textContent = task.done ? "✓" : "○";
    toggleBtn.setAttribute("aria-label", "Toggle done");

    const span = document.createElement("span");
    span.className = "task__text";
    span.textContent = task.text;

    const deleteBtn = document.createElement("button");
    deleteBtn.type = "button";
    deleteBtn.className = "task__delete";
    deleteBtn.textContent = "×";
    deleteBtn.setAttribute("aria-label", "Delete task");

    toggleBtn.addEventListener("click", function () {
        task.done = !task.done;
        renderTasks();
    });

    deleteBtn.addEventListener("click", function () {
        tasks = tasks.filter(function (t) { return t.id !== task.id; });
        renderTasks();
    });

    li.appendChild(toggleBtn);
    li.appendChild(span);
    li.appendChild(deleteBtn);
    return li;
}
```

### Step 4 — Form submit & theme (~10 min)

```js
const form = document.getElementById("taskForm");
const themeBtn = document.getElementById("themeBtn");

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const input = document.getElementById("taskInput");
    const text = input.value.trim();
    if (text.length === 0) return;

    tasks.push({ id: nextId, text: text, done: false });
    nextId += 1;
    input.value = "";
    renderTasks();
});

themeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});

renderTasks();   // initial empty state
```

### Test checklist

- [ ] Add three tasks — list updates, no reload
- [ ] Toggle done — checkmark and strikethrough (CSS)
- [ ] Delete one task — count updates
- [ ] Submit empty / whitespace-only — ignored
- [ ] Theme button toggles dark mode

### Stretch (if time remains)

- Disable **Add** when input is empty (`input` event + `button.disabled`)
- Show `"Nothing here — add a task above"` when list is empty
- `localStorage` to save tasks between refreshes

Full styled source: [`js-git-lab/`](./js-git-lab/).

---

## 9. Git vs GitHub

| | **Git** | **GitHub** |
|---|---------|------------|
| What | Tool on your laptop | Website in the cloud |
| Job | Tracks file changes (version control) | Stores repos + collaboration |
| Offline? | Yes | Needs internet to push/pull |

Think of Git as **save points in a game**. GitHub is where you **upload** those saves.

| Term | Meaning |
|------|---------|
| **Repository (repo)** | Project folder + full history |
| **Commit** | One saved snapshot with a message |
| **Push** | Send local commits to GitHub |
| **Pull** | Download latest commits from GitHub |
| **Clone** | Copy a remote repo to your machine |

Install Git: [git-scm.com/downloads](https://git-scm.com/downloads)

```bash
git --version
```

**One-time setup** (use your real name + college email):

```bash
git config --global user.name "Your Name"
git config --global user.email "you@college.edu"
```

Create a GitHub account at [github.com](https://github.com). For HTTPS push, use a **Personal Access Token** (Settings → Developer settings → Personal access tokens).

---

## 10. Create a repo & first commit

### On GitHub (browser)

1. **New repository** → name: `task-board` (or `js-git-lab`)
2. Public or Private (teacher's choice)
3. **Do not** add README if you already have local files (avoids extra merge step)
4. Copy the HTTPS URL: `https://github.com/your-username/task-board.git`

### On your laptop (terminal)

```bash
cd path/to/js-git-lab

git init
git status
```

Create `.gitignore` (do not track OS junk):

```gitignore
.DS_Store
Thumbs.db
```

**Stage and commit:**

```bash
git add .
git status          # files should be green (staged)
git commit -m "Add Task Board with DOM and events"
```

**Good commit messages** — present tense, specific:

- ✅ `Add Task Board with DOM and events`
- ❌ `update`, `final`, `done done2`

**Connect remote and push:**

```bash
git branch -M main
git remote add origin https://github.com/your-username/task-board.git
git push -u origin main
```

Refresh GitHub — you should see `index.html`, `style.css`, `script.js`.

### Exercise

**Task:** After the first push, edit `index.html` title to include your name. Commit with message `Update page title` and push again.

**Solution:**

```bash
# edit index.html <title>Your Name — Task Board</title>
git add index.html
git commit -m "Update page title"
git push
```

---

## 11. Push, pull & daily workflow

Every time you finish a small chunk of work:

```
edit files → git status → git add → git commit → git push
```

Before starting work on a **shared** repo, always **pull first**:

```bash
git pull
```

### Daily loop (cheat sheet)

| I want to… | Command |
|------------|---------|
| See what changed | `git status` |
| Stage one file | `git add index.html` |
| Stage everything | `git add .` |
| Save a snapshot | `git commit -m "message"` |
| Upload to GitHub | `git push` |
| Download updates | `git pull` |
| View history | `git log --oneline` |
| Copy someone else's repo | `git clone <url>` |

### Example session (after lab)

```bash
cd js-git-lab
git pull                              # if working with others
# edit script.js — add stretch feature
git status
git add script.js
git commit -m "Disable Add button when input is empty"
git push
```

### Fix common mistakes

**"Please tell me who you are"** — run `git config` from [§10](#10-create-a-repo--first-commit).

**"rejected — non-fast-forward"** — someone else pushed first:

```bash
git pull
git push
```

**Undo last commit (not pushed yet):**

```bash
git reset --soft HEAD~1    # keeps file changes
```

**Discard changes in one file (careful):**

```bash
git restore script.js
```

### Assignment hand-in flow

1. Build Task Board locally (Hour 4)
2. Create GitHub repo → `git init` → `add` → `commit` → `push`
3. Submit repo URL to teacher: `https://github.com/your-username/task-board`

Optional: work on a branch `git switch -c your-name-lab` before pushing — see [git-github.md](./git-github.md) for branches and pull requests.

---

## Quick reference

### JavaScript

| Topic | Remember |
|-------|----------|
| Variables | `const` by default; `let` when reassigning |
| Equality | `===` and `!==`, not `==` |
| Functions | `function name() { return x; }` |
| DOM select | `getElementById`, `querySelector` |
| DOM update | `textContent`, `classList.add/remove/toggle` |
| DOM create | `createElement` + `appendChild` |
| Events | `addEventListener("click" \| "submit", fn)` |
| Forms | `event.preventDefault()`; `input.value.trim()` |

### Git

| Topic | Remember |
|-------|----------|
| Local tool | Git — commits on your machine |
| Remote host | GitHub — backup + sharing |
| Loop | `pull` → edit → `status` → `add` → `commit` → `push` |
| Never commit | Passwords, `.env`, API keys |

### Debug checklist

1. Red error in Console? Read the line number.
2. `null` from `getElementById`? Wrong `id` or script before HTML.
3. Form reloads page? Missing `event.preventDefault()`.
4. `git push` fails? Pull first, or check remote URL and token.

**Related material**

- Full JS deep dive: [javascript-dom.md](./javascript-dom.md)
- Full Git deep dive: [git-github.md](./git-github.md)
- HTML/CSS foundation: [html-css.md](./html-css.md)
- Student card (alternate JS project): [html-css-js/](./html-css-js/)
