package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hari on 08-01-2019.
 */

public class POSTSubscriptionModel {


    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("message_key")
    @Expose
    private String message_key;
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

    public String getMessage_key() {
        return message_key;
    }

    public void setMessage_key(String message_key) {
        this.message_key = message_key;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Subscription implements Serializable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("subscription_name")
        @Expose
        private String subscription_name;
        @SerializedName("subscription_period")
        @Expose
        private String subscription_period;
        @SerializedName("period_type")
        @Expose
        private String period_type;
        @SerializedName("no_of_gigs")
        @Expose
        private String no_of_gigs;
        @SerializedName("subscription_rate")
        @Expose
        private String subscription_rate;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubscription_name() {
            return subscription_name;
        }

        public void setSubscription_name(String subscription_name) {
            this.subscription_name = subscription_name;
        }

        public String getSubscription_period() {
            return subscription_period;
        }

        public void setSubscription_period(String subscription_period) {
            this.subscription_period = subscription_period;
        }

        public String getPeriod_type() {
            return period_type;
        }

        public void setPeriod_type(String period_type) {
            this.period_type = period_type;
        }

        public String getNo_of_gigs() {
            return no_of_gigs;
        }

        public void setNo_of_gigs(String no_of_gigs) {
            this.no_of_gigs = no_of_gigs;
        }

        public String getSubscription_rate() {
            return subscription_rate;
        }

        public void setSubscription_rate(String subscription_rate) {
            this.subscription_rate = subscription_rate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    public class Data implements Serializable {

        @SerializedName("price_option")
        @Expose
        private String price_option;
        @SerializedName("extra_gig_price")
        @Expose
        private String extra_gig_price;
        @SerializedName("gig_price")
        @Expose
        private String gig_price;
        @SerializedName("subscription")
        @Expose
        private List<Subscription> subscription = null;

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

        public List<Subscription> getSubscription() {
            return subscription;
        }

        public void setSubscription(List<Subscription> subscription) {
            this.subscription = subscription;
        }

    }

}
