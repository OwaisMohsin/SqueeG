package com.example.co.squeeg.Model.seller_offers_for_buyer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("id")
    @Expose
    private Integer currencyId;
    @SerializedName("currency")
    @Expose
    private String currencyName;
    @SerializedName("currency_sign")
    @Expose
    private String currencySign;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
