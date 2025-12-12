async function showPayrollDialog(emp) {
    if (!window.payrollDialog) {
        console.error("Dialog not loaded yet!");
        return;
    }

    const empId = emp.employeeId;

    try {
        const [earningsRes, deductionsRes] = await Promise.all([
            fetch("../jsonfiles/earnings.json"),
            fetch("../jsonfiles/deductions.json")
        ]);

        const allEarnings = await earningsRes.json();
        const allDeductions = await deductionsRes.json();

        // Filter for this employee
        const empEarnings = allEarnings.filter(e => e.employeeId === empId);
        const empDeductions = allDeductions.filter(d => d.employeeId === empId);

        const earnBody = window.payrollDialog.querySelector("#earnings-body");
        earnBody.innerHTML = ""; // clear existing

        empEarnings.forEach(e => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td class="label-col">${e.earningsName}</td>
                <td class="amount-col">${e.earningsAmount}</td>
            `;
            earnBody.appendChild(row);
        });

        // Insert deductions rows
        const dedBody = window.payrollDialog.querySelector("#deductions-body");
        dedBody.innerHTML = ""; // clear existing

        empDeductions.forEach(d => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td class="label-col">${d.deductionsName}</td>
                <td class="amount-col">${d.deductionsAmount}</td>
            `;
            dedBody.appendChild(row);
        });

        // Calculate totals
        const grossPay = empEarnings.reduce((t, e) => t + (e.earningsAmount || 0), 0);
        const totalDeductions = empDeductions.reduce((t, d) => t + (d.deductionsAmount || 0), 0);
        const netPay = grossPay - totalDeductions;

        document.getElementById("emp-name").textContent =
            `${emp.employeeFirstName} ${emp.employeeLastName}`;
        document.getElementById("worked-hrs").textContent = emp.workedHours || 0;
        document.getElementById("ot-hrs").textContent = emp.overtimeHours || 0;
        document.getElementById("emp-rate").textContent = emp.employeeRate || 0;
        document.getElementById("doj").textContent = emp.dateOfHire || "N/A";
        document.getElementById("emp-type").textContent = emp.employeeEmploymentType;
        document.getElementById("emp-dep").textContent = emp.employeeDepartment;
        document.getElementById("p-period").textContent = emp.payPeriod || "N/A";

        // Fill payroll values
        document.getElementById("gross-pay").textContent = grossPay;
        document.getElementById("total-deductions").textContent = totalDeductions;
        document.getElementById("net-pay").textContent = netPay;

        // OPEN dialog
        window.payrollDialog.showModal();

    } catch (err) {
        console.error(err);
        alert("Failed to load payroll data.");
    }
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

document.addEventListener("DOMContentLoaded", () => {
    loadDialog();  // load dialog
    loadIntoTable("../jsonfiles/employee.json", document.querySelector(".emp-table"));
});
