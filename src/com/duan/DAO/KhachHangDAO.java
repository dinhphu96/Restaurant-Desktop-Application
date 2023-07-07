package com.duan.DAO;

import com.duan.Model.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class KhachHangDAO extends DuanDAO<KhachHang, Integer> {

    @Override
    public void insert(KhachHang entity) {
        String sql = "INSERT INTO KHACHHANG (TenKH, GioiTinh, Sdt, Email, NgayThamGia, GhiChu) VALUES (?,?,?,?,?,?)";
        jdbcHelper.update(sql,
                entity.getTenKH(),
                entity.isGioiTinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getNgayThamGia(),
                entity.getGhiChu());
    }

    @Override
    public void update(KhachHang entity) {
        String sql = "UPDATE KHACHHANG SET TenKH=?, GioiTinh=?, Sdt=?, Email=?, NgayThamGia=?, GhiChu=? where MaKH=?";
        jdbcHelper.update(sql,
                entity.getTenKH(),
                entity.isGioiTinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getNgayThamGia(),
                entity.getGhiChu(),
                entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM KHACHHANG WHERE MaKH=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public KhachHang selectById(Integer id) {
        String sql = "SELECT * FROM KHACHHANG WHERE MaKH=?";
        List<KhachHang> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }   

    public List<KhachHang> selectByTenKH(String ten) {
        String sql = "SELECT * FROM KHACHHANG WHERE TenKH like N'%"+ten+"%'";
        return this.selectBySql(sql);
    }
    
    public List<KhachHang> timKiemKH(String nameOrSdt) {
        String sql = "SELECT * FROM KHACHHANG WHERE Sdt like '%"+nameOrSdt+"%' or TenKH like N'%"+nameOrSdt+"%'";
        return selectBySql(sql);
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KHACHHANG";
        return selectBySql(sql);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {

                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    KhachHang entity = new KhachHang();

                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setTenKH(rs.getString("TenKH"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setSdt(rs.getString("Sdt"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setNgayThamGia(rs.getDate("NgayThamGia"));
                    entity.setGhiChu(rs.getString("GhiChu"));

                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
