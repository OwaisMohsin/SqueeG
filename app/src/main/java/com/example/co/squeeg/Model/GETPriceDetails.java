package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hari on 25-04-2018.
 */

public class GETPriceDetails {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("price_option")
        @Expose
        private String price_option;
        @SerializedName("extra_gig_price")
        @Expose
        private String extra_gig_price;
        @SerializedName("gig_price")
        @Expose
        private String gig_price;

        public String getPrice_option() {
            return price_option;
        }

        public void setPrice_option(String price_option) {
            this.price_option = price_option;
        }

        public String getExtra_gig_price() {
            return extra_gig_price;
        }

        public void setExtra_gig_price(String extra_gig_price) {
            this.extra_gig_price = extra_gig_price;
        }

        public String getGig_price() {
            return gig_price;
        }

        public void setGig_price(String gig_price) {
            this.gig_price = gig_price;
        }

    }
}
