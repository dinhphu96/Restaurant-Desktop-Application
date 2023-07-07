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
public class HoaDon {

    private int maHD;
    private int maKH;
    private Date thoiGian = new Date();
    private String thuNgan;
    private boolean trangThai;
    private int maBan;
    private float tongTien;

    public HoaDon() {
        thoiGian = new Date();
    }
    
    

    public HoaDon(int maHD, int maKH,Date thoiGian, String thuNgan, boolean trangThai, int maBan, float tongTien) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.thoiGian = thoiGian;
        this.thuNgan = thuNgan;
        this.trangThai = trangThai;
        this.maBan = maBan;
        this.tongTien = tongTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getThuNgan() {
        return thuNgan;
    }

    public void setThuNgan(String thuNgan) {
        this.thuNgan = thuNgan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

}
