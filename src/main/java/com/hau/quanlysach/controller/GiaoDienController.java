package com.hau.quanlysach.controller;

import com.hau.quanlysach.repository.NhaXuatBanRepository;
import com.hau.quanlysach.repository.SachRepository;
import com.hau.quanlysach.repository.TacGiaRepository;
import com.hau.quanlysach.repository.TheLoaiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String trangChu(Model model) {
        // Đổ dữ liệu bảng
        model.addAttribute("danhSachSach", sachRepository.findAll());

        // Đổ dữ liệu cho Modal (Phải có dòng này thì Modal mới có tên để chọn)
        model.addAttribute("danhSachTacGia", tacGiaRepository.findAll());
        model.addAttribute("danhSachTheLoai", theLoaiRepository.findAll());
        model.addAttribute("danhSachNXB", nhaXuatBanRepository.findAll());

        return "sach/index";
    }

    @GetMapping("/sach")
    public String trangSach(Model model) {
        // Để không phải viết lặp lại code, em chỉ cần gọi lại hàm trangChu là được
        return trangChu(model);
    }

    @GetMapping("/theloai")
    public String trangTheLoai() {
        return "theloai/index";
    }

    @GetMapping("/nxb")
    public String trangNhaXuatBan() {
        return "nhaxuatban/index"; // Trỏ đến file templates/nhaxuatban/index.html
    }
}