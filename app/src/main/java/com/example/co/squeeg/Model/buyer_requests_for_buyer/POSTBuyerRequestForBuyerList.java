package com.example.co.squeeg.Model.buyer_requests_for_buyer;

import com.example.co.squeeg.Model.buyer_requests_for_seller.BuyerRequestForSeller;
import com.example.co.squeeg.Model.buyer_requests_for_seller.Currency;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POSTBuyerRequestForBuyerList {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<BuyerRequestsForBuyer> data = null;

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

    public List<BuyerRequestsForBuyer> getData() {
        return data;
    }

    public void setData(List<BuyerRequestsForBuyer> data) {
        this.data = data;
    }
}
