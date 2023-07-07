/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.Model.DoanhThuService;
import com.duan.Model.MonUaThich;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ThongKeDAO {

    public List<DoanhThuService> getDoanhThuNam(String ngayFrom, String ngayTo) {
        String sql = "select year(ThoiGian) as Nam ,sum(TongTien) as Total, (sum(TongTien)*10)/100 as Thue, (dbo.fn_TongTienLaiTheoNam(year(ThoiGian))-(sum(TongTien)*10)/100) as Lai\n"
                + "                from HOADON\n"
                + "                where TrangThai = 1 and ThoiGian between ? and ?\n"
                + "                group by year(ThoiGian)";
        try {
            List<DoanhThuService> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, ngayFrom, ngayTo);
            while (rs.next()) {
                DoanhThuService dtt = new DoanhThuService();
                dtt.setNam(rs.getInt("Nam"));
                dtt.setThuNhap(rs.getFloat("Total"));
                dtt.setThue(rs.getFloat("Thue"));
                dtt.setLai(rs.getFloat("Lai"));
                list.add(dtt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DoanhThuService> getDoanhThuNgay(String ngayFrom, String ngayTo) {

        String ab = "select ThoiGian ,sum(TongTien) as Total, (sum(TongTien)*5)/100 as Thue, (dbo.fn_TongTienLaiTheoNgay(ThoiGian)-((sum(TongTien)*5)/100)) as Lai\n"
                + "                from HOADON\n"
                + "                where TrangThai = 1 and ThoiGian between ? and ?\n"
                + "                group by ThoiGian";
        try {
            List<DoanhThuService> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(ab, ngayFrom, ngayTo);
            while (rs.next()) {
                DoanhThuService dtt = new DoanhThuService();
                dtt.setThoiGian(rs.getDate("ThoiGian"));
                dtt.setThuNhap(rs.getFloat("Total"));
                dtt.setThue(rs.getFloat("Thue"));
                dtt.setLai(rs.getFloat("Lai"));
                list.add(dtt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MonUaThich> getTOP(String ngayFrom, String ngayTo) {

        String ab = "select TOP 3 HOADONCHITIET.MaMH,TenMH, sum(SoLuong) as Tong_SL, (sum(SoLuong)*100/(select sum(SoLuong) from HOADONCHITIET)) as tyLe from HOADONCHITIET \n"
                + "join HOADON on HOADON.MaHD = HOADONCHITIET.MaHD\n"
                + "join MATHANG on MATHANG.MaMH = HOADONCHITIET.MaMH\n"
                + "where TrangThai = 1 and ThoiGian between ? and ?\n"
                + "group by HOADONCHITIET.MaMH,TenMH\n"
                + "order by Tong_SL desc";
        try {
            List<MonUaThich> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(ab, ngayFrom, ngayTo);
            while (rs.next()) {
                MonUaThich dtt = new MonUaThich();
                dtt.setMaMH(rs.getString("MaMH"));
                dtt.setTenMH(rs.getString("TenMH"));
                dtt.setSoLuong(rs.getInt("Tong_SL"));
                dtt.setTyLe(rs.getInt("tyLe"));

                list.add(dtt);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
