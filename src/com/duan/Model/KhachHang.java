/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Model;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class KhachHang {

    private Integer maKH;
    private String tenKH;
    private boolean gioiTinh;
    private String sdt;
    private String email;
    private Date ngayThamGia;
    private String ghiChu;

    public KhachHang() {
    }

    public KhachHang(Integer maKH, String tenKH, boolean gioiTinh, String sdt, String email, Date ngayThamGia, String ghiChu) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.ngayThamGia = ngayThamGia;
        this.ghiChu = ghiChu;
    }

    public KhachHang(String tenKH, boolean gioiTinh, String sdt, Date ngayThamGia) {
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngayThamGia = ngayThamGia;
    }

    

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return this.tenKH;
    }

}
