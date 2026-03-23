package com.hau.quanlysach.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hau.quanlysach.repository.*;
import com.hau.quanlysach.entity.*;

@Service
public class MuonTraService {
    @Autowired
    private MuonTraRepository mtRepo;
    @Autowired
    private ChiTietMuonTraRepository ctRepo;
    @Autowired
    private DocGiaRepository dgRepo;
    @Autowired
    private SachRepository sRepo;
    @Autowired
    private NhanVienRepository nvRepo;

    public List<MuonTra> getAllMuonTra() {
        return mtRepo.findAll();
    }

    @Transactional
    public void savePhieuMuon(Long maDocGia, Long maNv, List<Integer> maSachs, List<Integer> soLuongs) {
        MuonTra mt = new MuonTra();
        mt.setDocGia(dgRepo.findById(maDocGia).orElse(null));
        mt.setNhanVien(nvRepo.findById(maNv).orElse(null));
        mt.setNgayMuon(LocalDate.now());
        mt.setTrangThai("DANG_MUON");

        for (int i = 0; i < maSachs.size(); i++) {
            ChiTietMuonTra ct = new ChiTietMuonTra();
            ct.setMuonTra(mt);
            ct.setSach(sRepo.findById(maSachs.get(i)).orElse(null));
            ct.setSoLuong(soLuongs.get(i));
            mt.getChiTiets().add(ct);
        }
        mtRepo.save(mt);
    }

    @Transactional
    public void updatePhieuMuon(Long maMuon, Long maNv, String trangThai, List<Integer> maSachs,
            List<Integer> soLuongs) {
        MuonTra mt = mtRepo.findById(maMuon).orElse(null);
        if (mt != null) {
            mt.setNhanVien(nvRepo.findById(maNv).orElse(null));
            mt.setTrangThai(trangThai);
            mt.setNgayTra("DA_TRA".equals(trangThai) ? LocalDate.now() : null);

            // Xóa chi tiết cũ và thêm mới
            mt.getChiTiets().clear();
            for (int i = 0; i < maSachs.size(); i++) {
                ChiTietMuonTra ct = new ChiTietMuonTra();
                ct.setMuonTra(mt);
                ct.setSach(sRepo.findById(maSachs.get(i)).orElse(null));
                ct.setSoLuong(soLuongs.get(i));
                mt.getChiTiets().add(ct);
            }
            mtRepo.save(mt);
        }
    }

    @Transactional
    public void deletePhieuMuon(Long id) {
        mtRepo.deleteById(id);
    }

    @Transactional
    public void traSach(Long id) {
        MuonTra mt = mtRepo.findById(id).orElse(null);
        if (mt != null) {
            mt.setTrangThai("DA_TRA");
            mt.setNgayTra(LocalDate.now());
            mtRepo.save(mt);
        }
    }
}