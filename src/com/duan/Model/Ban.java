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
public class Ban {
    private int maBan;
    private int khuVuc;
    private boolean trangThai;

    public Ban() {
    }

    public Ban(int maBan, int soLuongGe, boolean trangThai) {
        this.maBan = maBan;
        this.khuVuc = soLuongGe;
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(int khuVuc) {
        this.khuVuc = khuVuc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
