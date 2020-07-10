package com.example.co.squeeg.Model.seller_offers_for_buyer;

import com.example.co.squeeg.Model.buyer_requests_for_seller.BuyerRequestForSeller;
import com.example.co.squeeg.Model.buyer_requests_for_seller.Currency;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POSTSellerOffersForBuyerList {

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
    private List<SellerOffersForBuyer> data = null;

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

    public List<SellerOffersForBuyer> getData() {
        return data;
    }

    public void setData(List<SellerOffersForBuyer> data) {
        this.data = data;
    }
}
