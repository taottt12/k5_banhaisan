package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SanPham implements Serializable {
    @SerializedName("ma_mh")
    @Expose
    private String ma_mh;

    @SerializedName("tensanpham")
    @Expose
    private String tensanpham;

    @SerializedName("soluong")
    @Expose
    private String soluong;

    @SerializedName("dongia")
    @Expose
    private String dongia;

    @SerializedName("donvitinh")
    @Expose
    private String donvitinh;

    @SerializedName("ghichu")
    @Expose
    private String ghichu;

    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;

    @SerializedName("mancc")
    @Expose
    private String mancc;

    @SerializedName("malh")
    @Expose
    private String malh;

    public String getMa_mh() {
        return ma_mh;
    }

    public void setMa_mh(String ma_mh) {
        this.ma_mh = ma_mh;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getMalh() {
        return malh;
    }

    public void setMalh(String malh) {
        this.malh = malh;
    }
}
