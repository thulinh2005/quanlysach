package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "the_loai")
@Data
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_the_loai")
    private Integer maTheLoai;

    @Column(name = "ten_the_loai")
    private String tenTheLoai;
}