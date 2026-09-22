let tasks = [];
let nextId = 1;

const form = document.getElementById("taskForm");
const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const themeBtn = document.getElementById("themeBtn");
const emptyMsg = document.getElementById("emptyMsg");

function updateStats() {
    const total = tasks.length;
    const done = tasks.filter(function (task) {
        return task.done;
    }).length;
    const stats = document.getElementById("stats");

    if (total === 0) {
        stats.textContent = "No tasks yet";
    } else {
        const label = total === 1 ? "task" : "tasks";
        stats.textContent = `${total} ${label} · ${done} done`;
    }

    emptyMsg.classList.toggle("empty-msg--hidden", total > 0);
}

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
        tasks = tasks.filter(function (t) {
            return t.id !== task.id;
        });
        renderTasks();
    });

    li.appendChild(toggleBtn);
    li.appendChild(span);
    li.appendChild(deleteBtn);
    return li;
}

function renderTasks() {
    const list = document.getElementById("taskList");
    list.innerHTML = "";

    for (const task of tasks) {
        list.appendChild(createTaskElement(task));
    }

    updateStats();
}

function syncAddButton() {
    addBtn.disabled = taskInput.value.trim().length === 0;
}

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const text = taskInput.value.trim();
    if (text.length === 0) return;

    tasks.push({ id: nextId, text: text, done: false });
    nextId += 1;
    taskInput.value = "";
    syncAddButton();
    renderTasks();
});

taskInput.addEventListener("input", syncAddButton);

themeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});

syncAddButton();
renderTasks();
