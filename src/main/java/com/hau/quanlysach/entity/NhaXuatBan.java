package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nha_xuat_ban")
@Data
public class NhaXuatBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nxb")
    private Integer maNxb;

    @Column(name = "ten_nxb")
    private String tenNxb;
}