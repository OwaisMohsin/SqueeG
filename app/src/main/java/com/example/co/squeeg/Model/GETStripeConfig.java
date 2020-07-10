package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hari on 16-07-2018.
 */

public class GETStripeConfig {

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

        @SerializedName("stripe_public_key")
        @Expose
        private String stripePublicKey;
        @SerializedName("stripe_secret_key")
        @Expose
        private String stripeSecretKey;

        public String getStripePublicKey() {
            return stripePublicKey;
        }

        public void setStripePublicKey(String stripePublicKey) {
            this.stripePublicKey = stripePublicKey;
        }

        public String getStripeSecretKey() {
            return stripeSecretKey;
        }

        public void setStripeSecretKey(String stripeSecretKey) {
            this.stripeSecretKey = stripeSecretKey;
        }

    }
}
