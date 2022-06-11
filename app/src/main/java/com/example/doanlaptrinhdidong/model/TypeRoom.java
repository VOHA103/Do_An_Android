package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class TypeRoom implements Serializable {
    String ID_LOAIPHONG,TENLOAI,GIAPHONG,_status;

    public String getID_LOAIPHONG() {
        return ID_LOAIPHONG;
    }

    public void setID_LOAIPHONG(String ID_LOAIPHONG) {
        this.ID_LOAIPHONG = ID_LOAIPHONG;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }

    public String getGIAPHONG() {
        return GIAPHONG;
    }

    public void setGIAPHONG(String GIAPHONG) {
        this.GIAPHONG = GIAPHONG;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public TypeRoom(String ID_LOAIPHONG, String TENLOAI, String GIAPHONG, String _status) {
        this.ID_LOAIPHONG = ID_LOAIPHONG;
        this.TENLOAI = TENLOAI;
        this.GIAPHONG = GIAPHONG;
        this._status = _status;
    }
}
