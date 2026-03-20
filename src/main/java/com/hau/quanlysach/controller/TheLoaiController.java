package com.hau.quanlysach.controller;

import java.util.List;
import com.hau.quanlysach.entity.TheLoai;
import com.hau.quanlysach.service.TheLoaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theloai")
@CrossOrigin("*")
public class TheLoaiController {

    @Autowired
    private TheLoaiService service;

    @GetMapping
    public List<TheLoai> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TheLoai create(@RequestBody TheLoai tl) {
        return service.create(tl);
    }

    @PutMapping("/{id}")
    public TheLoai update(@PathVariable Integer id, @RequestBody TheLoai tl) {
        return service.update(id, tl);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}