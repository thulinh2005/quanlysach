package com.hau.quanlysach.controller;

import com.hau.quanlysach.entity.TacGia;
import com.hau.quanlysach.service.TacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tacgia")
@CrossOrigin("*")
public class TacGiaController {
    @Autowired
    private TacGiaService service;

    @GetMapping
    public List<TacGia> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TacGia create(@RequestBody TacGia tg) {
        return service.create(tg);
    }

    @PutMapping("/{id}")
    public TacGia update(@PathVariable Integer id, @RequestBody TacGia tg) {
        return service.update(id, tg);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}