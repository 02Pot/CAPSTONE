function openModal(id) {
  const m = document.getElementById(id);
  if (m) m.classList.add('show');
  m && m.setAttribute('aria-hidden', 'false');
}

function closeModal(id) {
  const m = document.getElementById(id);
  if (m) m.classList.remove('show');
  m && m.setAttribute('aria-hidden', 'true');
}

const addBtn = document.getElementById('addEmployeeBtn');
if (addBtn) addBtn.addEventListener('click', () => openModal('addModal'));

const editBtn = document.getElementById('editEmployeeBtn');
if (editBtn) editBtn.addEventListener('click', () => openModal('editModal'));

document.querySelectorAll('[data-close]').forEach(el => {
  el.addEventListener('click', () => {
    const id = el.getAttribute('data-close');
    closeModal(id);
  });
});

window.addEventListener('click', (e) => {
  if (e.target.classList.contains('modal')) {
    e.target.classList.remove('show');
    e.target.setAttribute('aria-hidden', 'true');
  }
});

function appendEmployeeRow(emp, table) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${emp.employeeFirstName} ${emp.employeeLastName}</td>
        <td>${emp.employeeContactNumber || "N/A"}</td>
        <td>${emp.employeeEmail || "N/A"}</td>
        <td>${emp.employeeGender || "N/A"}</td>
        <td>${emp.employeeEmploymentType || "N/A"}</td>
        <td>${emp.employeeDepartment || "N/A"}</td>
    `;

    const actionTd = document.createElement("td");
    const btn = document.createElement("button");
    btn.classList.add("emp-more-btn");
    btn.textContent = "View";
    btn.addEventListener("click", () => openEmployeeDetails(emp));
    actionTd.appendChild(btn);
    tr.appendChild(actionTd);

    table.querySelector("tbody").appendChild(tr);

    const tbody = table.querySelector("tbody");
    if (tbody.children.length > 10) {
        tbody.removeChild(tbody.lastElementChild);
    }
}

function connectAddEmployeeForm() {
    const addEmployeeForm = document.getElementById("addEmployeeForm");

    if (!addEmployeeForm) {
        console.error("Add Employee form not found!");
        return;
    }

    addEmployeeForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData(addEmployeeForm);
        const employmentType = formData.get("employeeEmploymentType");

        const data = {
            employeeFirstName: formData.get("firstName"),
            employeeLastName: formData.get("lastName"),
            employeeAddress: formData.get("address"),
            employeeEmail: formData.get("email"),
            employeeContactNumber: formData.get("contact"),
            employeeGender: formData.get("gender")?.toUpperCase(),
            employeeEmploymentType: formData.get("employeeEmploymentType"),
            dateOfHire: formData.get("dateofhire"),
            employeeDepartment: formData.get("department"),
        };
        
        if(employmentType === "Regular"){
          data.monthlySalary = Number(formData.get("rate"));
        }else{
          data.employeeRate = Number(formData.get("rate"));
        }

        try {
            const token = localStorage.getItem("jwtToken");

            const res = await fetch("http://localhost:8081/api/employee", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(data)
            });

            if (!res.ok) throw new Error("Failed to add employee");

            const result = await res.json();
            console.log(result);

            const table = document.querySelector(".emp-table");
            if (table) appendEmployeeRow(result, table);

            addEmployeeForm.reset();

        } catch (err) {
            console.error(err);
            alert("Error adding employee: " + err.message);
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    connectAddEmployeeForm();
});


//EMPLOYEE DIALOG
async function openEmployeeDetails(emp) {
  if (!window.employeeDialog) {
      console.error("Dialog not loaded yet!");
      return;
  }

  document.getElementById("emp-first-name").textContent = emp.employeeFirstName;
  document.getElementById("emp-last-name").textContent = emp.employeeLastName;
  document.getElementById("emp-address").textContent = emp.employeeAddress || "N/A";
  document.getElementById("emp-contact").textContent = emp.employeeContactNumber;
  document.getElementById("emp-email").textContent = emp.employeeEmail;

  document.getElementById("doj").textContent = emp.dateOfHire || "N/A";
  document.getElementById("emp-type").textContent = emp.employeeEmploymentType;
  document.getElementById("emp-dep").textContent = emp.employeeDepartment;
  document.getElementById("emp-rate").textContent = emp.employeeRate;

  const token = localStorage.getItem("jwtToken");

  const [earningsRes, deductionsRes] = await Promise.all([
    fetch(`http://localhost:8081/earnings/${emp.employeeId}`, {
      headers: { "Authorization": `Bearer ${token}` }
    }),
    fetch(`http://localhost:8081/deductions/${emp.employeeId}`, {
      headers: { "Authorization": `Bearer ${token}` }
    })
  ]);

  const allEarnings = await earningsRes.json();
  const allDeductions = await deductionsRes.json();

  const empEarnings = allEarnings.filter(e => e.employeeId === emp.employeeId);
  const empDeductions = allDeductions.filter(d => d.employeeId === emp.employeeId);

  const earnBody = document.getElementById("earnings-body");
  earnBody.innerHTML = "";

  empEarnings.forEach(e => {
      const row = document.createElement("tr");
      row.innerHTML = `
          <td class="label-col">${e.earningsName}</td>
          <td class="amount-col">${e.earningsAmount}</td>
      `;
      earnBody.appendChild(row);
  });

  const dedBody = document.getElementById("deductions-body");
  dedBody.innerHTML = "";

  empDeductions.forEach(d => {
      const row = document.createElement("tr");
      row.innerHTML = `
          <td class="label-col">${d.deductionsName}</td>
          <td class="amount-col">${d.deductionsAmount}</td>
      `;
      dedBody.appendChild(row);
  });

  window.employeeDialog.showModal();
}

