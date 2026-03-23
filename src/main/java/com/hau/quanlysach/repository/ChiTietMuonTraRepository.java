package com.hau.quanlysach.repository;

import com.hau.quanlysach.entity.ChiTietMuonTra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ChiTietMuonTraRepository extends JpaRepository<ChiTietMuonTra, Long> {

    void deleteByMuonTra_MaMuon(Long maMuon);
}