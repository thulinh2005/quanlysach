package com.hau.quanlysach.repository;

import com.hau.quanlysach.entity.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocGiaRepository extends JpaRepository<DocGia, Long> {
    // JpaRepository đã có sẵn findAll(), save(), delete() nên không cần viết gì
    // thêm ở đây
}