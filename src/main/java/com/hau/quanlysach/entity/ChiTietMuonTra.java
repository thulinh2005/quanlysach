package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chi_tiet_muon_tra")
public class ChiTietMuonTra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maCt;

    @ManyToOne
    @JoinColumn(name = "ma_muon")
    private MuonTra muonTra;

    @ManyToOne
    @JoinColumn(name = "ma_sach")
    private Sach sach;

    private Integer soLuong;

    public MuonTra getMuonTra() {
        return muonTra;
    }

    public Sach getSach() {
        return sach;
    }

    public Integer getSoLuong() {
        return soLuong;
    }
}