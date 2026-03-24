const api = "http://localhost:8080/api/nxb";
let dataGlobal = [];

// 1. LOAD DATA
function loadNXB() {
    fetch(api)
        .then(res => res.json())
        .then(data => {
            console.log("Dữ liệu nhận được:", data); // Kiểm tra ở F12 xem có dữ liệu chưa
            dataGlobal = data;
            renderTable(data);
        })
        .catch(err => console.error("Lỗi fetch:", err));
}

// 2. RENDER TABLE
function renderTable(data) {
    let html = "";
    data.forEach(nxb => {
        // QUAN TRỌNG: nxb.maNxb và nxb.tenNxb (chữ x viết thường giống file Java)
        html += `
        <tr>
            <td class="ps-3 fw-bold">${nxb.maNxb}</td>
            <td>${nxb.tenNxb}</td>
            <td class="text-center">
                <div class="btn-group gap-2">
                    <a href="javascript:void(0)" class="text-warning"
                       onclick="openEdit(${nxb.maNxb}, '${nxb.tenNxb}')"
                       data-bs-toggle="modal" data-bs-target="#editModal">
                        <i class="bi bi-pencil-square fs-5"></i>
                    </a>
                    <a href="javascript:void(0)" class="text-danger"
                       onclick="deleteNXB(${nxb.maNxb})">
                        <i class="bi bi-trash3-fill fs-5"></i>
                    </a>
                </div>
            </td>
        </tr>
        `;
    });
    document.getElementById("tbody").innerHTML = html;
}

// 3. SEARCH & INIT
document.addEventListener("DOMContentLoaded", function () {
    loadNXB();

    const searchInput = document.getElementById("searchInput");
    if (searchInput) {
        searchInput.addEventListener("keyup", function () {
            const keyword = this.value.toLowerCase().trim();
            const filtered = dataGlobal.filter(nxb =>
                nxb.tenNxb && nxb.tenNxb.toLowerCase().includes(keyword)
            );
            renderTable(filtered);
        });
    }
});

// Các hàm Add, Delete, Update giữ nguyên nhưng chú ý field tenNxb
function addNXB() {
    const ten = document.getElementById("tenNxb").value;
    fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenNxb: ten })
    }).then(() => {
        loadNXB();
        bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
        document.getElementById("tenNxb").value = "";
    });
}

function deleteNXB(id) {
    if (!confirm("Bạn có chắc chắn muốn xóa nhà xuất bản này?")) return;

    fetch(api + "/" + id, {
        method: "DELETE"
    })
        .then(async response => {
            if (!response.ok) {
                // Lấy tin nhắn lỗi từ Service ném ra
                const errorMsg = await response.text();
                alert(errorMsg);
            } else {
                alert("Xóa thành công!");
                loadNXB(); // Gọi đúng tên hàm để cập nhật lại bảng
            }
        })
        .catch(err => {
            console.error("Lỗi:", err);
            alert("Đã có lỗi xảy ra trong quá trình xóa.");
        });
}

function openEdit(id, ten) {
    document.getElementById("editId").value = id;
    document.getElementById("editTen").value = ten;
}

function updateNXB() {
    const id = document.getElementById("editId").value;
    const ten = document.getElementById("editTen").value;
    fetch(api + "/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenNxb: ten })
    }).then(() => {
        loadNXB();
        bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
    });
}