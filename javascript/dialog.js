function showPayrollDialog(emp) {
    if (!window.payrollDialog) {
        console.error("Dialog not loaded yet!");
        return;
    }
    window.payrollDialog.showModal();
}
function loadDialog() {
    fetch("../html/payslip-dialog.html")
        .then(res => res.text())
        .then(html => {
            document.getElementById("dialog-container")
                .insertAdjacentHTML("beforeend", html);

            const dialog = document.getElementById("payroll-dialog");

            dialog.addEventListener("click", (event) => {
                const rect = dialog.getBoundingClientRect();
                const inside =
                    event.clientX >= rect.left &&
                    event.clientX <= rect.right &&
                    event.clientY >= rect.top &&
                    event.clientY <= rect.bottom;

                if (!inside) dialog.close();
            });

            window.payrollDialog = dialog; // Make it globally accessible
        });
}

document.addEventListener("DOMContentLoaded", loadDialog);