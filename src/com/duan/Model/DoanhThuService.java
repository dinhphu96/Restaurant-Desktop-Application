/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.Model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DoanhThuService {
    private Date thoiGian;
    private Integer nam;
    private Float thuNhap;
    private Float thue;
    private Float lai;

    public DoanhThuService() {
    }

    public DoanhThuService(Date thoiGian, Float thuNhap, Float thue, Float lai) {
        this.thoiGian = thoiGian;
        this.thuNhap = thuNhap;
        this.thue = thue;
        this.lai = lai;
    }

    public DoanhThuService(Integer nam, Float thuNhap, Float thue, Float lai) {
        this.nam = nam;
        this.thuNhap = thuNhap;
        this.thue = thue;
        this.lai = lai;
    }
    
    

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }
    
    

    public Float getLai() {
        return lai;
    }

    public void setLai(Float lai) {
        this.lai = lai;
    }

    

    public Float getThue() {
        return thue;
    }

    public void setThue(Float thue) {
        this.thue = thue;
    }

    

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Float getThuNhap() {
        return thuNhap;
    }

    public void setThuNhap(Float thuNhap) {
        this.thuNhap = thuNhap;
    }

    
    
}
