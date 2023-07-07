
package com.duan.DAO;

import com.duan.Model.MatHang;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MatHangDAO extends DuanDAO<MatHang, String> {

    @Override
    public void insert(MatHang entity) {
        String sql = "INSERT INTO MATHANG(TenMH, DonVi, GiaBan, GiaGoc, MaLoai, Hinh) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql,
                entity.getTenMH(),
                entity.getDonVi(),
                entity.getGiaBan(),
                entity.getGiaGoc(),
                entity.getMaLoai(),
                entity.getHinh());
    }

    @Override
    public void update(MatHang entity) {
        String sql = "UPDATE MATHANG SET TenMH=?, DonVi=?, GiaBan=?, GiaGoc=?, MaLoai=?, Hinh=? WHERE MaMH=?";
        jdbcHelper.update(sql,
                entity.getTenMH(),
                entity.getDonVi(),
                entity.getGiaBan(),
                entity.getGiaGoc(),
                entity.getMaLoai(),
                entity.getHinh(),
                entity.getMaMH()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM MATHANG WHERE MaMH=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public MatHang selectById(String id) {
        String sql = "SELECT * FROM MATHANG WHERE MaMH=?";
        List<MatHang> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<MatHang> selectAll() {
        String sql = " SELECT * FROM MATHANG ";
        return selectBySql(sql);
    }

    @Override
    protected List<MatHang> selectBySql(String sql, Object... args) {
        List<MatHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    MatHang entity = new MatHang();

                    entity.setMaMH(rs.getInt("MaMH"));
                    entity.setTenMH(rs.getString("TenMH"));
                    entity.setDonVi(rs.getString("DonVi"));
                    entity.setGiaBan(rs.getInt("GiaBan"));
                    entity.setGiaGoc(rs.getInt("GiaGoc"));
                    entity.setMaLoai(rs.getString("MaLoai"));
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
    
    

    public List<MatHang> getmaMH(String tenMH) {
        String sql = "select * From MATHANG \n"
                + "join LOAIMATHANG on MATHANG.MaLoai  = LOAIMATHANG.MaLoai\n"
                + "where LOAIMATHANG.TenLoai like N'" + tenMH + "'";
        return selectBySql(sql);
    }

    public List<MatHang> TimMon(String tenMon) {
        String sql = "select * From MATHANG \n"
                + "where TenMH like N'" + tenMon + "'";
        return selectBySql(sql);
    }
    
    public int getmaMH2(String ten) {
        String sql = "select MaMH from MATHANG where TenMH = ?";

        try {
            int vals = 0;
            ResultSet rs = jdbcHelper.query(sql, ten);

            while (rs.next()) {
                vals = rs.getInt("MaMH");
            }
            rs.getStatement().getConnection().close();
            return vals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGiaMH(int ma) {
        String sql = "select GiaBan from MATHANG where MaMH = ?";

        try {
            int vals = 0;
            ResultSet rs = jdbcHelper.query(sql, ma);

            while (rs.next()) {
                vals = rs.getInt("GiaBan");
            }
            rs.getStatement().getConnection().close();
            return vals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTenMH(int ma) {
        String sql = "select TenMH from MATHANG where MaMH = ?";

        try {
            String vals = null;
            ResultSet rs = jdbcHelper.query(sql, ma);

            while (rs.next()) {
                vals = rs.getString("TenMH");
            }
            rs.getStatement().getConnection().close();
            return vals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // show ra bảng
    public List<MatHang> ShowTable() {
        String sql = "select MaMH, TenMH, DonVi, GiaBan, GiaGoc, MATHANG.MaLoai, LOAIMATHANG.TenLoai,"
                + " Hinh from mathang join LOAIMATHANG on MATHANG.MaLoai = LOAIMATHANG.MaLoai";
        return selectBySqls(sql);
    }
    
    protected List<MatHang> selectBySqls(String sql, Object... args) {
        List<MatHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    MatHang entity = new MatHang();

                    entity.setMaMH(rs.getInt("MaMH"));
                    entity.setTenMH(rs.getString("TenMH"));
                    entity.setDonVi(rs.getString("DonVi"));
                    entity.setGiaBan(rs.getInt("GiaBan"));
                    entity.setGiaGoc(rs.getInt("GiaGoc"));
                    entity.setMaLoai(rs.getString("MaLoai"));
                    entity.setTenLoai(rs.getString("TenLoai"));
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
