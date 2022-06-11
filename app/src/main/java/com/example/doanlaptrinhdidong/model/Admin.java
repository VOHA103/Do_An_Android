package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Admin implements Serializable {
    String TAIKHOAN,MATKHAU,PHANQUYEN;

    public Admin(String TAIKHOAN, String MATKHAU, String PHANQUYEN) {
        this.TAIKHOAN = TAIKHOAN;
        this.MATKHAU = MATKHAU;
        this.PHANQUYEN = PHANQUYEN;
    }

    public String getTAIKHOAN() {
        return TAIKHOAN;
    }

    public void setTAIKHOAN(String TAIKHOAN) {
        this.TAIKHOAN = TAIKHOAN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getPHANQUYEN() {
        return PHANQUYEN;
    }

    public void setPHANQUYEN(String PHANQUYEN) {
        this.PHANQUYEN = PHANQUYEN;
    }
}
