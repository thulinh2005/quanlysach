package com.hau.quanlysach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.hau.quanlysach.entity.NhaXuatBan;
import com.hau.quanlysach.repository.NhaXuatBanRepository;

@Service
public class NhaXuatBanService {
    @Autowired
    private NhaXuatBanRepository repo;

    public List<NhaXuatBan> getAll() {
        return repo.findAll();
    }

    public NhaXuatBan create(NhaXuatBan nxb) {
        return repo.save(nxb);
    }

    public NhaXuatBan update(Integer id, NhaXuatBan newNXB) {
        NhaXuatBan nxb = repo.findById(id).orElseThrow();
        nxb.setTenNxb(newNXB.getTenNxb());
        return repo.save(nxb);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}