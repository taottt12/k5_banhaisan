package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllDonHangResponse {
    @SerializedName("success")
    @Expose
    public int success;

    @SerializedName("result")
    @Expose
    public List<AllDonHang> result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<AllDonHang> getResult() {
        return result;
    }

    public void setResult(List<AllDonHang> result) {
        this.result = result;
    }
}
