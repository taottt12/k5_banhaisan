package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("success")
    @Expose
    public int success;

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("sdt")
    @Expose
    public String sdt;
    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("authorities")
    @Expose
    public int authorities;
    @SerializedName("id")
    @Expose
    public int id_user;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAuthorities() {
        return authorities;
    }

    public void setAuthorities(int authorities) {
        this.authorities = authorities;
    }
}
