package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "muon_tra")
public class MuonTra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maMuon;

    @ManyToOne
    @JoinColumn(name = "ma_doc_gia")
    private DocGia docGia;

    @ManyToOne
    @JoinColumn(name = "ma_nv")
    private NhanVien nhanVien;

    private LocalDate ngayMuon;
    private LocalDate ngayTra;
    private String trangThai;

    @OneToMany(mappedBy = "muonTra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<ChiTietMuonTra> chiTiets = new ArrayList<>();
}