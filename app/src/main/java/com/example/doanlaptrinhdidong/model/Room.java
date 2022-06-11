package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Room implements Serializable {
    String ID_PHONG,ID_LOAIPHONG,TENPHONG,DIACHI,HINH,_status;

    public String getID_PHONG() {
        return ID_PHONG;
    }

    public void setID_PHONG(String ID_PHONG) {
        this.ID_PHONG = ID_PHONG;
    }

    public String getID_LOAIPHONG() {
        return ID_LOAIPHONG;
    }

    public void setID_LOAIPHONG(String ID_LOAIPHONG) {
        this.ID_LOAIPHONG = ID_LOAIPHONG;
    }

    public String getTENPHONG() {
        return TENPHONG;
    }

    public void setTENPHONG(String TENPHONG) {
        this.TENPHONG = TENPHONG;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
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

    public Room(String ID_PHONG, String ID_LOAIPHONG, String TENPHONG, String DIACHI, String HINH, String _status) {
        this.ID_PHONG = ID_PHONG;
        this.ID_LOAIPHONG = ID_LOAIPHONG;
        this.TENPHONG = TENPHONG;
        this.DIACHI = DIACHI;
        this.HINH = HINH;
        this._status = _status;
    }
}
