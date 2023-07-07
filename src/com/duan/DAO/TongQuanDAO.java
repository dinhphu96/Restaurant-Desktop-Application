/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.Model.TongQuan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TongQuanDAO {    
    public List<TongQuan> getTongQuan(String ngayFrom, String ngayTo) {
        String sql = "select Count(*) SLKH,\n" +
"		Count(MaHD) SLMaHD,\n" +
"		ROUND((ISNULL(cast((select count(HOADONCHITIET.MaMH) from HOADONCHITIET) as float) / NULLIF(( COUNT(HOADON.MaHD)),0),0)),2) TBMHHD,\n" +
"		ROUND(ISNULL((sum(TongTien) / NULLIF(Count(MaHD),0)),0) ,2) TBDTHD\n" +
"from HOADON \n" +
"where ThoiGian between ? and ? and TrangThai = 1";
        try {
            List<TongQuan> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, ngayFrom,ngayTo);
            if(rs.getRow() == 0){
                
            }
            while (rs.next()) {
                TongQuan tq = new TongQuan();
                tq.setSoKhachHang(rs.getInt("SLKH"));
                tq.setSoHoaDon(rs.getInt("SLMaHD"));
                tq.setTbMatHangHoaDon(rs.getFloat("TBMHHD"));
                tq.setTbDoanhThuHoaDon(rs.getFloat("TBDTHD"));
                list.add(tq);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
    }
    
//    public List<DoanhThuService> getDoanhThuNgay(String ngayFrom, String ngayTo) {
//
//        String ab = "select year(ThoiGian) as Nam ,sum(TongTien) as Total, (sum(TongTien)*5)/100 as Thue, dbo.fn_TongTienLaiTheoNam(year(ThoiGian)) as Lai\n" +
//"                from HOADON\n" +
//"                where TrangThai = 1 and ThoiGian between ? and ?\n" +
//"                group by year(ThoiGian)";
//        try {
//            List<DoanhThuService> list = new ArrayList<>();
//            ResultSet rs = jdbcHelper.query(ab, ngayFrom, ngayTo);
//            while (rs.next()) {
//                DoanhThuService dtt = new DoanhThuService();
//                dtt.setThangDate(rs.getString("Nam"));
//                dtt.setThuNhap(rs.getFloat("Total"));
//                dtt.setThue(rs.getFloat("Thue"));
//                dtt.setLai(rs.getFloat("Lai"));
//                list.add(dtt);
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
