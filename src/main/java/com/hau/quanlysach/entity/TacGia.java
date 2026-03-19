package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tac_gia")
@Data
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tac_gia")
    private Integer maTacGia;

    @Column(name = "ten_tac_gia")
    private String tenTacGia;
}