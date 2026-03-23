package com.hau.quanlysach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hau.quanlysach.repository.*;
import com.hau.quanlysach.service.MuonTraService;
import java.util.List;

@Controller
@RequestMapping("/muontra")
public class MuonTraController {

    @Autowired
    private MuonTraService muonTraService;
    @Autowired
    private DocGiaRepository docGiaRepository;
    @Autowired
    private SachRepository sachRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("")
    public String index(Model model) {
        // PHẢI ĐỔI TÊN BIẾN THÀNH danhSachMuonTra ĐỂ KHỚP VỚI HTML MỚI
        model.addAttribute("danhSachMuonTra", muonTraService.getAllMuonTra());
        model.addAttribute("danhSachDocGia", docGiaRepository.findAll());
        model.addAttribute("danhSachSach", sachRepository.findAll());
        model.addAttribute("danhSachNhanVien", nhanVienRepository.findAll());
        return "muontra/index";
    }

    @PostMapping("/them")
    public String themPhieuMuon(@RequestParam Long maDocGia,
            @RequestParam Long maNv,
            @RequestParam("maSachs[]") List<Integer> maSachs,
            @RequestParam("soLuongs[]") List<Integer> soLuongs) {
        muonTraService.savePhieuMuon(maDocGia, maNv, maSachs, soLuongs);
        return "redirect:/muontra";
    }

    @PostMapping("/sua")
    public String suaPhieuMuon(@RequestParam Long maMuon,
            @RequestParam Long maNv,
            @RequestParam String trangThai,
            @RequestParam("maSachs[]") List<Integer> maSachs,
            @RequestParam("soLuongs[]") List<Integer> soLuongs) {
        muonTraService.updatePhieuMuon(maMuon, maNv, trangThai, maSachs, soLuongs);
        return "redirect:/muontra";
    }

    @GetMapping("/tra-sach/{id}")
    public String traSach(@PathVariable Long id) {
        muonTraService.traSach(id);
        return "redirect:/muontra";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id) {
        muonTraService.deletePhieuMuon(id);
        return "redirect:/muontra";
    }
}