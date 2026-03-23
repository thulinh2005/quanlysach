document.addEventListener('DOMContentLoaded', function () {
    // --- 1. XỬ LÝ TÌM KIẾM NHANH (Giữ nguyên logic của bạn) ---
    const searchInput = document.getElementById('searchInput');
    const searchCategory = document.getElementById('searchCategory');

    if (searchInput && searchCategory) {
        searchInput.addEventListener('keyup', function () {
            const keyword = this.value.toLowerCase().trim();
            const colIndex = searchCategory.value;
            const rows = document.querySelectorAll('#tableMuonTra tbody tr'); // Chỉ định rõ ID bảng

            rows.forEach(row => {
                const cell = row.querySelector(`td:nth-child(${colIndex})`);
                if (cell) {
                    const text = cell.innerText.toLowerCase();
                    row.style.display = text.includes(keyword) ? "" : "none";
                }
            });
        });

        searchCategory.addEventListener('change', () => {
            searchInput.dispatchEvent(new Event('keyup'));
        });
    }

    // --- 2. ĐỔ DỮ LIỆU VÀO MODAL SỬA ---


    // --- 3. XỬ LÝ ĐỔ DỮ LIỆU VÀO MODAL XEM CHI TIẾT ---
    const viewButtons = document.querySelectorAll('.view-btn');
    viewButtons.forEach(btn => {
        btn.addEventListener('click', function () {
            // Đổ Text vào các thẻ span/h5 trong modal xem
            document.getElementById('view-maMuon').innerText = this.getAttribute('data-id');
            document.getElementById('view-docGia').innerText = this.getAttribute('data-docgia');
            document.getElementById('view-sach').innerText = this.getAttribute('data-sach');
            
            // Các thông tin khác bạn có thể thêm thuộc tính data-* ở file HTML rồi gọi ở đây
        });
    });
});

function addRow(containerId) {
    const container = document.getElementById(containerId);
    const row = document.createElement('div');
    row.className = 'row g-2 mb-2 sach-row';
    row.innerHTML = `
        <div class="col-7">
            <select name="maSachs[]" class="form-select" required>${document.getElementById('sach-source').innerHTML}</select>
        </div>
        <div class="col-3">
            <input type="number" name="soLuongs[]" class="form-control" value="1" min="1">
        </div>
        <div class="col-2">
            <button type="button" class="btn btn-danger w-100" onclick="this.closest('.row').remove()">X</button>
        </div>`;
    container.appendChild(row);
}

document.addEventListener('DOMContentLoaded', function () {
    const editButtons = document.querySelectorAll('.edit-btn');
    editButtons.forEach(btn => {
        btn.addEventListener('click', function () {
            document.getElementById('edit-maMuon').value = this.getAttribute('data-id');
            document.getElementById('edit-maNv').value = this.getAttribute('data-manv');
            document.getElementById('edit-trangThai').value = this.getAttribute('data-trangthai');
            
            // Render lại danh sách sách khi sửa (tùy chọn nâng cao)
        });
    });
});

// Hàm thêm một hàng sách mới vào Modal Sửa
function addEditRow(maSach = '', soLuong = 1) {
    const container = document.getElementById('edit-sach-container');
    const row = document.createElement('div');
    row.className = 'row g-2 mb-2 align-items-end sach-row';
    
    const options = document.getElementById('sach-options-template').innerHTML;
    
    row.innerHTML = `
        <div class="col-7">
            <select name="maSachs[]" class="form-select">${options}</select>
        </div>
        <div class="col-3">
            <input type="number" name="soLuongs[]" class="form-control" value="${soLuong}" min="1">
        </div>
        <div class="col-2">
            <button type="button" class="btn btn-danger w-100" onclick="this.closest('.row').remove()">X</button>
        </div>
    `;
    container.appendChild(row);
    if(maSach) row.querySelector('select').value = maSach;
}

// Lắng nghe sự kiện nhấn nút Sửa trên bảng
document.addEventListener('click', function(e) {
    const btn = e.target.closest('.edit-btn');
    if (btn) {
        document.getElementById('edit-maMuon').value = btn.dataset.id;
        document.getElementById('edit-maNv').value = btn.dataset.manv;
        document.getElementById('edit-trangThai').value = btn.dataset.trangthai;

        const container = document.getElementById('edit-sach-container');
        container.innerHTML = '';

const sachIds = btn.dataset.sachids ? btn.dataset.sachids.split(',') : [];
const soLuongs = btn.dataset.soluongs ? btn.dataset.soluongs.split(',') : [];

if (sachIds.length === 0 || sachIds[0] === "") {
    addEditRow(); // fallback nếu không có dữ liệu
} else {
    for (let i = 0; i < sachIds.length; i++) {
        addEditRow(sachIds[i], soLuongs[i]);
    }
}

    }
});

// --- 4. HÀM XÁC NHẬN XÓA (Nếu bạn có nút xóa phiếu) ---
function confirmDelete(event) {
    const check = confirm("Bạn có chắc chắn muốn xóa bản ghi mượn trả này không?");
    if (!check) {
        event.preventDefault(); // Ngăn không cho chuyển hướng nếu bấm Hủy
        return false;
    }
    return true;
}