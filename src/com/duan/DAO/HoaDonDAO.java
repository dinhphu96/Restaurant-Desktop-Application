/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.Model.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author lenovo
 */
public class HoaDonDAO extends DuanDAO<HoaDon, Integer> {

    @Override
    public void insert(HoaDon entity) {
        String sql = "INSERT INTO HOADON (MaKH, ThoiGian, ThuNgan, TrangThai, MaBan, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql,
                //entity.getMaHD(),
                entity.getMaKH(),
                entity.getThoiGian(),
                entity.getThuNgan(),
                entity.isTrangThai(),
                entity.getMaBan(),
                entity.getTongTien());
    }

    public void insertKhachLe(HoaDon entity) {
        String sql = "INSERT INTO HOADON (MaKH, ThoiGian, ThuNgan, TrangThai, MaBan, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql,
                //entity.getMaHD(),
                null,
                entity.getThoiGian(),
                entity.getThuNgan(),
                entity.isTrangThai(),
                entity.getMaBan(),
                entity.getTongTien());
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HOADON SET MaKH=?, ThoiGian=?, ThuNgan=?, TrangThai=?, MaBan=?, TongTien=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                entity.getMaKH(),
                entity.getThoiGian(),
                entity.getThuNgan(),
                entity.isTrangThai(),
                entity.getMaBan(),
                entity.getTongTien(),
                entity.getMaHD());
    }

    public void updateThanhToan(HoaDon entity) {
        String sql = "UPDATE HOADON SET TrangThai=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                entity.isTrangThai(),
                entity.getMaHD());
    }

    public void updateMaBan(int maBan, int maHD) {
        String sql = "UPDATE HOADON SET MaBan=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                maBan,
                maHD);
    }
    
    public void updateMakhach(HoaDon entity) {
        String sql = "UPDATE HOADON SET MaKH=?, TongTien=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                entity.getMaKH(),
                entity.getTongTien(),
                entity.getMaHD());
    }

    public void updateMakhachNull(HoaDon entity) {
        String sql = "UPDATE HOADON SET MaKH=?, TongTien=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                null,
                entity.getTongTien(),
                entity.getMaHD());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM HOADON WHERE MaHD=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public HoaDon selectById(Integer id) {
        String sql = "SELECT * FROM HOADON WHERE MaHD=?";
        List<HoaDon> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM HOADON";
        return selectBySql(sql);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    HoaDon entity = new HoaDon();

                    entity.setMaHD(rs.getInt("MaHD"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setThoiGian(rs.getDate("ThoiGian"));
                    entity.setThuNgan(rs.getString("ThuNgan"));
                    entity.setTrangThai(rs.getBoolean("TrangThai"));
                    entity.setMaBan(rs.getInt("MaBan"));
                    entity.setTongTien(rs.getFloat("TongTien"));

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

    public List<HoaDon> hoaDonTheoMaKH(Integer maKH) {
        String sql = "SELECT * FROM HOADON where MaKH = ?";
        List<HoaDon> list = selectBySql(sql, maKH);
        return list;
    }

    public List<HoaDon> selectByTrangThai(int tt) {
        String sql = "SELECT * FROM HOADON WHERE TrangThai = ?";
        List<HoaDon> list = selectBySql(sql, tt);
        return list.size() > 0 ? list : null;
    }
    
    public List<HoaDon> hoaDonChuaThanhToan() {
        String sql = "SELECT * FROM HOADON where TrangThai = 0";
        return selectBySql(sql);
    }
}
