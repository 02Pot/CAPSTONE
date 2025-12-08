// console.log("Table element:", document.querySelector(".payroll-table"));

async function loadIntoTable(url, table) {
    const tableBody = table.querySelector("tbody");

    const response = await fetch(url);
    const employees = await response.json();
        
    tableBody.innerHTML = "";
    employees.forEach(employee => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${employee.employeeFirstName} ${employee.employeeLastName}</td>
            <td>${employee.employeeEmail}</td>
            <td>${employee.employeeContactNumber}</td>
            <td>${employee.employeeEmploymentType}</td>
        `;

        const actionTd = document.createElement("td");
        const btn = document.createElement("button");
        btn.classList.add("emp-more-btn");
        btn.textContent = "View";

        btn.addEventListener("click", () => showPayrollDialog(employee));

        actionTd.appendChild(btn);
        tr.appendChild(actionTd);

        tableBody.appendChild(tr);
    });
    
}

document.addEventListener("DOMContentLoaded",() => {
    const employeeTable = document.querySelector(".emp-table");
    // console.log(employeeTable);
    loadIntoTable("../jsonfiles/employee.json", employeeTable);
})

