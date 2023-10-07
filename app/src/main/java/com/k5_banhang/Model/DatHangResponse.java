package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatHangResponse {
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
