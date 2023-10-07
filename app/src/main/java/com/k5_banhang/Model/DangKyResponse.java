package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DangKyResponse {
    @SerializedName("success")
    @Expose
    public int success;

    @SerializedName("username")
    @Expose
    public String message;

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
}
