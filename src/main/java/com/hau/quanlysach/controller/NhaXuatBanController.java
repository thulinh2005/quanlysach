package com.hau.quanlysach.controller;

import java.util.List;
import com.hau.quanlysach.entity.NhaXuatBan;
import com.hau.quanlysach.service.NhaXuatBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nxb")
@CrossOrigin("*")
public class NhaXuatBanController {
    @Autowired
    private NhaXuatBanService service;

    @GetMapping
    public List<NhaXuatBan> getAll() {
        return service.getAll();
    }

    @PostMapping
    public NhaXuatBan create(@RequestBody NhaXuatBan nxb) {
        return service.create(nxb);
    }

    @PutMapping("/{id}")
    public NhaXuatBan update(@PathVariable Integer id, @RequestBody NhaXuatBan nxb) {
        return service.update(id, nxb);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().body("Xóa thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}