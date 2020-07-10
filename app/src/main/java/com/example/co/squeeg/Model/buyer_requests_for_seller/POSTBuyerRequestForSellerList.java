package com.example.co.squeeg.Model.buyer_requests_for_seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POSTBuyerRequestForSellerList {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("currency")
    @Expose
    private Currency currencyData = null;
    @SerializedName("data")
    @Expose
    private List<BuyerRequestForSeller> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Currency getCurrencyData() {
        return currencyData;
    }

    public void setCurrencyData(Currency currencyData) {
        this.currencyData = currencyData;
    }

    public List<BuyerRequestForSeller> getData() {
        return data;
    }

    public void setData(List<BuyerRequestForSeller> data) {
        this.data = data;
    }
}