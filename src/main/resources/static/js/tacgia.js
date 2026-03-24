const api = "http://localhost:8080/api/tacgia";
let dataGlobal = [];

// 1. LOAD DATA
function loadTacGia() {
    fetch(api)
        .then(res => res.json())
        .then(data => {
            dataGlobal = data;
            renderTable(data);
        });
}

// 2. RENDER TABLE
function renderTable(data) {
    let html = "";
    data.forEach(tg => {
        html += `
        <tr>
            <td class="ps-3 fw-bold">${tg.maTacGia}</td>
            <td>${tg.tenTacGia}</td>
            <td class="text-center">
                <div class="btn-group gap-2">
                    <a href="javascript:void(0)" class="text-warning" onclick="openEdit(${tg.maTacGia}, '${tg.tenTacGia}')" data-bs-toggle="modal" data-bs-target="#editModal">
                        <i class="bi bi-pencil-square fs-5"></i>
                    </a>
                    <a href="javascript:void(0)" class="text-danger" onclick="deleteTG(${tg.maTacGia})">
                        <i class="bi bi-trash3-fill fs-5"></i>
                    </a>
                </div>
            </td>
        </tr>`;
    });
    document.getElementById("tbody").innerHTML = html;
}

// 3. TÌM KIẾM
document.getElementById("searchInput").addEventListener("keyup", function() {
    const keyword = this.value.toLowerCase().trim();
    const filtered = dataGlobal.filter(tg => tg.tenTacGia.toLowerCase().includes(keyword));
    renderTable(filtered);
});

// 4. THÊM
function addTG() {
    const ten = document.getElementById("tenTacGia").value;
    fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenTacGia: ten })
    }).then(() => {
        loadTacGia();
        bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
        document.getElementById("tenTacGia").value = "";
    });
}

// 5. XÓA
function deleteTG(id) {
    if (confirm("Xóa tác giả này?")) {
        fetch(api + "/" + id, { method: "DELETE" }).then(() => loadTacGia());
    }
}

// 6. MỞ MODAL SỬA
function openEdit(id, ten) {
    document.getElementById("editId").value = id;
    document.getElementById("editTen").value = ten;
}

// 7. CẬP NHẬT
function updateTG() {
    const id = document.getElementById("editId").value;
    const ten = document.getElementById("editTen").value;
    fetch(api + "/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenTacGia: ten })
    }).then(() => {
        loadTacGia();
        bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
    });
}

loadTacGia();