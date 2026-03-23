package com.hau.quanlysach.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doc_gia")
public class DocGia {

    @Id
    @Column(name = "ma_doc_gia")
    private Long maDocGia;

    @Column(name = "ten_doc_gia")
    private String tenDocGia;

    public String getTenDocGia() {
        return tenDocGia;
    }
}