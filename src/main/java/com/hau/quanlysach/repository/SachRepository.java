package com.hau.quanlysach.repository;

import com.hau.quanlysach.entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SachRepository extends JpaRepository<Sach, Integer> {
    // Thêm dòng này: Đếm số lượng sách dựa vào mã tác giả
    long countByTacGiaMaTacGia(Integer maTacGia);

    // Thêm dòng này cho NXB
    long countByNhaXuatBanMaNxb(Integer maNxb);

    // Thêm dòng này cho Thể loại
    long countByTheLoaiMaTheLoai(Integer maTheLoai);
}
