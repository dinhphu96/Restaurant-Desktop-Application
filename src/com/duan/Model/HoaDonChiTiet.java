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
public class HoaDonChiTiet {

    private int maHDCT;
    private int maMH;
    private int soLuong;
    private int maHD;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, int maMH, int soLuong, int maHD) {
        this.maHDCT = maHDCT;
        this.maMH = maMH;
        this.soLuong = soLuong;
        this.maHD = maHD;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public int getMaMH() {
        return maMH;
    }

    public void setMaMH(int maMH) {
        this.maMH = maMH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

}
