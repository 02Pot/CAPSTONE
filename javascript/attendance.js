function openAttendancePicker() {
    document.getElementById("attendanceFile").click();
}

function toggleAttendanceViews(hasAttendance) {
    const uploadView = document.getElementById("uploadView");
    const statsView  = document.getElementById("statsView");

    if (!uploadView || !statsView) return;

    if (!hasAttendance) {
        uploadView.style.display = "flex";  // show import card
        statsView.style.display  = "none";   // hide stats card
    } else {
        uploadView.style.display = "none";   // hide import card
        statsView.style.display  = "flex";   // show stats card
    }
}

// Load attendance table
async function loadAttendanceTable() {
    const token = localStorage.getItem("jwtToken");

    try {
        const res = await fetch("http://localhost:8081/attendance", {
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (!res.ok) throw new Error("Failed to fetch attendance");

        const data = await res.json();
        const hasAttendance = data.length > 0;

        toggleAttendanceViews(hasAttendance);

    } catch (err) {
        console.error(err);
        toggleAttendanceViews();
    }
}

// Handle attendance file upload
document.getElementById("attendanceFile").addEventListener("change", async function(event) {
    const file = event.target.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    const token = localStorage.getItem("jwtToken");

    try {
        const res = await fetch("http://localhost:8081/attendance/import", {
            method: "POST",
            headers: { "Authorization": `Bearer ${token}` },
            body: formData
        });

        if (!res.ok) throw new Error("Failed to import attendance");
        //TODO ALERT POPUP
        // alert("Attendance imported successfully!");

        await loadAttendanceTable(); // refresh table & toggle views
        event.target.value = "";     // reset input

    } catch (err) {
        console.error(err);
        alert("Error importing attendance: " + err.message);
    }
});

document.getElementById("runPayrollBtn").addEventListener("click", async () => {
    const token = localStorage.getItem("jwtToken");

    try {
        const res = await fetch("http://localhost:8081/payroll/run-cutoff", {
            method: "POST",
            headers: { "Content-Type": "application/json" ,
                "Authorization": `Bearer ${token}`
            }
        });
        const data = await res.json();
        // console.log(data)

        alert(data.message); // "Payroll successfully processed for current cutoff."
        location.reload()
    } 
    catch (err) {
        alert("Error running payroll!");
        console.error(err);
    }
});


document.addEventListener("DOMContentLoaded", () => {
    loadAttendanceTable();
});
