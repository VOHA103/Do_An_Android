package com.example.doanlaptrinhdidong.model;

import java.io.Serializable;

public class TypeService implements Serializable {
    String ID_LOAIDICHVU,TENDICHVU,DONGIA,_status;

    public String getID_LOAIDICHVU() {
        return ID_LOAIDICHVU;
    }

    public void setID_LOAIDICHVU(String ID_LOAIDICHVU) {
        this.ID_LOAIDICHVU = ID_LOAIDICHVU;
    }

    public String getTENDICHVU() {
        return TENDICHVU;
    }

    public void setTENDICHVU(String TENDICHVU) {
        this.TENDICHVU = TENDICHVU;
    }

    public String getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(String DONGIA) {
        this.DONGIA = DONGIA;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public TypeService(String ID_LOAIDICHVU, String TENDICHVU, String DONGIA, String _status) {
        this.ID_LOAIDICHVU = ID_LOAIDICHVU;
        this.TENDICHVU = TENDICHVU;
        this.DONGIA = DONGIA;
        this._status = _status;
    }
}
