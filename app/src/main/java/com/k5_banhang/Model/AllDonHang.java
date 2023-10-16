package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AllDonHang implements Serializable {
    @SerializedName("tenKH")
    @Expose
    private String TenKH;

    @SerializedName("sdtKH")
    @Expose
    private String SoDienThoai;

    @SerializedName("diaChiKH")
    @Expose
    private String DiaChi;

    @SerializedName("ngayban")
    @Expose
    private String NgayDatHang;


    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        this.TenKH = tenKH;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.SoDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    public String getNgayDatHang() {
        return NgayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.NgayDatHang = ngayDatHang;
    }
}
