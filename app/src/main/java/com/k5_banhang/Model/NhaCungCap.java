package com.k5_banhang.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NhaCungCap implements Serializable {

    @SerializedName("mancc")
    @Expose
    private String mancc;
    @SerializedName("tenncc")
    @Expose
    private String tenncc;
    @SerializedName("diachincc")
    @Expose
    private String 	diachincc;
    @SerializedName("sdtncc")
    @Expose
    private String sdtncc;
    @SerializedName("emailncc")
    @Expose
    private String emailncc;
    @SerializedName("stkncc")
    @Expose
    private String stkncc;
    @SerializedName("ghichu")
    @Expose
    private String ghichu;

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachincc() {
        return diachincc;
    }

    public void setDiachincc(String diachincc) {
        this.diachincc = diachincc;
    }

    public String getSdtncc() {
        return sdtncc;
    }

    public void setSdtncc(String sdtncc) {
        this.sdtncc = sdtncc;
    }

    public String getEmailncc() {
        return emailncc;
    }

    public void setEmailncc(String emailncc) {
        this.emailncc = emailncc;
    }

    public String getStkncc() {
        return stkncc;
    }

    public void setStkncc(String stkncc) {
        this.stkncc = stkncc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
