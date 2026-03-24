package com.hau.quanlysach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.hau.quanlysach.entity.NhaXuatBan;
import com.hau.quanlysach.repository.NhaXuatBanRepository;
import com.hau.quanlysach.repository.SachRepository;

@Service
public class NhaXuatBanService {
    @Autowired
    private NhaXuatBanRepository repo;
    @Autowired
    private SachRepository sachRepo;

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
        // Kiểm tra ràng buộc
        long count = sachRepo.countByNhaXuatBanMaNxb(id);
        if (count > 0) {
            throw new RuntimeException(
                    "Không thể xóa! Nhà xuất bản này đang có " + count + " cuốn sách trong hệ thống.");
        }
        repo.deleteById(id);
    }
}