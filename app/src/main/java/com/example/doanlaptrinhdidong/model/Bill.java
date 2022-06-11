package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Bill implements Serializable {
    String ID_HOADON;
    String ID_PHONG;
    String TONGTIEN;
    String Thang_TToan;
    String _status;

    public String getID_HOADON() {
        return ID_HOADON;
    }

    public void setID_HOADON(String ID_HOADON) {
        this.ID_HOADON = ID_HOADON;
    }

    public String getID_PHONG() {
        return ID_PHONG;
    }

    public void setID_PHONG(String ID_PHONG) {
        this.ID_PHONG = ID_PHONG;
    }

    public String getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(String TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public String getThang_TToan() {
        return Thang_TToan;
    }

    public void setThang_TToan(String thang_TToan) {
        Thang_TToan = thang_TToan;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public Bill(String ID_HOADON, String ID_PHONG, String TONGTIEN, String thang_TToan, String _status) {
        this.ID_HOADON = ID_HOADON;
        this.ID_PHONG = ID_PHONG;
        this.TONGTIEN = TONGTIEN;
        Thang_TToan = thang_TToan;
        this._status = _status;
    }

}
