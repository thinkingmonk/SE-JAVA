function isPass(marks) {
    return marks >= 40;
}

function getInitials(name) {
    const parts = name.trim().split(/\s+/).filter(Boolean);
    if (parts.length === 0) return "—";
    if (parts.length === 1) return parts[0].charAt(0).toUpperCase();
    return (parts[0].charAt(0) + parts[parts.length - 1].charAt(0)).toUpperCase();
}

const form = document.getElementById("cardForm");
const themeBtn = document.getElementById("themeBtn");

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const roll = document.getElementById("roll").value;
    const marks = Number(document.getElementById("marks").value);

    document.getElementById("displayName").textContent = name || "Student name";
    document.getElementById("displayRoll").textContent = roll;
    document.getElementById("displayInitials").textContent = getInitials(name);

    const result = document.getElementById("result");
    result.classList.remove("pass", "fail", "result--empty");

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
