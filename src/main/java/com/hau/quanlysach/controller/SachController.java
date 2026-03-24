package com.hau.quanlysach.controller;

import com.hau.quanlysach.entity.Sach;
import com.hau.quanlysach.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Controller // Dùng @Controller để có thể chuyển hướng (redirect) trang giao diện
@RequestMapping("/api/sach")
public class SachController {

    @Autowired
    private SachRepository sachRepository;

    /**
     * Chức năng: Lưu một cuốn sách mới từ Modal
     * Sau khi lưu xong sẽ quay trở lại trang danh sách
     */

    @PostMapping("/them")
    public String createSach(@ModelAttribute Sach sach, @RequestParam("fileAnh") MultipartFile fileAnh) {
        // 1. Nếu là thêm mới, đảm bảo maSach là null để tránh ghi đè lung tung
        sach.setMaSach(null);

        if (fileAnh != null && !fileAnh.isEmpty()) {
            try {
                String fileName = fileAnh.getOriginalFilename();
                // Dùng đường dẫn này để VS Code dễ nhận diện file hơn
                String uploadDir = "src/main/resources/static/css/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(fileAnh.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // 2. Gán tên file vào đối tượng
                sach.setBiaSach(fileName);
                System.out.println("DEBUG: Da gán ten file: " + fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 3. Lưu xuống Database
        sachRepository.save(sach);

        // 4. Redirect về đúng trang chủ (Nếu em dùng "/" thì để "/", nếu dùng
        // "/quan-ly-sach" thì sửa lại nhé)
        return "redirect:/sach";
    }

    /**
     * Chức năng: Xóa sách theo Mã sách
     * Link gọi: /api/sach/xoa/{id}
     */
    @GetMapping("/xoa/{id}")
    public String deleteSach(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            if (sachRepository.existsById(id)) {
                sachRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("message", "Đã xóa sách thành công!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa cuốn sách này.");
        }
        return "redirect:/sach";
    }

    @PostMapping("/sua")
    public String updateSach(@ModelAttribute Sach sach,
            @RequestParam(value = "fileAnh", required = false) MultipartFile fileAnh) {
        try {
            // 1. Tìm bản ghi cũ trong DB
            Sach existingSach = sachRepository.findById(sach.getMaSach()).orElse(null);

            if (existingSach != null) {
                // 2. Cập nhật các trường thông tin từ Form
                existingSach.setTenSach(sach.getTenSach());
                existingSach.setTacGia(sach.getTacGia());
                existingSach.setTheLoai(sach.getTheLoai());
                existingSach.setNamXuatBan(sach.getNamXuatBan());
                existingSach.setSoLuong(sach.getSoLuong());
                // Nếu em có thêm Nhà xuất bản trong form sửa thì thêm dòng dưới:
                // existingSach.setNhaXuatBan(sach.getNhaXuatBan());

                // 3. Xử lý ảnh bìa
                if (fileAnh != null && !fileAnh.isEmpty()) {
                    String fileName = fileAnh.getOriginalFilename();
                    String uploadDir = "src/main/resources/static/images/";
                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath))
                        Files.createDirectories(uploadPath);

                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(fileAnh.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    existingSach.setBiaSach(fileName); // Cập nhật ảnh mới
                }
                // Nếu không chọn ảnh mới, existingSach vẫn giữ nguyên biaSach cũ

                // 4. Lưu đối tượng đã cập nhật
                sachRepository.save(existingSach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}