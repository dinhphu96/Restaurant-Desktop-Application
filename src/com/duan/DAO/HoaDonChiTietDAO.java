
package com.duan.DAO;

import com.duan.Model.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class HoaDonChiTietDAO extends DuanDAO<HoaDonChiTiet, String> {

    @Override
    public void insert(HoaDonChiTiet entity) {
        String sql = "INSERT INTO HOADONCHITIET (MaMH, SoLuong, MaHD) VALUES (?, ?, ?)";
        jdbcHelper.update(sql,
                //entity.getMaMH(),
                entity.getMaMH(),
                entity.getSoLuong(),
                entity.getMaHD());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        String sql = "UPDATE HOADONCHITIET SET MaMH=?, SoLuong=?, MaHD=? WHERE MaHDCT=?";
        jdbcHelper.update(sql,
                entity.getMaMH(),
                entity.getSoLuong(),
                entity.getMaHD(),
                entity.getMaHDCT()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM HOADONCHITIET WHERE MaHDCT=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public HoaDonChiTiet selectById(String id) {
        String sql = "SELECT * FROM HOADONCHITIET WHERE MaHDCT=?";
        List<HoaDonChiTiet> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<HoaDonChiTiet> selectByMaHD(int id) {
        String sql = "SELECT * FROM HOADONCHITIET WHERE MaHD=?";
        List<HoaDonChiTiet> list = selectBySql(sql, id);
        return list.size() > 0 ? list : null;
    }
    

    @Override
    public List<HoaDonChiTiet> selectAll() {
        String sql = "SELECT * FROM HOADONCHITIET";
        return selectBySql(sql);
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    HoaDonChiTiet entity = new HoaDonChiTiet();

                    entity.setMaHDCT(rs.getInt("MaHDCT"));
                    entity.setMaMH(rs.getInt("MaMH"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    entity.setMaHD(rs.getInt("MaHD"));

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
    
    
    
    public void deleteAllByMaHD(int maHD) {
        String sql = "DELETE FROM HOADONCHITIET WHERE MaHD=?";
        jdbcHelper.update(sql, maHD);
    }

}
