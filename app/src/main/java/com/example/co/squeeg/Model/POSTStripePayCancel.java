package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hari on 04-04-2018.
 */

public class POSTStripePayCancel {

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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("account_holder_name")
        @Expose
        private String account_holder_name;
        @SerializedName("account_number")
        @Expose
        private String account_number;
        @SerializedName("account_iban")
        @Expose
        private String account_iban;
        @SerializedName("bank_name")
        @Expose
        private String bank_name;
        @SerializedName("bank_address")
        @Expose
        private String bank_address;
        @SerializedName("sort_code")
        @Expose
        private String sort_code;
        @SerializedName("routing_number")
        @Expose
        private String routing_number;
        @SerializedName("account_ifsc")
        @Expose
        private String account_ifsc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAccount_holder_name() {
            return account_holder_name;
        }

        public void setAccount_holder_name(String account_holder_name) {
            this.account_holder_name = account_holder_name;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getAccount_iban() {
            return account_iban;
        }

        public void setAccount_iban(String account_iban) {
            this.account_iban = account_iban;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getSort_code() {
            return sort_code;
        }

        public void setSort_code(String sort_code) {
            this.sort_code = sort_code;
        }

        public String getRouting_number() {
            return routing_number;
        }

        public void setRouting_number(String routing_number) {
            this.routing_number = routing_number;
        }

        public String getAccount_ifsc() {
            return account_ifsc;
        }

        public void setAccount_ifsc(String account_ifsc) {
            this.account_ifsc = account_ifsc;
        }

    }
}
