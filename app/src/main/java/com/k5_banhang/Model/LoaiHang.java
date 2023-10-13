package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoaiHang implements Serializable {

    @SerializedName("malh")
    @Expose
    private String malh;

    @SerializedName("tenloaihang")
    @Expose
    private String tenloaihang;

    @SerializedName("mota")
    @Expose
    private String mota;

    @SerializedName("ghichu")
    @Expose
    private String ghichu;

    public String getMalh() {
        return malh;
    }

    public void setMalh(String malh) {
        this.malh = malh;
    }

    public String getTenloaihang() {
        return tenloaihang;
    }

    public void setTenloaihang(String tenloaihang) {
        this.tenloaihang = tenloaihang;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
