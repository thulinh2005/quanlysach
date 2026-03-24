package com.hau.quanlysach.controller;

import com.hau.quanlysach.repository.NhaXuatBanRepository;
import com.hau.quanlysach.repository.SachRepository;
import com.hau.quanlysach.repository.TacGiaRepository;
import com.hau.quanlysach.repository.TheLoaiRepository;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GiaoDienController {

    @Autowired
    private SachRepository sachRepository;
    @Autowired
    private TacGiaRepository tacGiaRepository;
    @Autowired
    private TheLoaiRepository theLoaiRepository;
    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;

    // Sửa lại hàm này để đổ đầy đủ dữ liệu khi vừa mở web
    @GetMapping("/")
    public String trangChu(Model model,
            @RequestParam(value = "sortField", defaultValue = "maSach") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {

        // Tạo đối tượng Sort dựa trên tham số truyền vào
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        // Lấy danh sách sách đã sắp xếp
        model.addAttribute("danhSachSach", sachRepository.findAll(sort));

        // Gửi lại tham số để hiển thị icon mũi tên trên HTML
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // Đổ dữ liệu cho Modal (Phải có dòng này thì Modal mới có tên để chọn)
        model.addAttribute("danhSachTacGia", tacGiaRepository.findAll());
        model.addAttribute("danhSachTheLoai", theLoaiRepository.findAll());
        model.addAttribute("danhSachNXB", nhaXuatBanRepository.findAll());

        return "sach/index";
    }

    @GetMapping("/sach")
    public String trangSach(Model model) {
        // Gọi hàm trangChu với các giá trị mặc định để tránh lỗi compile
        return trangChu(model, "maSach", "asc");
    }

    @GetMapping("/theloai")
    public String trangTheLoai() {
        return "theloai/index";
    }

    @GetMapping("/nxb")
    public String trangNhaXuatBan() {
        return "nhaxuatban/index"; // Trỏ đến file templates/nhaxuatban/index.html
    }

    @GetMapping("/tacgia")
    public String trangTacGia() {
        return "tacgia/index"; // Đường dẫn file HTML của bạn
    }
}