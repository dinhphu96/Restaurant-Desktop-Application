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
public class DatTruoc {

    private String sdt;
    private String maKH;
    private Date thoiGianDen;
    private Date thoiGianDi;
    private int maHD;
    private int soLuongKhach;

    public DatTruoc() {
    }

    public DatTruoc(String sdt, String maKH, Date thoiGianDen, Date thoiGianDi, int maHD, int soLuongKhach) {
        this.sdt = sdt;
        this.maKH = maKH;
        this.thoiGianDen = thoiGianDen;
        this.thoiGianDi = thoiGianDi;
        this.maHD = maHD;
        this.soLuongKhach = soLuongKhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(Date thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public Date getThoiGianDi() {
        return thoiGianDi;
    }

    public void setThoiGianDi(Date thoiGianDi) {
        this.thoiGianDi = thoiGianDi;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        this.soLuongKhach = soLuongKhach;
    }

}
