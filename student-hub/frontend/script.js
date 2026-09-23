const API_BASE = "http://localhost:8080";
const STORAGE_KEY = "tcet-student-hub-announcements";

let announcements = [];
let useApi = false;

const form = document.getElementById("announcementForm");
const announcementList = document.getElementById("announcementList");
const emptyMsg = document.getElementById("emptyMsg");
const feedStats = document.getElementById("feedStats");
const apiStatus = document.getElementById("apiStatus");
const submitBtn = document.getElementById("submitBtn");
const themeBtn = document.getElementById("themeBtn");

function setApiStatus(online) {
    useApi = online;
    apiStatus.textContent = online ? "Java API connected" : "Offline mode";
    apiStatus.className = "status " + (online ? "status--online" : "status--offline");
}

function saveLocal(data) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
}

function loadLocal() {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return [];
    try {
        return JSON.parse(raw);
    } catch {
        return [];
    }
}

async function fetchAnnouncements() {
    try {
        const response = await fetch(`${API_BASE}/api/announcements`);
        if (!response.ok) throw new Error("Bad response");
        const data = await response.json();
        setApiStatus(true);
        return data;
    } catch {
        setApiStatus(false);
        return loadLocal();
    }
}

async function createAnnouncement(payload) {
    if (useApi) {
        const response = await fetch(`${API_BASE}/api/announcements`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });
        if (!response.ok) throw new Error("Create failed");
        return response.json();
    }

    const local = loadLocal();
    const item = {
        id: Date.now(),
        author: payload.author,
        title: payload.title,
        body: payload.body,
        createdAt: new Date().toISOString()
    };
    local.unshift(item);
    saveLocal(local);
    return item;
}

async function deleteAnnouncement(id) {
    if (useApi) {
        const response = await fetch(`${API_BASE}/api/announcements/${id}`, {
            method: "DELETE"
        });
        if (!response.ok) throw new Error("Delete failed");
        return;
    }

    const filtered = loadLocal().filter(function (item) {
        return item.id !== id;
    });
    saveLocal(filtered);
}

function formatDate(isoString) {
    const date = new Date(isoString);
    return date.toLocaleString("en-IN", {
        day: "2-digit",
        month: "short",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit"
    });
}

function createAnnouncementElement(item) {
    const li = document.createElement("li");
    li.className = "announcement";

    const title = document.createElement("h3");
    title.className = "announcement__title";
    title.textContent = item.title;

    const meta = document.createElement("p");
    meta.className = "announcement__meta";
    meta.textContent = `${item.author} · ${formatDate(item.createdAt)}`;

    const body = document.createElement("p");
    body.className = "announcement__body";
    body.textContent = item.body;

    const actions = document.createElement("div");
    actions.className = "announcement__actions";

    const deleteBtn = document.createElement("button");
    deleteBtn.type = "button";
    deleteBtn.className = "btn btn--danger";
    deleteBtn.textContent = "Delete";
    deleteBtn.setAttribute("aria-label", "Delete announcement");

    deleteBtn.addEventListener("click", async function () {
        deleteBtn.disabled = true;
        try {
            await deleteAnnouncement(item.id);
            await refreshAnnouncements();
        } catch {
            deleteBtn.disabled = false;
            alert("Could not delete. Try again.");
        }
    });

    actions.appendChild(deleteBtn);
    li.appendChild(title);
    li.appendChild(meta);
    li.appendChild(body);
    li.appendChild(actions);
    return li;
}

function renderAnnouncements() {
    announcementList.innerHTML = "";

    for (const item of announcements) {
        announcementList.appendChild(createAnnouncementElement(item));
    }

    const count = announcements.length;
    const label = count === 1 ? "announcement" : "announcements";
    feedStats.textContent = count === 0 ? "No posts yet" : `${count} ${label}`;
    emptyMsg.classList.toggle("empty-msg--hidden", count > 0);
}

async function refreshAnnouncements() {
    announcements = await fetchAnnouncements();
    renderAnnouncements();
}

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const author = document.getElementById("author").value.trim();
    const title = document.getElementById("title").value.trim();
    const body = document.getElementById("body").value.trim();

    if (!author || !title || !body) return;

    submitBtn.disabled = true;
    submitBtn.textContent = "Publishing…";

    try {
        await createAnnouncement({ author, title, body });
        form.reset();
        await refreshAnnouncements();
    } catch {
        alert("Could not publish. Check if the Java server is running.");
    } finally {
        submitBtn.disabled = false;
        submitBtn.textContent = "Publish";
    }
});

themeBtn.addEventListener("click", function () {
    document.body.classList.toggle("dark");
});

refreshAnnouncements();
