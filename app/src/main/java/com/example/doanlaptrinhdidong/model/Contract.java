package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Contract implements Serializable {
    String ID_HOPDONG,ID_PHONG,ID_KHACHHANG,TIENCOC,NGAYTHUE,NGAYTRA,_status;

    public String getID_HOPDONG() {
        return ID_HOPDONG;
    }

    public void setID_HOPDONG(String ID_HOPDONG) {
        this.ID_HOPDONG = ID_HOPDONG;
    }

    public String getID_PHONG() {
        return ID_PHONG;
    }

    public void setID_PHONG(String ID_PHONG) {
        this.ID_PHONG = ID_PHONG;
    }

    public String getID_KHACHHANG() {
        return ID_KHACHHANG;
    }

    public void setID_KHACHHANG(String ID_KHACHHANG) {
        this.ID_KHACHHANG = ID_KHACHHANG;
    }

    public String getTIENCOC() {
        return TIENCOC;
    }

    public void setTIENCOC(String TIENCOC) {
        this.TIENCOC = TIENCOC;
    }

    public String getNGAYTHUE() {
        return NGAYTHUE;
    }

    public void setNGAYTHUE(String NGAYTHUE) {
        this.NGAYTHUE = NGAYTHUE;
    }

    public String getNGAYTRA() {
        return NGAYTRA;
    }

    public void setNGAYTRA(String NGAYTRA) {
        this.NGAYTRA = NGAYTRA;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public Contract(String ID_HOPDONG, String ID_PHONG, String ID_KHACHHANG, String TIENCOC, String NGAYTHUE, String NGAYTRA, String _status) {
        this.ID_HOPDONG = ID_HOPDONG;
        this.ID_PHONG = ID_PHONG;
        this.ID_KHACHHANG = ID_KHACHHANG;
        this.TIENCOC = TIENCOC;
        this.NGAYTHUE = NGAYTHUE;
        this.NGAYTRA = NGAYTRA;
        this._status = _status;
    }
}
