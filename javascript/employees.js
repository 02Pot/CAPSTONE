// JS file (optional for future interactions)
console.log("Sidebar loaded");

// helper: open modal by id
function openModal(id) {
  const m = document.getElementById(id);
  if (m) m.classList.add('show');
  m && m.setAttribute('aria-hidden', 'false');
}

// helper: close modal by id
function closeModal(id) {
  const m = document.getElementById(id);
  if (m) m.classList.remove('show');
  m && m.setAttribute('aria-hidden', 'true');
}

// wire open button (add)
const addBtn = document.getElementById('addEmployeeBtn');
if (addBtn) addBtn.addEventListener('click', () => openModal('addModal'));

// if you add an edit button with id "editEmployeeBtn" (global) you can wire too
const editBtn = document.getElementById('editEmployeeBtn');
if (editBtn) editBtn.addEventListener('click', () => openModal('editModal'));

// close buttons (the X and cancel)
document.querySelectorAll('[data-close]').forEach(el => {
  el.addEventListener('click', () => {
    const id = el.getAttribute('data-close');
    closeModal(id);
  });
});

// close when clicking outside modal-content
window.addEventListener('click', (e) => {
  if (e.target.classList.contains('modal')) {
    e.target.classList.remove('show');
    e.target.setAttribute('aria-hidden', 'true');
  }
});

// HANDLE Add form submit (demo: prevent default and log form contents)
const addForm = document.getElementById('addEmployeeForm');
if (addForm) {
  addForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const formData = new FormData(addForm);
    const data = Object.fromEntries(formData.entries());
    console.log('Add Employee data:', data);

    // TODO: replace with real save action (AJAX / form POST)
    // For now: close modal after submit
    closeModal('addModal');

    // Optionally clear form:
    addForm.reset();

    // Optionally: update table UI directly (append row) or refresh page
  });
}



let activeId = null; // kung sino ang aktibong row


// Ipakita ang choice modal
function showChoiceModal(id, fullName){
  activeId = id;
  document.getElementById('empName').textContent = fullName;
  document.getElementById('choiceModal').style.display = 'flex';
}

function closeChoiceModal(){
  document.getElementById('choiceModal').style.display = 'none';
  activeId = null;
}

function viewEmployee(){
  closeChoiceModal();
  // halimbawa: bukas ng view page para sa view details
  window.location.href = `../html/irreg.html?id=${activeId}`;
}

function deleteEmployee(){
  if(!confirm('Are you sure you want to delete this employee?')) return;
  closeChoiceModal();
  // AJAX or redirect to delete endpoint
  fetch(`delete-employee.php?id=${activeId}`, {method:'DELETE'})
    .then(r => r.ok ? location.reload() : alert('Delete failed'));
}
// close modal on ESC or click outside
document.addEventListener('keydown', e => {
  if (e.key === 'Escape') closeChoiceModal();
});
document.getElementById('choiceModal').onclick = e => {
  if (e.target === e.currentTarget) closeChoiceModal();
};