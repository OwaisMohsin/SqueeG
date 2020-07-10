package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prasad on 10/25/2017.
 */

public class POSTLogin {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("user_timezone")
        @Expose
        private String user_timezone;
        @SerializedName("verified")
        @Expose
        private String verified;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("lang_speaks")
        @Expose
        private String lang_speaks;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("profession")
        @Expose
        private String profession;
        @SerializedName("contact")
        @Expose
        private String contact;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("user_profile_image")
        @Expose
        private String user_profile_image;
        @SerializedName("user_thumb_image")
        @Expose
        private String user_thumb_image;
        @SerializedName("price_option")
        @Expose
        private String price_option;
        @SerializedName("gig_price")
        @Expose
        private String gig_price;
        @SerializedName("extra_gig_price")
        @Expose
        private String extra_gig_price;
        @SerializedName("paypal_option")
        @Expose
        private String paypal_option;
        @SerializedName("paypal_allow")
        @Expose
        private String paypal_allow;
        @SerializedName("stripe_option")
        @Expose
        private String stripe_option;
        @SerializedName("stripe_allow")
        @Expose
        private String stripe_allow;
        @SerializedName("paypal_email")
        @Expose
        private String paypal_email;
        @SerializedName("stripe_bank")
        @Expose
        private Stripe_bank stripe_bank;
        @SerializedName("unique_code")
        @Expose
        private String unique_code;

        public String getUnique_code() {
            return unique_code;
        }

        public void setUnique_code(String unique_code) {
            this.unique_code = unique_code;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getUser_timezone() {
            return user_timezone;
        }

        public void setUser_timezone(String user_timezone) {
            this.user_timezone = user_timezone;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getLang_speaks() {
            return lang_speaks;
        }

        public void setLang_speaks(String lang_speaks) {
            this.lang_speaks = lang_speaks;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUser_profile_image() {
            return user_profile_image;
        }

        public void setUser_profile_image(String user_profile_image) {
            this.user_profile_image = user_profile_image;
        }

        public String getUser_thumb_image() {
            return user_thumb_image;
        }

        public void setUser_thumb_image(String user_thumb_image) {
            this.user_thumb_image = user_thumb_image;
        }

        public String getPrice_option() {
            return price_option;
        }

        public void setPrice_option(String price_option) {
            this.price_option = price_option;
        }

        public String getGig_price() {
            return gig_price;
        }

        public void setGig_price(String gig_price) {
            this.gig_price = gig_price;
        }

        public String getExtra_gig_price() {
            return extra_gig_price;
        }

        public void setExtra_gig_price(String extra_gig_price) {
            this.extra_gig_price = extra_gig_price;
        }

        public String getPaypal_option() {
            return paypal_option;
        }

        public void setPaypal_option(String paypal_option) {
            this.paypal_option = paypal_option;
        }

        public String getPaypal_allow() {
            return paypal_allow;
        }

        public void setPaypal_allow(String paypal_allow) {
            this.paypal_allow = paypal_allow;
        }

        public String getStripe_option() {
            return stripe_option;
        }

        public void setStripe_option(String stripe_option) {
            this.stripe_option = stripe_option;
        }

        public String getStripe_allow() {
            return stripe_allow;
        }

        public void setStripe_allow(String stripe_allow) {
            this.stripe_allow = stripe_allow;
        }

        public String getPaypal_email() {
            return paypal_email;
        }

        public void setPaypal_email(String paypal_email) {
            this.paypal_email = paypal_email;
        }

        public Stripe_bank getStripe_bank() {
            return stripe_bank;
        }

        public void setStripe_bank(Stripe_bank stripe_bank) {
            this.stripe_bank = stripe_bank;
        }

    }

    public class Stripe_bank {

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
