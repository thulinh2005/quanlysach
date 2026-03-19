package com.hau.quanlysach.controller;

import com.hau.quanlysach.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GiaoDienController {

    @Autowired
    private SachRepository sachRepository;

    @GetMapping("/")
    public String trangChu(Model model) {
        // Lấy toàn bộ sách từ DB và gửi sang file HTML
        model.addAttribute("danhSachSach", sachRepository.findAll());
        return "index"; // Nó sẽ tìm file index.html trong thư mục templates
    }
}