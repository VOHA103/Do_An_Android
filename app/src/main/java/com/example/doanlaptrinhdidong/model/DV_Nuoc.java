package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class DV_Nuoc implements Serializable {
    String ID_Nuoc,ID_PHONG,Thang_TT,CSM,CSC,TONGTIEN,_status;

    public String getID_Nuoc() {
        return ID_Nuoc;
    }

    public void setID_Nuoc(String ID_Nuoc) {
        this.ID_Nuoc = ID_Nuoc;
    }

    public String getID_PHONG() {
        return ID_PHONG;
    }

    public void setID_PHONG(String ID_PHONG) {
        this.ID_PHONG = ID_PHONG;
    }

    public String getThang_TT() {
        return Thang_TT;
    }

    public void setThang_TT(String thang_TT) {
        Thang_TT = thang_TT;
    }

    public String getCSM() {
        return CSM;
    }

    public void setCSM(String CSM) {
        this.CSM = CSM;
    }

    public String getCSC() {
        return CSC;
    }

    public void setCSC(String CSC) {
        this.CSC = CSC;
    }

    public String getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(String TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public DV_Nuoc(String ID_Nuoc, String ID_PHONG, String thang_TT, String CSM, String CSC, String TONGTIEN, String _status) {
        this.ID_Nuoc = ID_Nuoc;
        this.ID_PHONG = ID_PHONG;
        Thang_TT = thang_TT;
        this.CSM = CSM;
        this.CSC = CSC;
        this.TONGTIEN = TONGTIEN;
        this._status = _status;
    }
}
