/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.Model.LoaiMH;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author lenovo
 */
public class LoaiMHDAO extends DuanDAO<LoaiMH, String> {

    @Override
    public void insert(LoaiMH entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoaiMH entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoaiMH selectById(String id) {
         String sql = "SELECT * FROM LOAIMATHANG WHERE MaLoai=?";
        List<LoaiMH> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<LoaiMH> selectAll() {
        String sql = "SELECT * FROM LOAIMATHANG";
        return selectBySql(sql);
    }

    @Override
    protected List<LoaiMH> selectBySql(String sql, Object... args) {
        List<LoaiMH> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    LoaiMH entity = new LoaiMH();

                    entity.setMaLoai(rs.getString("MaLoai"));
                    entity.setTenLoai(rs.getString("TenLoai"));
                   

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

    public Object getmaLoai(Object tenLoai) {
        String sql = "select MaLoai from LOAIMATHANG where TenLoai like ?";

        try {
            Object vals = new Object();
            ResultSet rs = jdbcHelper.query(sql, tenLoai);

            while (rs.next()) {
                vals = rs.getObject("MaLoai");
            }
            rs.getStatement().getConnection().close();
            return vals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
