/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Model;

/**
 *
 * @author lenovo
 */
public class ChonMon {

    private String tenMon;
    private int soLuong;
    private String donVi;
    private int gia;

    public ChonMon() {
    }

    public ChonMon(String tenMon, int soLuong, String donVi, int gia) {
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.gia = gia;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

}
