# Git & GitHub — Crash Course for Students

~90 minutes. Same flow as other labs: short idea → command → try it yourself.

**Course repo:** [thinkingmonk/SE-JAVA](https://github.com/thinkingmonk/SE-JAVA/tree/master)  
Local Java setup: [setup.md](./setup.md)

Install Git: [git-scm.com/downloads](https://git-scm.com/downloads) — then verify:

```bash
git --version
```

---

## Table of contents

1. [Git vs GitHub](#1-git-vs-github)
2. [One-time setup](#2-one-time-setup)
3. [Clone a repo](#3-clone-a-repo)
4. [Daily workflow](#4-daily-workflow)
5. [Branches (basics)](#5-branches-basics)
6. [`.gitignore`](#6-gitignore)
7. [Fix common mistakes](#7-fix-common-mistakes)
8. [Cheat sheet](#8-cheat-sheet)

---

## 1. Git vs GitHub

| | **Git** | **GitHub** |
|---|---------|------------|
| What | Tool on your laptop | Website in the cloud |
| Job | Tracks file changes (version control) | Stores repos + collaboration (PRs, issues) |
| Offline? | Yes | Needs internet to push/pull |

Think of Git as **save points in a game**. GitHub is where you **upload** those saves so your team can see them.

**Key words**

| Term | Meaning |
|------|---------|
| **Repository (repo)** | Project folder + full history |
| **Commit** | One saved snapshot with a message |
| **Push** | Send local commits to GitHub |
| **Pull** | Download latest commits from GitHub |
| **Clone** | Copy a remote repo to your machine |
| **Branch** | Parallel line of work (e.g. `feature-login`) |

---

## 2. One-time setup

Tell Git who you are (use your real name + college email):

```bash
git config --global user.name "Your Name"
git config --global user.email "you@college.edu"
```

Check:

```bash
git config --global --list
```

**Sign in to GitHub**

1. Create account at [github.com](https://github.com)
2. For HTTPS push/pull, use a **Personal Access Token** (not your password):  
   GitHub → **Settings → Developer settings → Personal access tokens → Generate**

---

## 3. Clone a repo

Get the course code on your laptop:

```bash
cd ~/Documents
git clone https://github.com/thinkingmonk/SE-JAVA.git
cd SE-JAVA
```

You now have a full copy with history. Open the folder in VS Code or IntelliJ.

### Exercise

**Task:** Clone the repo (or `cd` into it if already cloned). Run `git status`. What does it say?

**Solution:** `On branch master` (or `main`) and `nothing to commit, working tree clean` — you are up to date and have no unsaved changes.

---

## 4. Daily workflow

Every time you finish a small chunk of work:

```
edit files → git status → git add → git commit → git push
```

### Step 1 — See what changed

```bash
git status
```

- **red** = changed but not staged  
- **green** = staged, ready to commit

### Step 2 — Stage files

```bash
git add Main.java              # one file
git add .                      # everything in this folder (careful)
```

### Step 3 — Commit (save point)

```bash
git commit -m "Add Student class with display method"
```

Write messages in **present tense**, short and specific. Bad: `fix`. Good: `Fix divide-by-zero in calculator`.

### Step 4 — Push to GitHub

```bash
git push
```

First push on a new branch may need:

```bash
git push -u origin your-branch-name
```

### Step 5 — Before you start work (pull first)

```bash
git pull
```

Always **pull before push** if others share the repo — avoids merge conflicts.

### Example session

```bash
cd SE-JAVA/playground
# edit Main.java in your editor
git status
git add src/main/java/org/java/Main.java
git commit -m "Print student roll number in main"
git pull
git push
```

### Exercise

**Task:** Create a file `notes.txt` with one line, commit it with message `Add lab notes`, then check `git log --oneline -3`.

**Solution:**

```bash
echo "Week 1: variables and loops" > notes.txt
git add notes.txt
git commit -m "Add lab notes"
git log --oneline -3
```

You should see your commit at the top with hash + message.

---

## 5. Branches (basics)

Branches let you work on a feature without breaking `main`.

```bash
git branch                    # list branches (* = current)
git switch -c lab-week2       # create + switch to new branch
# ... edit, add, commit ...
git push -u origin lab-week2  # first push of this branch
```

When done (often via **Pull Request** on GitHub), the branch is merged into `main`.

| Command | What it does |
|---------|----------------|
| `git switch main` | Go back to main branch |
| `git pull` | Update main after merge |
| `git branch -d lab-week2` | Delete local branch (after merge) |

**Rule:** Do lab assignments on your own branch, not directly on `main`, unless your teacher says otherwise.

---

## 6. `.gitignore`

A file that tells Git **what not to track**.

Example `.gitignore` for Java + IDE:

```gitignore
# Compiled
*.class
target/
out/
build/

# IDE
.idea/
*.iml
.vscode/

# OS junk
.DS_Store
Thumbs.db
```

Never commit passwords, `.env`, or API keys. If you accidentally committed a secret, tell your teacher — rotating the key matters more than hiding the mistake.

---

## 7. Fix common mistakes

### “Please tell me who you are”

Run the `git config` commands from [§2](#2-one-time-setup).

### “rejected — non-fast-forward”

Someone else pushed first. Pull, then push:

```bash
git pull
# fix conflicts if any, then:
git push
```

### Merge conflict

Git marks both versions in the file:

```
<<<<<<< HEAD
your change
=======
their change
>>>>>>> branch-name
```

Edit the file, keep the correct code, delete the `<<<<`, `====`, `>>>>` lines, then:

```bash
git add conflicted-file.java
git commit -m "Resolve merge conflict in Main.java"
```

### Undo last commit (not pushed yet)

```bash
git reset --soft HEAD~1    # keeps your file changes
```

### Discard changes in one file (careful — cannot undo)

```bash
git restore Main.java
```

### See history

```bash
git log --oneline -10
git diff                     # unstaged changes
git diff --staged            # staged changes
```

---

## 8. Cheat sheet

| I want to… | Command |
|------------|---------|
| Copy a repo | `git clone <url>` |
| See status | `git status` |
| Stage all | `git add .` |
| Commit | `git commit -m "message"` |
| Upload | `git push` |
| Download updates | `git pull` |
| New branch | `git switch -c branch-name` |
| Switch branch | `git switch branch-name` |
| View history | `git log --oneline` |
| Who changed line 10? | `git blame File.java` |

---

## GitHub in the browser (2-minute tour)

| Page | Use |
|------|-----|
| **Code** | Browse files, clone URL, download ZIP |
| **Commits** | See every save point |
| **Branches** | List / switch default branch |
| **Pull requests** | Propose merging your branch → `main` |
| **Issues** | Bugs, tasks, questions |

**Typical assignment flow**

1. Clone course repo  
2. `git switch -c your-name-lab1`  
3. Code → `add` → `commit` → `push`  
4. On GitHub: **Compare & pull request** → submit link to teacher  

---

## Quick reference

**Git** = local version control. **GitHub** = remote backup + collaboration.

**Loop:** `pull` → edit → `status` → `add` → `commit` → `push`

**Good commit message:** what changed and why — `Add BankAccount withdraw method` not `update` or `final final2`.

**Never commit:** build output (`target/`, `*.class`), IDE folders (`.idea/`), secrets.
