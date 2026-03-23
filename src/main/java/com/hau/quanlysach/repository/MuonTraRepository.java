package com.hau.quanlysach.repository;

import com.hau.quanlysach.entity.MuonTra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuonTraRepository extends JpaRepository<MuonTra, Long> {
}