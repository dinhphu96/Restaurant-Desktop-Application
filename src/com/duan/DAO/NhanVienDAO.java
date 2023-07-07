/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.Model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author lenovo
 */
public class NhanVienDAO extends DuanDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien entity) {
        String sql = "INSERT INTO NHANVIEN VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql,
                entity.getMaNV(),
                entity.getHoTenNV(),
                entity.getMatKhau(),
                entity.getNgaySinh(),
                entity.isGioiTinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getNgayBatDau(),
                entity.getVaiTro(),
                entity.getHinh());
    }

    @Override
    public void update(NhanVien entity) {
        String sql = "UPDATE NHANVIEN SET HoTenNV=?, MatKhau=?, NgaySinh=?, GioiTinh=?, Sdt=? , Email=? , DiaChi=? , NgayBatDau=? , VaiTro=? , Hinh=? WHERE MaNV=?";
        jdbcHelper.update(sql,
                entity.getHoTenNV(),
                entity.getMatKhau(),
                entity.getNgaySinh(),
                entity.isGioiTinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getNgayBatDau(),
                entity.getVaiTro(),
                entity.getHinh(),
                entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM NHANVIEN WHERE MaNV=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public NhanVien selectById(String id) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";
        List<NhanVien> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NHANVIEN";
        return selectBySql(sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();

                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTenNV(rs.getString("HoTenNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setSdt(rs.getString("Sdt"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setDiaChi(rs.getString("DiaChi"));
                    entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                    entity.setVaiTro(rs.getString("VaiTro"));
                    entity.setHinh(rs.getString("Hinh"));

                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
