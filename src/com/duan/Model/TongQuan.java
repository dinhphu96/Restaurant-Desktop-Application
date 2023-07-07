/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Model;

/**
 *
 * @author Administrator
 */
public class TongQuan {
    private int soKhachHang;
    private int soHoaDon;
    private float tbMatHangHoaDon;
    private float tbDoanhThuHoaDon;

    public TongQuan() {
    }

    public TongQuan(int soKhachHang, int soHoaDon, float tbMatHangHoaDon, float tbDoanhThuHoaDon) {
        this.soKhachHang = soKhachHang;
        this.soHoaDon = soHoaDon;
        this.tbMatHangHoaDon = tbMatHangHoaDon;
        this.tbDoanhThuHoaDon = tbDoanhThuHoaDon;
    }

    public int getSoKhachHang() {
        return soKhachHang;
    }

    public void setSoKhachHang(int soKhachHang) {
        this.soKhachHang = soKhachHang;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public float getTbMatHangHoaDon() {
        return tbMatHangHoaDon;
    }

    public void setTbMatHangHoaDon(float tbMatHangHoaDon) {
        this.tbMatHangHoaDon = tbMatHangHoaDon;
    }

    public float getTbDoanhThuHoaDon() {
        return tbDoanhThuHoaDon;
    }

    public void setTbDoanhThuHoaDon(float tbDoanhThuHoaDon) {
        this.tbDoanhThuHoaDon = tbDoanhThuHoaDon;
    }
    
    
}
