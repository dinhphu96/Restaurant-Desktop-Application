package com.duan.DAO;

import com.duan.Model.Ban;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class BanDAO extends DuanDAO<Ban, Integer>{

    @Override
    public void insert(Ban entity) {
        String sql = "INSERT INTO BAN  VALUES (?, ?, ?)";
        jdbcHelper.update(sql,
                entity.getMaBan(),
                entity.getKhuVuc(),
                entity.isTrangThai());
    }

    @Override
    public void update(Ban entity) {
        String sql = "UPDATE BAN SET KhuVuc=?, TrangThai=? WHERE MaBan=?";
        jdbcHelper.update(sql,
                entity.getKhuVuc(),
                entity.isTrangThai(),
                entity.getMaBan());
    }

    @Override
    public void delete(Integer maBan) {
        String sql = "DELETE FROM BAN WHERE MaBan=?";
        jdbcHelper.update(sql, maBan);
    }

    @Override
    public Ban selectById(Integer maBan ){
        String sql = "SELECT * FROM BAN WHERE MaBan=?";
        List<Ban> list = selectBySql(sql, maBan);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Ban> selectAll() {
        String sql = "SELECT * FROM BAN";
        return selectBySql(sql);
    }

    public List<Ban> selectBanCoKhach() {
        String sql = "SELECT * FROM BAN WHERE TrangThai = 1";
        return selectBySql(sql);
    }
    
    @Override
    protected List<Ban> selectBySql(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    Ban entity = new Ban();
                    
                    entity.setMaBan(rs.getInt("MaBan"));
                    entity.setKhuVuc(rs.getInt("KhuVuc"));
                    entity.setTrangThai(rs.getBoolean("TrangThai"));
     
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
