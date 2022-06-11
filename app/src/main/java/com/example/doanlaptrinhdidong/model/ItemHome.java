package com.example.doanlaptrinhdidong.model;

public class ItemHome {

    String tenItem;

    public ItemHome(String tenItem, String hinhItem) {
        this.tenItem = tenItem;
        this.hinhItem = hinhItem;
    }

    String hinhItem;
    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }

    public String getHinhItem() {
        return hinhItem;
    }

    public void setHinhItem(String hinhItem) {
        this.hinhItem = hinhItem;
    }

}
