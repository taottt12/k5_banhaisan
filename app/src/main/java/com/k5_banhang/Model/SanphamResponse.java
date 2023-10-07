package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SanphamResponse {
    @SerializedName("success")
    @Expose
    public int success;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("result")
    @Expose
    public List<SanPham> result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SanPham> getResult() {
        return result;
    }

    public void setResult(List<SanPham> result) {
        this.result = result;
    }
}
