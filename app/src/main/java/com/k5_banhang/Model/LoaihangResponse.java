package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoaihangResponse {

    @SerializedName("success")
    @Expose
    public int success;

    @SerializedName("result")
    @Expose
    public List<LoaiHang> result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<LoaiHang> getResult() {
        return result;
    }

    public void setResult(List<LoaiHang> result) {
        this.result = result;
    }
}
