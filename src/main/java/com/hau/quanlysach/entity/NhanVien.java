package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @Column(name = "ma_nv")
    private Long maNv;

    @Column(name = "ten_nv")
    private String tenNhanVien; // Đổi từ tenNv thành tenNhanVien

    // Thêm Getter thủ công để đảm bảo Thymeleaf luôn đọc được
    public String getTenNhanVien() {
        return tenNhanVien;
    }
}