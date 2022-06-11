package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Customer implements Serializable {
    String ID_KHACHHANG,TENKHACHHANG,GIOITINH,DIACHI,CMND,SDT,HINH,_status;

    public Customer(String ID_KHACHHANG, String TENKHACHHANG, String GIOITINH, String DIACHI, String CMND, String SDT, String HINH, String _status) {
        this.ID_KHACHHANG = ID_KHACHHANG;
        this.TENKHACHHANG = TENKHACHHANG;
        this.GIOITINH = GIOITINH;
        this.DIACHI = DIACHI;
        this.CMND = CMND;
        this.SDT = SDT;
        this.HINH = HINH;
        this._status = _status;
    }

    public String getID_KHACHHANG() {
        return ID_KHACHHANG;
    }

    public void setID_KHACHHANG(String ID_KHACHHANG) {
        this.ID_KHACHHANG = ID_KHACHHANG;
    }

    public String getTENKHACHHANG() {
        return TENKHACHHANG;
    }

    public void setTENKHACHHANG(String TENKHACHHANG) {
        this.TENKHACHHANG = TENKHACHHANG;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHINH() {
        return HINH;
    }

    public void setHINH(String HINH) {
        this.HINH = HINH;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }
}
