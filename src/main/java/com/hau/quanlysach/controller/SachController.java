package com.hau.quanlysach.controller;

import com.hau.quanlysach.entity.Sach;
import com.hau.quanlysach.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sach")
public class SachController {

    @Autowired
    private SachRepository sachRepository;

    // Lấy danh sách toàn bộ sách
    @GetMapping
    public List<Sach> getAllSach() {
        return sachRepository.findAll();
    }

    // Lưu một cuốn sách mới
    @PostMapping("/them")
    public Sach createSach(@RequestBody Sach sach) {
        return sachRepository.save(sach);
    }
}