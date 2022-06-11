package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class Service implements Serializable {
    String ID_DV,ID_LOAIDICHVU,ID_PHONG,TU_NGAY,DEN_NGAY,TONGTIEN,_status;

    public String getID_DV() {
        return ID_DV;
    }

    public void setID_DV(String ID_DV) {
        this.ID_DV = ID_DV;
    }

    public String getID_LOAIDICHVU() {
        return ID_LOAIDICHVU;
    }

    public void setID_LOAIDICHVU(String ID_LOAIDICHVU) {
        this.ID_LOAIDICHVU = ID_LOAIDICHVU;
    }

    public String getID_PHONG() {
        return ID_PHONG;
    }

    public void setID_PHONG(String ID_PHONG) {
        this.ID_PHONG = ID_PHONG;
    }

    public String getTU_NGAY() {
        return TU_NGAY;
    }

    public void setTU_NGAY(String TU_NGAY) {
        this.TU_NGAY = TU_NGAY;
    }

    public String getDEN_NGAY() {
        return DEN_NGAY;
    }

    public void setDEN_NGAY(String DEN_NGAY) {
        this.DEN_NGAY = DEN_NGAY;
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

    public Service(String ID_DV, String ID_LOAIDICHVU, String ID_PHONG, String TU_NGAY, String DEN_NGAY, String TONGTIEN, String _status) {
        this.ID_DV = ID_DV;
        this.ID_LOAIDICHVU = ID_LOAIDICHVU;
        this.ID_PHONG = ID_PHONG;
        this.TU_NGAY = TU_NGAY;
        this.DEN_NGAY = DEN_NGAY;
        this.TONGTIEN = TONGTIEN;
        this._status = _status;
    }
}
