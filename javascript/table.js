// console.log("Table element:", document.querySelector(".payroll-table"));
async function loadIntoTable(url, table) {
    const tableBody = table.querySelector("tbody");

    try {
        const token = localStorage.getItem("jwtToken");

        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}` //send token
            }
        });
        if (!response.ok) throw new Error("Failed to fetch employees");
        const employees = await response.json();
        console.log(employees);

        tableBody.innerHTML = "";
        const limitedEmployees = employees.slice(0, 10);

        limitedEmployees.forEach(emp => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${emp.employeeFirstName} ${emp.employeeLastName}</td>
                <td>${emp.employeeEmail}</td>
                <td>${emp.employeeContactNumber ?
                    (emp.employeeContactNumber.toString().startsWith("0")? emp.employeeContactNumber: "0" + emp.employeeContactNumber): ""}</td>
                <td>${emp.employeeEmploymentType.replaceAll("_"," ")}</td>
            `;

            const actionTd = document.createElement("td");

            if(emp.latestPayrollId){
                const btn = document.createElement("button");
                btn.classList.add("emp-more-btn");
                btn.textContent = "View";
                btn.addEventListener("click", () => showPayrollDialog(emp.latestPayrollId));
                actionTd.appendChild(btn);
            }else{
                actionTd.textContent = "No Payroll Yet"
            }

            tr.appendChild(actionTd);

            tableBody.appendChild(tr);
        });
    } catch (err) {
        console.error(err);
        tableBody.innerHTML = `<tr><td colspan="5">Failed to load employees</td></tr>`;
    }
}

document.addEventListener("DOMContentLoaded",() => {
    const employeeTable = document.querySelector(".emp-table");
    // console.log(employeeTable);
    loadIntoTable("http://localhost:8081/api/employee", employeeTable);
})

