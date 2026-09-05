# Web Fundamentals — HTML5, CSS3 & JavaScript

4-hour lab session. Same teaching flow as [Java Fundamentals](./fundamentals.md): short idea → example → exercise with solution.

Open files in a browser (Chrome / Edge / Firefox). VS Code + Live Server is enough; no compiler.

---

## Session plan (4 hours)

| Block | Time | Topics |
|-------|------|--------|
| **Hour 1** | 0:00–1:00 | HTML5 structure, text, links, lists, semantic tags, tables, forms |
| **Hour 2** | 1:00–2:00 | CSS3, selectors, box model, Flexbox, CSS Grid, CSS variables, [portal](./html-css-portal/) + [UI labs](./html-css/) |
| **Hour 3** | 2:00–3:00 | JS variables, types, operators, conditions, functions |
| **Hour 4** | 3:00–4:00 | DOM, events, JS mini project |

**Starter files** (create these in one folder):

```
web-lab/
  index.html
  style.css
  script.js
```

---

## Table of contents

1. [How a page is built](#1-how-a-page-is-built)
2. [HTML5 document structure](#2-html5-document-structure)
3. [Comments](#3-comments)
4. [Text and headings](#4-text-and-headings)
5. [Links, images, lists](#5-links-images-lists)
6. [Semantic HTML](#6-semantic-html)
7. [Tables](#7-tables)
8. [Forms](#8-forms)
9. [Adding CSS](#9-adding-css)
10. [Selectors](#10-selectors)
11. [Colors, fonts, units](#11-colors-fonts-units)
12. [Box model](#12-box-model)
13. [Display and Flexbox](#13-display-and-flexbox)
14. [CSS Grid](#14-css-grid)
15. [CSS variables](#15-css-variables)
16. [Mini project: Course portal (HTML5 + CSS3)](#16-mini-project-course-portal-html5--css3)
17. [JavaScript in the page](#17-javascript-in-the-page)
18. [Variables](#18-variables)
19. [Data types](#19-data-types)
20. [Operators](#20-operators)
21. [Conditional statements](#21-conditional-statements)
22. [Functions](#22-functions)
23. [DOM](#23-dom)
24. [Events](#24-events)
25. [Mini project: Student card](#25-mini-project-student-card)

---

# Hour 1 — HTML5 (0:00–1:00)

## 1. How a page is built

The browser builds a page in three layers:

| Layer | Language | Job |
|-------|----------|-----|
| Structure | **HTML** | What is on the page (headings, forms, images) |
| Look | **CSS** | How it looks (colors, spacing, layout) |
| Behavior | **JavaScript** | What happens on click, input, load |

HTML is **not** a programming language. It is a markup language: you wrap content in **tags**.

```html
<p>This is a paragraph.</p>
```

`<p>` opens, `</p>` closes. Most tags come in pairs. A few are **void** (no close tag): `<img>`, `<br>`, `<input>`.

### Exercise

**Task:** In one sentence each, say what HTML, CSS, and JavaScript are for.

**Solution:**

- **HTML** — Marks up the structure and meaning of content.
- **CSS** — Styles layout, color, and typography.
- **JavaScript** — Adds logic, events, and DOM updates.

---

## 2. HTML5 document structure

Every HTML5 file starts with a **doctype** and a skeleton. The browser uses this to parse the page correctly.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TCET Lab</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Hello, Web</h1>
    <script src="script.js"></script>
</body>
</html>
```

| Part | Meaning |
|------|---------|
| `<!DOCTYPE html>` | HTML5 document |
| `<html lang="en">` | Root; `lang` helps screen readers and search |
| `<head>` | Metadata — not shown as page content |
| `<meta charset="UTF-8">` | Character encoding (needed for ₹, emojis) |
| `viewport` | Makes the page scale on phones |
| `<title>` | Tab title |
| `<link>` | Attach an external CSS file |
| `<body>` | Visible content |
| `<script src="...">` | Attach JS (usually at end of `body` so HTML exists first) |

**`head` vs `body`**

- `head` = instructions for the browser (title, CSS, charset)
- `body` = what the user sees

### Exercise

**Task:** Create `index.html` with a valid HTML5 skeleton. Title: `Web Lab`. Inside `body`, print `Hello` and `TCET` on **two lines** using two headings or a line break.

**Solution:**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Lab</title>
</head>
<body>
    <h1>Hello</h1>
    <h2>TCET</h2>
</body>
</html>
```

(`<br>` also works: `Hello<br>TCET`.)

---

## 3. Comments

Comments are ignored by the browser. Use them as notes for humans.

```html
<!-- HTML comment -->

<!--
  Multi-line
  HTML comment
-->
```

```css
/* CSS comment */
```

```js
// JS single-line

/*
   JS multi-line
*/
```

HTML comments use `<!-- -->`. CSS and JS use `/* */` (JS also has `//`).

### Exercise

**Task:** Above your `<h1>`, add an HTML comment `Main heading`. The page output must not show that text.

**Solution:**

```html
<!-- Main heading -->
<h1>Hello</h1>
```

---

## 4. Text and headings

**Headings** `h1`–`h6` describe outline, not just size. Use **one `h1` per page**.

```html
<h1>College portal</h1>
<h2>Attendance</h2>
<h3>SE-A</h3>

<p>Regular paragraph text.</p>
<p>Line one.<br>Line two (forced break).</p>

<strong>Important</strong>   <!-- strong meaning, usually bold -->
<em>Emphasis</em>            <!-- emphasis, usually italic -->
<mark>Highlight</mark>
```

| Tag | Use |
|-----|-----|
| `h1`–`h6` | Section titles (outline) |
| `p` | Paragraph |
| `br` | Line break inside text |
| `strong` / `em` | Importance / emphasis |
| `span` | Inline hook for CSS (no meaning of its own) |
| `div` | Block hook for CSS/layout (no meaning of its own) |

Prefer semantic tags (`header`, `nav`, `p`) over wrapping everything in `div`.

### Exercise

**Task:** Write a page with `h1` = course name, `h2` = your name, and one `p` containing your roll number in `<strong>`.

**Solution:**

```html
<h1>Web Fundamentals</h1>
<h2>Riya Sharma</h2>
<p>Roll <strong>101</strong></p>
```

---

## 5. Links, images, lists

```html
<!-- Link: href is the destination -->
<a href="https://tcetmumbai.in" target="_blank">TCET website</a>
<a href="#attendance">Jump to Attendance</a>
<a href="mailto:office@college.edu">Email office</a>

<!-- Image: src + alt (required for accessibility) -->
<img src="logo.png" alt="College logo" width="120">

<!-- Unordered list -->
<ul>
    <li>HTML</li>
    <li>CSS</li>
    <li>JavaScript</li>
</ul>

<!-- Ordered list -->
<ol>
    <li>Open file</li>
    <li>Refresh browser</li>
</ol>
```

| Attribute | Meaning |
|-----------|---------|
| `href` | URL or `#id` on the same page |
| `target="_blank"` | Open in a new tab |
| `src` | Image path (relative or URL) |
| `alt` | Text if image fails / for screen readers |
| `id="attendance"` | Unique name; `#attendance` can jump here |

`ul` = bullets (no order). `ol` = numbered steps.

### Exercise

**Task:** Make a list of three subjects. The first item should be a link to `https://developer.mozilla.org`. Below the list, add an image with `alt="Lab photo"` (any `src` is fine).

**Solution:**

```html
<ul>
    <li><a href="https://developer.mozilla.org">HTML</a></li>
    <li>CSS</li>
    <li>JavaScript</li>
</ul>
<img src="lab.jpg" alt="Lab photo">
```

---

## 6. Semantic HTML

HTML5 tags describe **role**, not only boxes. Assistive tech and SEO use these names.

```html
<body>
    <header>
        <h1>SE Web Lab</h1>
        <nav>
            <a href="#home">Home</a>
            <a href="#notes">Notes</a>
        </nav>
    </header>

    <main>
        <section id="home">
            <h2>Home</h2>
            <article>
                <h3>Today's topic</h3>
                <p>HTML5 structure.</p>
            </article>
        </section>
    </main>

    <aside>Related: CSS box model</aside>
    <footer>© TCET</footer>
</body>
```

| Tag | Role |
|-----|------|
| `header` | Intro / branding for page or section |
| `nav` | Primary navigation links |
| `main` | Unique main content (one per page) |
| `section` | Thematic grouping |
| `article` | Self-contained piece (post, card) |
| `aside` | Side note, related links |
| `footer` | Credits, copyright |
| `figure` / `figcaption` | Image + caption |

A `div` is a generic box. Use it when **no semantic tag fits**.

### Exercise

**Task:** Wrap a heading and a paragraph in `<main>`. Add a `<footer>` with the text `TCET`.

**Solution:**

```html
<main>
    <h1>Web Lab</h1>
    <p>Hour 1: HTML5</p>
</main>
<footer>TCET</footer>
```

---

## 7. Tables

Use tables for **tabular data**, not for page layout.

```html
<table>
    <thead>
        <tr>
            <th>Roll</th>
            <th>Name</th>
            <th>CGPA</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>101</td>
            <td>Riya</td>
            <td>8.5</td>
        </tr>
        <tr>
            <td>102</td>
            <td>Aman</td>
            <td>9.1</td>
        </tr>
    </tbody>
</table>
```

| Tag | Meaning |
|-----|---------|
| `table` | Whole table |
| `thead` / `tbody` | Header vs data rows |
| `tr` | Row |
| `th` | Header cell (bold, scoped) |
| `td` | Data cell |
| `colspan` / `rowspan` | Merge cells |

Default tables have no borders — add CSS later (`border-collapse`, `padding`).

### Exercise

**Task:** A 2-column table: headers `Subject` and `Marks`, one data row `Java` and `95`.

**Solution:**

```html
<table>
    <tr>
        <th>Subject</th>
        <th>Marks</th>
    </tr>
    <tr>
        <td>Java</td>
        <td>95</td>
    </tr>
</table>
```

---

## 8. Forms

Forms collect input. `name` is what a server would receive; `id` pairs with `<label>`.

```html
<form action="#" method="get">
    <label for="name">Name</label>
    <input id="name" name="name" type="text" required>

    <label for="roll">Roll</label>
    <input id="roll" name="roll" type="number" min="1">

    <label for="dept">Department</label>
    <select id="dept" name="dept">
        <option value="it">IT</option>
        <option value="cs">CS</option>
    </select>

    <label>
        <input type="checkbox" name="hostel"> Hostel
    </label>

    <button type="submit">Save</button>
    <button type="reset">Clear</button>
</form>
```

| `type` | Typical use |
|--------|-------------|
| `text` | Name, short text |
| `email` | Email (browser format check) |
| `password` | Hidden characters |
| `number` | Numeric with `min` / `max` |
| `checkbox` / `radio` | Yes/no or one-of-many |
| `submit` | Send the form |

`label for="id"` makes the text clickable and accessible. Always connect labels.

### Exercise

**Task:** A form with a text field `studentName` (with label) and a Submit button.

**Solution:**

```html
<form>
    <label for="studentName">Name</label>
    <input id="studentName" name="studentName" type="text">
    <button type="submit">Submit</button>
</form>
```

---

# Hour 2 — CSS3 (1:00–2:00)

## 9. Adding CSS

Three places CSS can live. **External file** is the default for real projects.

```html
<!-- 1. External (best) -->
<link rel="stylesheet" href="style.css">

<!-- 2. Internal (one page only) -->
<style>
    h1 { color: navy; }
</style>

<!-- 3. Inline (avoid except tiny overrides) -->
<h1 style="color: navy;">Title</h1>
```

**Cascade (who wins):** inline > internal/external (later rules beat earlier) > browser default. **Specificity** also matters (IDs beat classes beat tags).

```css
h1 {
    color: navy;
    font-size: 28px;
}
```

Each rule: **selector** `{ property: value; }`

### Exercise

**Task:** In `style.css`, make every `h1` `color: teal`. Link that file from `index.html`.

**Solution:**

`index.html` in `<head>`:

```html
<link rel="stylesheet" href="style.css">
```

`style.css`:

```css
h1 {
    color: teal;
}
```

---

## 10. Selectors

A selector picks **which elements** get the styles.

```html
<p class="note">Class can be reused.</p>
<p id="alert">ID must be unique.</p>
<p>Plain paragraph.</p>
```

```css
p { color: #333; }           /* all <p> */
.note { font-style: italic; } /* class */
#alert { color: crimson; }    /* id */

header p { margin: 0; }       /* p inside header (descendant) */
h1, h2 { font-family: sans-serif; }  /* group */

a:hover { text-decoration: underline; }  /* pseudo-class */
```

| Selector | Matches | Specificity (simple) |
|----------|---------|----------------------|
| `p` | Tag | Low |
| `.note` | `class="note"` | Medium |
| `#alert` | `id="alert"` | High |
| `header p` | Nested | Tag + tag |
| `:hover` | Pointer over element | Extra |

One element can have **many classes**: `class="card featured"`. Only **one id**.

### Exercise

**Task:** Give a paragraph `class="highlight"`. CSS: yellow background for `.highlight` only.

**Solution:**

```html
<p class="highlight">Exam tomorrow</p>
```

```css
.highlight {
    background-color: yellow;
}
```

---

## 11. Colors, fonts, units

```css
body {
    color: #222;                    /* text */
    background-color: #f4f4f4;      /* page */
    font-family: system-ui, sans-serif;
    font-size: 16px;
    line-height: 1.5;
}

h1 {
    font-size: 2rem;                /* 2 × root font size */
    font-weight: 700;
}
```

**Color:** name (`navy`), hex (`#1a73e8`), `rgb(26, 115, 232)`, `rgba(..., 0.5)` for transparency.

**Units**

| Unit | Meaning |
|------|---------|
| `px` | Fixed pixels |
| `rem` | Relative to **root** (`html`) font size — prefer for type |
| `%` | Relative to parent |
| `em` | Relative to **this element’s** font size |
| `vw` / `vh` | 1% of viewport width / height |

`font-family` lists fallbacks: first available font wins.

### Exercise

**Task:** Set `body` font to `sans-serif`, size `18px`, text color `#111`.

**Solution:**

```css
body {
    font-family: sans-serif;
    font-size: 18px;
    color: #111;
}
```

---

## 12. Box model

Every element is a **box**. From inside out: **content → padding → border → margin**.

```
+---------------------------+
|         margin            |
|  +---------------------+  |
|  |      border         |  |
|  |  +---------------+  |  |
|  |  |    padding    |  |  |
|  |  |  +---------+  |  |  |
|  |  |  | content |  |  |  |
|  |  |  +---------+  |  |  |
|  |  +---------------+  |  |
|  +---------------------+  |
+---------------------------+
```

```css
.card {
    width: 300px;
    padding: 16px;              /* inside, around content */
    border: 2px solid #333;
    margin: 12px auto;          /* outside; auto centers a block */
    box-sizing: border-box;     /* width includes padding + border */
}
```

| Property | Effect |
|----------|--------|
| `content` | Text / children; `width` / `height` apply here (default `content-box`) |
| `padding` | Space inside the border |
| `border` | Line around padding |
| `margin` | Space **outside** the border (collapses between siblings) |
| `box-sizing: border-box` | `width` = content + padding + border — use this globally |

```css
*, *::before, *::after {
    box-sizing: border-box;
}
```

**Shorthand:** `padding: 10px 20px;` → top/bottom 10, left/right 20. Same pattern for `margin`.

### Exercise

**Task:** A `.box` with `width: 200px`, `padding: 20px`, `border: 1px solid black`, `margin: 10px`. Use `border-box`.

**Solution:**

```css
.box {
    box-sizing: border-box;
    width: 200px;
    padding: 20px;
    border: 1px solid black;
    margin: 10px;
}
```

---

## 13. Display and Flexbox

**`display`** changes how a box participates in layout.

| Value | Behavior |
|-------|----------|
| `block` | Full width, stacks vertically (`div`, `p`, `h1`) |
| `inline` | Sits in the text line (`span`, `a`) — width/height ignored |
| `inline-block` | Inline flow, but width/height work |
| `none` | Removed from layout (not visible) |
| `flex` | Children become a flex row/column |
| `grid` | Children become grid items (rows + columns) |

**Flexbox** (one-dimensional layout — row or column):

```css
.row {
    display: flex;
    gap: 12px;
    justify-content: space-between; /* main axis */
    align-items: center;            /* cross axis */
}

.col {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.grow {
    flex: 1;   /* take remaining space */
}
```

| Property | On | Meaning |
|----------|----|---------|
| `flex-direction` | parent | `row` (default) or `column` |
| `justify-content` | parent | Along main axis: `flex-start`, `center`, `space-between` |
| `align-items` | parent | Cross axis: `stretch`, `center` |
| `gap` | parent | Space between items |
| `flex` | child | Growth/shrink (e.g. `1`) |

Navbar: `display: flex` + `justify-content: space-between`. Card row: `display: flex` + `gap`.

### Exercise

**Task:** A `nav` with two links, displayed in a row with space between them.

**Solution:**

```html
<nav class="bar">
    <a href="#home">Home</a>
    <a href="#lab">Lab</a>
</nav>
```

```css
.bar {
    display: flex;
    justify-content: space-between;
}
```

---

## 14. CSS Grid

**Flexbox** lays out items in **one** direction (a row *or* a column). **CSS Grid** lays out in **two** dimensions — rows **and** columns at once. Use Grid for dashboards, card galleries, and page shells.

```css
.dashboard {
    display: grid;
    gap: 16px;
    grid-template-columns: repeat(3, 1fr);   /* three equal columns */
}

.hero {
    grid-column: span 2;   /* this child spans two columns */
}
```

| Property | On | Meaning |
|----------|----|---------|
| `display: grid` | parent | Children become grid items |
| `grid-template-columns` | parent | Column track sizes (`1fr`, `200px`, `repeat(3, 1fr)`) |
| `grid-template-rows` | parent | Row track sizes (optional) |
| `gap` | parent | Space between rows and columns |
| `grid-column` | child | Which columns to span (`span 2`, `1 / 3`) |
| `grid-row` | child | Which rows to span |

**`fr`** = one fraction of leftover space. `1fr 2fr` → second column is twice as wide.

**Responsive columns** without a media query:

```css
.cards {
    display: grid;
    gap: 16px;
    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
}
```

`auto-fit` adds as many columns as fit; `minmax(220px, 1fr)` keeps each column at least 220px wide.

**Flexbox vs Grid**

| Use | Tool |
|-----|------|
| Navbar, button row, vertical stack | Flexbox |
| Card grid, dashboard, page layout | Grid |
| Center one item | Either (`place-items: center` on Grid, or Flexbox `align` + `justify`) |

Live example: [`html-css/grid/`](./html-css/grid/) — campus dashboard with a hero panel, stats, and club cards.

### Exercise

**Task:** A `.gallery` with three equal columns and `gap: 12px`. The first item spans all three columns.

**Solution:**

```html
<div class="gallery">
    <figure class="featured">Wide shot</figure>
    <figure>Photo 2</figure>
    <figure>Photo 3</figure>
    <figure>Photo 4</figure>
</div>
```

```css
.gallery {
    display: grid;
    gap: 12px;
    grid-template-columns: repeat(3, 1fr);
}

.featured {
    grid-column: 1 / -1;   /* from first line to last line */
}
```

---

## 15. CSS variables

CSS **custom properties** store values you reuse. They cascade: children inherit unless overridden.

```css
:root {
    --brand: #0b5fff;
    --bg: #f7f8fa;
    --radius: 8px;
    --space: 16px;
}

body {
    background: var(--bg);
}

button {
    background: var(--brand);
    color: white;
    padding: var(--space);
    border-radius: var(--radius);
}

.card.dark {
    --bg: #1a1a1a;   /* override for this subtree */
    background: var(--bg);
}
```

| Piece | Meaning |
|-------|---------|
| `:root` | Document root — global tokens |
| `--name` | Variable name (must start with `--`) |
| `var(--name)` | Use the value |
| `var(--name, fallback)` | Fallback if undefined |

Change `--brand` once; every button updates. Later, JavaScript can set `element.style.setProperty('--brand', '#c00')` for themes.

### Exercise

**Task:** In `:root` define `--accent: teal`. Style `h1 { color: var(--accent); }`.

**Solution:**

```css
:root {
    --accent: teal;
}

h1 {
    color: var(--accent);
}
```

---

## 16. Mini project: Course portal (HTML5 + CSS3)

**No JavaScript.** Build a one-page college portal that uses everything from Hours 1–2.

Live files: [`html-css-portal/`](./html-css-portal/)

```
html-css-portal/
  index.html
  style.css
```

Open `index.html` in a browser (or Live Server). Study the markup, then restyle tokens in `:root`.

| HTML5 | CSS3 |
|-------|------|
| Skeleton, `header` / `nav` / `main` / `section` / `article` / `aside` / `footer` | External stylesheet, class + id selectors |
| Links, lists, table, form + labels | Box model + `border-box` |
| `figure` / `figcaption` | Flexbox (header, nav) + Grid (card rows) |
| | CSS variables (`--brand`, `--space`, `--radius`) |

**What to try in the lab**

1. Change `--brand` in `:root` and watch the header buttons, chips, and table header update.
2. Add a fourth course card in `.card-row`.
3. Add a timetable row for Saturday.
4. On a narrow window, confirm the `@media` rule stacks header, cards, and form.

### UI pattern labs (no JavaScript)

Open the hub: [`html-css/index.html`](./html-css/index.html)

These are the components students usually want on a real site. Each folder is its own project (`index.html` + `style.css`).

| Project | What you build | CSS trick |
|---------|----------------|-----------|
| [`pagination/`](./html-css/pagination/) | Campus club grid with pages 1–3 | Hidden radios + `label` + `:checked ~` |
| [`modal/`](./html-css/modal/) | Fest-pass overlay + email form | `:target` to show / `#` to close |
| [`carousel/`](./html-css/carousel/) | Campus highlights slider | Radios move a flex `track` with `translateX` |
| [`header-nav/`](./html-css/header-nav/) | Sticky product header | `:hover` underline, dropdown, checkbox hamburger |
| [`image-shimmer/`](./html-css/image-shimmer/) | Feed tiles that “load” | Facade overlay + shimmer keyframes, then fade-in image |
| [`grid/`](./html-css/grid/) | Campus dashboard bento board | `repeat(auto-fit, minmax())`, `grid-column: span 2` |
| [`portfolio/`](./html-css/portfolio/) | Full one-page landing | Grid + Flexbox across every section |

Needs network once (images from `picsum.photos`). Reload shimmer to see the skeleton again.

### Exercise

**Task:** In `html-css-portal/style.css`, set `--radius` to `4px`. Reload and confirm cards look sharper.

**Solution:** Change the token only — do not edit every `border-radius`:

```css
:root {
    --radius: 4px;
}
```

---

# Hour 3 — JavaScript basics (2:00–3:00)

## 17. JavaScript in the page

JS runs in the browser. Put the script **after** the HTML it needs, or use `defer` on `<script>` in `head`.

```html
<button id="go">Go</button>
<script src="script.js"></script>
```

```js
console.log("Hello, Web");   // DevTools → Console
```

Open DevTools: **Right-click → Inspect → Console**. `console.log` is the web equivalent of `System.out.println`.

A missing `)` or `'` stops the rest of the file. Check the Console for red errors.

### Exercise

**Task:** In `script.js`, log `Hello` and `TCET` as two separate logs.

**Solution:**

```js
console.log("Hello");
console.log("TCET");
```

---

## 18. Variables

A **variable** is a named box that holds a value.

```js
let name = "Aman";      // can reassign
const college = "TCET"; // cannot reassign
var old = 1;            // avoid — function-scoped, legacy
```

```js
let age = 18;
age = 19;               // ok

const maxMarks = 100;
// maxMarks = 50;       // TypeError
```

| Keyword | Reassign? | Scope | Use |
|---------|-----------|-------|-----|
| `let` | Yes | Block `{ }` | Values that change |
| `const` | No | Block | Defaults, DOM refs, functions |
| `var` | Yes | Function | Do not use in new code |

**Naming:** letters, digits, `_`, `$`; cannot start with a digit; camelCase (`studentName`).

### Exercise

**Task:** `const studentName = "Riya"`, `let rollNo = 101`. Log one line: `Roll 101: Riya`.

**Solution:**

```js
const studentName = "Riya";
let rollNo = 101;

console.log("Roll " + rollNo + ": " + studentName);
// or: console.log(`Roll ${rollNo}: ${studentName}`);
```

---

## 19. Data types

JS is **loosely typed**: the same `let` can hold a number, then a string (don’t do that on purpose).

```js
let marks = 95;             // number (int and float are both number)
let gpa = 8.5;
let title = "Web Lab";      // string
let passed = true;          // boolean
let empty = null;           // intentional empty
let notSet;                 // undefined
let scores = [95, 98, 100]; // array (object)
let student = { name: "Riya", roll: 101 };  // object
```

```js
typeof marks;     // "number"
typeof title;     // "string"
typeof passed;    // "boolean"
typeof scores;    // "object"  (arrays are objects)
typeof notSet;    // "undefined"
```

**Strings**

```js
const first = "Aman";
const last = "Kumar";
const full = first + " " + last;
const also = `${first} ${last}`;   // template literal

title.length;          // 7
title.toUpperCase();   // "WEB LAB"
title.includes("Lab"); // true
```

**Arrays**

```js
const marks = [95, 98, 100];
marks[0];          // 95
marks.length;      // 3
marks.push(88);    // add at end
```

### Exercise

**Task:** `const course = "Java";` — log length, and the character at index `2`.

**Solution:**

```js
const course = "Java";
console.log(course.length);   // 4
console.log(course[2]);       // v
```

---

## 20. Operators

```js
let a = 10;
let b = 3;

a + b;   // 13
a - b;   // 7
a * b;   // 30
a / b;   // 3.333...
a % b;   // 1  remainder
a ** b;  // 1000  power
```

```js
a += 5;   // a = a + 5
a++;      // increment by 1
```

**Comparison** (used in `if`):

```js
5 == "5";    // true  — converts types (avoid)
5 === "5";   // false — strict, same type and value
5 !== 3;     // true
age >= 18;
```

Always prefer `===` and `!==`.

**Logical**

```js
true && false;   // false  AND
true || false;   // true   OR
!true;           // false  NOT
```

**`+` with strings concatenates:** `"Roll " + 101` → `"Roll 101"`. `"5" + 3` → `"53"` (string). Use `Number("5")` or `+str` to convert.

### Exercise

**Task:** For `let n = 7`, log whether `n` is even (`n % 2 === 0`).

**Solution:**

```js
let n = 7;
console.log(n % 2 === 0);   // false
```

---

## 21. Conditional statements

**`if` / `else if` / `else`** — run a block only when a condition is true.

```js
const age = 17;

if (age >= 18) {
    console.log("Adult");
} else if (age >= 13) {
    console.log("Teen");
} else {
    console.log("Child");
}
```

**Truthy / falsy:** `0`, `""`, `null`, `undefined`, `NaN`, `false` are falsy. Everything else is truthy.

```js
const name = "";
if (name) {
    console.log("Has name");
} else {
    console.log("Missing name");
}
```

**`switch`** — compare one value to several cases.

```js
const grade = "A";

switch (grade) {
    case "A":
        console.log("Excellent");
        break;
    case "B":
        console.log("Good");
        break;
    default:
        console.log("Keep going");
}
```

Forget `break` and execution **falls through** to the next case.

**Ternary** (short if/else for a value):

```js
const status = age >= 18 ? "Adult" : "Minor";
```

### Exercise

**Task:** `const marks = 40`. If `marks >= 40` log `Pass`, else log `Fail`.

**Solution:**

```js
const marks = 40;

if (marks >= 40) {
    console.log("Pass");
} else {
    console.log("Fail");
}
```

---

## 22. Functions

A **function** is a reusable block. It can take **parameters** and **return** a value.

```js
function printHello() {
    console.log("Hello");
}

printHello();
printHello();
```

**Parameters and return**

```js
function add(a, b) {
    return a + b;
}

const result = add(3, 5);
console.log(result);   // 8
```

**Arrow function** (common in event handlers):

```js
const add = (a, b) => a + b;
const greet = (name) => {
    console.log("Hi " + name);
};
```

| Kind | Returns |
|------|---------|
| No `return` / only `console.log` | `undefined` |
| `return value` | That value to the caller |

Call a function with `name()`. Forgetting `()` does not run it — it just refers to the function.

### Exercise

**Task:** Write `isEven(n)` that returns `true` if `n` is even. Log `isEven(4)` and `isEven(7)`.

**Solution:**

```js
function isEven(n) {
    return n % 2 === 0;
}

console.log(isEven(4));   // true
console.log(isEven(7));   // false
```

---

# Hour 4 — DOM, events, JS mini project (3:00–4:00)

## 23. DOM

The **Document Object Model** is the tree of HTML nodes the browser builds. JavaScript reads and changes that tree.

```
document
  html
    head
    body
      h1
      p#status
      button
```

```html
<p id="status">Ready</p>
<ul id="list"></ul>
```

```js
const status = document.getElementById("status");
status.textContent = "Loaded";          // text only (safe)
status.innerHTML = "<strong>Loaded</strong>"; // parses HTML (careful)

status.classList.add("ok");
status.classList.remove("ok");
status.classList.toggle("ok");

const p = document.querySelector("p");       // first p
const items = document.querySelectorAll("li"); // all li (NodeList)

const li = document.createElement("li");
li.textContent = "HTML";
document.getElementById("list").appendChild(li);
```

| API | Use |
|-----|-----|
| `getElementById("id")` | One element by id |
| `querySelector(".card")` | First match (CSS selector) |
| `querySelectorAll("li")` | All matches |
| `textContent` | Get/set plain text |
| `classList` | Add/remove/toggle classes |
| `createElement` + `appendChild` | Build new nodes |

`document` is the whole page. `null` means “not found” — usually a typo in the id, or the script ran **before** the HTML existed.

### Exercise

**Task:** An `h1` with `id="title"`. In JS, set its text to `Web Lab`.

**Solution:**

```html
<h1 id="title">Placeholder</h1>
<script src="script.js"></script>
```

```js
document.getElementById("title").textContent = "Web Lab";
```

---

## 24. Events

An **event** is something that happened: click, key, submit, load. You **listen** and run a function.

```html
<button id="go">Go</button>
<input id="name" type="text">
<p id="out"></p>
```

```js
const go = document.getElementById("go");
const nameInput = document.getElementById("name");
const out = document.getElementById("out");

go.addEventListener("click", function () {
    out.textContent = "Clicked";
});

nameInput.addEventListener("input", function (event) {
    out.textContent = event.target.value;
});
```

**Form submit** — prevent full page reload:

```js
form.addEventListener("submit", function (event) {
    event.preventDefault();
    console.log("No reload");
});
```

| Event | Typical on |
|-------|------------|
| `click` | button, anything |
| `input` | text fields (every keystroke) |
| `change` | select, checkbox |
| `submit` | `form` |
| `keydown` | keyboard |

`event.target` is the element that fired. `event.preventDefault()` stops the browser’s default (link navigation, form reload).

**Arrow + toggle class** (theme):

```js
document.getElementById("theme").addEventListener("click", () => {
    document.body.classList.toggle("dark");
});
```

### Exercise

**Task:** A button `id="btn"`. On click, log `Clicked`.

**Solution:**

```html
<button id="btn">Press</button>
<script src="script.js"></script>
```

```js
document.getElementById("btn").addEventListener("click", function () {
    console.log("Clicked");
});
```

---

## 25. Mini project: Student card

Build a small page: profile card, pass/fail from marks, dark-theme toggle. Ties HTML structure, CSS box model + variables, JS conditions, functions, DOM, and events.

Live files: [`html-css-js/`](./html-css-js/)

```
html-css-js/
  index.html
  style.css
  script.js
```

Open `index.html` in a browser. Fill the form and click **Update card** — the profile updates without a page reload. Use **Toggle theme** to switch light / dark via a `body.dark` class.

### `index.html`

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student card</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header class="bar">
        <h1>SE student card</h1>
        <button type="button" id="themeBtn">Toggle theme</button>
    </header>

    <main>
        <article class="card">
            <h2 id="displayName">—</h2>
            <p>Roll <span id="displayRoll">—</span></p>
            <p id="result"></p>
        </article>

        <form id="cardForm" class="card">
            <label for="name">Name</label>
            <input id="name" name="name" type="text" required>

            <label for="roll">Roll</label>
            <input id="roll" name="roll" type="number" required min="1">

            <label for="marks">Marks (out of 100)</label>
            <input id="marks" name="marks" type="number" required min="0" max="100">

            <button type="submit">Update card</button>
        </form>
    </main>

    <script src="script.js"></script>
</body>
</html>
```

### `style.css`

```css
:root {
    --bg: #f4f6f8;
    --text: #1a1a1a;
    --card: #ffffff;
    --brand: #0b5fff;
    --space: 16px;
    --radius: 12px;
}

body.dark {
    --bg: #121212;
    --text: #f0f0f0;
    --card: #1e1e1e;
}

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: system-ui, sans-serif;
    background: var(--bg);
    color: var(--text);
}

.bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: var(--space);
}

.card {
    width: min(400px, 90%);
    margin: var(--space) auto;
    padding: var(--space);
    background: var(--card);
    border: 1px solid #ccc;
    border-radius: var(--radius);
}

label, input, button {
    display: block;
    width: 100%;
    margin-bottom: 8px;
}

button {
    background: var(--brand);
    color: white;
    border: none;
    padding: 10px;
    border-radius: 8px;
    cursor: pointer;
}

.pass { color: teal; }
.fail { color: crimson; }
```

### `script.js`

```js
function isPass(marks) {
    return marks >= 40;
}

const form = document.getElementById("cardForm");
const themeBtn = document.getElementById("themeBtn");

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const roll = document.getElementById("roll").value;
    const marks = Number(document.getElementById("marks").value);

    document.getElementById("displayName").textContent = name;
    document.getElementById("displayRoll").textContent = roll;

    const result = document.getElementById("result");
    result.classList.remove("pass", "fail");

    if (isPass(marks)) {
        result.textContent = "Pass (" + marks + ")";
        result.classList.add("pass");
    } else {
        result.textContent = "Fail (" + marks + ")";
        result.classList.add("fail");
    }
});

themeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});
```

**What you practiced**

| Piece | Where |
|-------|--------|
| HTML5 skeleton, form, semantic `header` / `main` / `article` | `index.html` |
| Box model, Flexbox, Grid, CSS variables | `style.css` |
| Variables, `Number()`, `if`, function `isPass` | `script.js` |
| DOM + `submit` / `click` + `preventDefault` | `script.js` |

### Stretch (if time remains)

- Disable Submit while Name is empty (`input` event + `disabled` on the button).
- Add a `select` for department and show it on the card.
- Store theme: `localStorage.setItem("theme", "dark")` and restore on load.

---

## Quick reference

**HTML:** `<!DOCTYPE html>` → `html` → `head` (meta, title, CSS) + `body` (content, then JS).

**CSS box:** content + padding + border + margin; prefer `box-sizing: border-box`.

**Flexbox:** one row or column — `display: flex`, `gap`, `justify-content`, `align-items`.

**Grid:** rows and columns — `display: grid`, `grid-template-columns`, `gap`, `grid-column: span N`.

**CSS variables:** `:root { --brand: #0b5fff; }` then `var(--brand)`.

**JS:** `let` / `const`, `===`, `if` / `else`, `function`, `return`.

**DOM:** `getElementById` / `querySelector`, `textContent`, `classList`.

**Events:** `addEventListener("click" | "submit" | "input", handler)`; `event.preventDefault()` on forms.
