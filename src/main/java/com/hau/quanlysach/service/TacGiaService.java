package com.hau.quanlysach.service;

import com.hau.quanlysach.entity.TacGia;
import com.hau.quanlysach.repository.SachRepository;
import com.hau.quanlysach.repository.TacGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TacGiaService {
    @Autowired
    private TacGiaRepository repo;
    @Autowired
    private SachRepository sachRepo;

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
        // 1. Kiểm tra xem tác giả có đang dính líu đến cuốn sách nào không
        long count = sachRepo.countByTacGiaMaTacGia(id);

        if (count > 0) {
            // 2. Nếu có, ném ra một thông báo lỗi
            throw new RuntimeException("Không thể xóa! Tác giả này đang có " + count + " cuốn sách trong thư viện.");
        }

        // 3. Nếu không có sách thì mới tiến hành xóa
        repo.deleteById(id);
    }
}