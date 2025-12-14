// console.log("Table element:", document.querySelector(".payroll-table"));

async function loadIntoTable(url, table) {
    const tableBody = table.querySelector("tbody");

    const response = await fetch(url);
    const employees = await response.json();
        
    tableBody.innerHTML = "";

    const limitedEmployees = employees.slice(0, 10);

    limitedEmployees.forEach(emp => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${emp.employeeFirstName} ${emp.employeeLastName}</td>
            <td>${emp.employeeEmail}</td>
            <td>${emp.employeeContactNumber}</td>
            <td>${emp.employeeEmploymentType}</td> 
        `;

        const actionTd = document.createElement("td");
        const btn = document.createElement("button");
        btn.classList.add("emp-more-btn");
        btn.textContent = "View";

        btn.addEventListener("click", () => showPayrollDialog(emp));

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

