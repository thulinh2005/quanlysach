const api = "http://localhost:8080/api/theloai";

let dataGlobal = [];

// LOAD DATA
function loadTheLoai() {
    fetch(api)
        .then(res => res.json())
        .then(data => {
            dataGlobal = data;
            renderTable(data);
        });
}

// RENDER TABLE
function renderTable(data) {
    let html = "";

    data.forEach(tl => {
        html += `
        <tr>
            <td class="ps-3 fw-bold">${tl.maTheLoai}</td>
            <td>${tl.tenTheLoai}</td>
            <td class="text-center">
                <div class="btn-group gap-2">

                    <a href="#" class="text-warning"
                       onclick="openEdit(${tl.maTheLoai}, '${tl.tenTheLoai}')"
                       data-bs-toggle="modal" data-bs-target="#editModal">
                        <i class="bi bi-pencil-square fs-5"></i>
                    </a>

                    <a href="#" class="text-danger"
                       onclick="deleteTL(${tl.maTheLoai})">
                        <i class="bi bi-trash3-fill fs-5"></i>
                    </a>

                </div>
            </td>
        </tr>
        `;
    });

    document.getElementById("tbody").innerHTML = html;
}

// SEARCH (GIỐNG TRANG SÁCH)
document.addEventListener("DOMContentLoaded", function () {

    loadTheLoai();

    const searchInput = document.getElementById("searchInput");

    if (searchInput) {
        searchInput.addEventListener("keyup", function () {
            const keyword = this.value.toLowerCase().trim();

            const filtered = dataGlobal.filter(tl =>
                tl.tenTheLoai.toLowerCase().includes(keyword)
            );

            renderTable(filtered);
        });
    }
});

// ADD
function addTL() {
    const ten = document.getElementById("tenTheLoai").value;

    fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenTheLoai: ten })
    }).then(() => {
        loadTheLoai();
        document.getElementById("tenTheLoai").value = "";
    });
}

// DELETE
// DELETE
function deleteTL(id) {
    if (!confirm("Bạn có chắc chắn muốn xóa thể loại này?")) return;

    fetch(api + "/" + id, {
        method: "DELETE"
    })
        .then(async response => {
            if (!response.ok) {
                // Lấy nội dung lỗi từ server ném ra
                const errorMsg = await response.text();
                alert(errorMsg);
            } else {
                alert("Xóa thành công!");
                loadTheLoai(); // Gọi đúng hàm để refresh bảng
            }
        })
        .catch(err => {
            console.error("Lỗi:", err);
            alert("Đã có lỗi xảy ra!");
        });
}

// OPEN EDIT
function openEdit(id, ten) {
    document.getElementById("editId").value = id;
    document.getElementById("editTen").value = ten;
}

// UPDATE
function updateTL() {
    const id = document.getElementById("editId").value;
    const ten = document.getElementById("editTen").value;

    fetch(api + "/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ tenTheLoai: ten })
    }).then(() => loadTheLoai());
}