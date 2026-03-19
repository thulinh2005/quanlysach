package com.hau.quanlysach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.hau.quanlysach.entity.Sach;
import com.hau.quanlysach.repository.SachRepository;

@Service
public class SachService {

    @Autowired
    private SachRepository sachRepository;

    public List<Sach> getAll() {
        return sachRepository.findAll();
    }

    public Sach save(Sach sach) {
        return sachRepository.save(sach);
    }
}
