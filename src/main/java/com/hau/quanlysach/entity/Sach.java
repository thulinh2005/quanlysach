package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sach")
@Data
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach")
    private Integer maSach;

    @Column(name = "bia_sach")
    private String biaSach;

    @Column(name = "ten_sach")
    private String tenSach;

    @ManyToOne
    @JoinColumn(name = "ma_tac_gia")
    private TacGia tacGia;

    @ManyToOne
    @JoinColumn(name = "ma_the_loai")
    private TheLoai theLoai;

    @ManyToOne
    @JoinColumn(name = "ma_nxb")
    private NhaXuatBan nhaXuatBan;

    private Integer namXuatBan;
    private Integer soLuong;
}