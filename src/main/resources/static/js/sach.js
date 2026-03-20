document.addEventListener('DOMContentLoaded', function () {
    // --- 1. XỬ LÝ TÌM KIẾM NHANH ---
    const searchInput = document.getElementById('searchInput');
    const searchCategory = document.getElementById('searchCategory');

    if (searchInput && searchCategory) {
        searchInput.addEventListener('keyup', function () {
            const keyword = this.value.toLowerCase().trim();
            const colIndex = searchCategory.value;
            const rows = document.querySelectorAll('tbody tr');

            rows.forEach(row => {
                // nth-child đếm từ 1
                const cell = row.querySelector(`td:nth-child(${colIndex})`);
                if (cell) {
                    const text = cell.innerText.toLowerCase();
                    // Hiển thị nếu khớp từ khóa, hoặc nếu ô tìm kiếm trống
                    row.style.display = text.includes(keyword) ? "" : "none";
                }
            });
        });

        // Tự động tìm lại khi đổi mục (Category)
        searchCategory.addEventListener('change', () => {
            searchInput.dispatchEvent(new Event('keyup'));
        });
    }
    // --- 2. ĐỔ DỮ LIỆU VÀO MODAL SỬA ---
    const editButtons = document.querySelectorAll('.edit-btn');
    editButtons.forEach(btn => {
        btn.addEventListener('click', function () {
            // Lấy dữ liệu từ thuộc tính data-* của nút được bấm
            const id = this.getAttribute('data-id');
            const ten = this.getAttribute('data-ten');
            const nam = this.getAttribute('data-nam');
            const tacgia = this.getAttribute('data-tacgia');
            const theloai = this.getAttribute('data-theloai');
            const soluong = this.getAttribute('data-soluong');

            // Điền vào các ô input trong Modal Sửa
            document.getElementById('edit-maSach').value = id;
            document.getElementById('edit-tenSach').value = ten;
            document.getElementById('edit-namXuatBan').value = nam;
            document.getElementById('edit-tacGia').value = tacgia;
            document.getElementById('edit-theLoai').value = theloai;
            document.getElementById('edit-soLuong').value = soluong;
        });
    });
    // --- 4. XỬ LÝ ĐỔ DỮ LIỆU VÀO MODAL XEM CHI TIẾT ---
    const viewButtons = document.querySelectorAll('.view-btn');
    viewButtons.forEach(btn => {
        btn.addEventListener('click', function () {
            // Đổ Text vào các thẻ span/h5
            document.getElementById('view-tenSach').innerText = this.getAttribute('data-ten');
            document.getElementById('view-tacGia').innerText = this.getAttribute('data-tacgia');
            document.getElementById('view-theLoai').innerText = this.getAttribute('data-theloai');
            document.getElementById('view-nxb').innerText = this.getAttribute('data-nxb');
            document.getElementById('view-namXB').innerText = this.getAttribute('data-nam');
            document.getElementById('view-soLuong').innerText = this.getAttribute('data-soluong');

            // Đổ đường dẫn ảnh
            const biaPath = this.getAttribute('data-bia');
            document.getElementById('view-biaSach').src = "/images/" + (biaPath ? biaPath : "default-book.png");
        });
    });
});

// --- 3. HÀM XÁC NHẬN XÓA ---
function confirmDelete(event) {
    if (!confirm("Em có chắc chắn muốn xóa vĩnh viễn cuốn sách này không?")) {
        event.preventDefault();
        return false;
    }
    return true;

}