async function loadIntoTable(url, table) {
    const tableBody = table.querySelector("tbody");

    const token = localStorage.getItem("jwtToken");

    const response = await fetch(url, {
        headers: {
            "Authorization": `Bearer ${token}` //send token
        }
    });

    if (!response.ok) throw new Error("Failed to fetch employees");
    const employees = await response.json();

    tableBody.innerHTML = "";

    const limitedEmployees = employees.slice(0, 10);

    limitedEmployees.forEach(emp => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${emp.employeeFirstName} ${emp.employeeLastName}</td>
            <td>${emp.employeeContactNumber}</td>
            <td>${emp.employeeEmail}</td>
            <td>${emp.employeeGender}</td>
            <td>${emp.employeeEmploymentType}</td>
            <td>${emp.employeeDepartment}</td>
        `;

        const actionTd = document.createElement("td");
        const btn = document.createElement("button");
        btn.classList.add("emp-more-btn");
        btn.textContent = "View";

        btn.addEventListener("click", () => openEmployeeDetails(emp));

        actionTd.appendChild(btn);
        tr.appendChild(actionTd);

        tableBody.appendChild(tr);
    });
    
}

async function loadDialog() {
  const res = await fetch("../html/employee-details.html");
  const html = await res.text();
  document.getElementById("dialog-container")
    .insertAdjacentHTML("beforeend", html);

  const dialog = document.getElementById("employee-dialog");

  dialog.addEventListener("click", (event) => {
    const rect = dialog.getBoundingClientRect();
    const inside =
        event.clientX >= rect.left &&
        event.clientX <= rect.right &&
        event.clientY >= rect.top &&
        event.clientY <= rect.bottom;

      if (!inside) dialog.close();
  });

  window.employeeDialog = dialog;
}

document.addEventListener("DOMContentLoaded", async () => {
    loadDialog();  // wait for dialog to laod
    loadIntoTable("http://localhost:8081/api/employee", document.querySelector(".emp-table"));
});
