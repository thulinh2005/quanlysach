package com.hau.quanlysach.service;

import com.hau.quanlysach.entity.TacGia;
import com.hau.quanlysach.repository.TacGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TacGiaService {
    @Autowired
    private TacGiaRepository repo;

    public List<TacGia> getAll() {
        return repo.findAll();
    }

    public TacGia create(TacGia tg) {
        return repo.save(tg);
    }

    public TacGia update(Integer id, TacGia newTG) {
        TacGia tg = repo.findById(id).orElseThrow();
        tg.setTenTacGia(newTG.getTenTacGia());
        return repo.save(tg);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}