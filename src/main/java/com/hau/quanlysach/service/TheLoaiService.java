package com.hau.quanlysach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.hau.quanlysach.entity.TheLoai;
import com.hau.quanlysach.repository.TheLoaiRepository;

@Service
public class TheLoaiService {

    @Autowired
    private TheLoaiRepository repo;

    public List<TheLoai> getAll() {
        return repo.findAll();
    }

    public TheLoai create(TheLoai tl) {
        return repo.save(tl);
    }

    public TheLoai update(Integer id, TheLoai newTL) {
        TheLoai tl = repo.findById(id).orElseThrow();
        tl.setTenTheLoai(newTL.getTenTheLoai());
        return repo.save(tl);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}