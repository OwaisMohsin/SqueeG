package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prasad on 10/26/2017.
 */

public class POSTUpdateProfile {
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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

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
        private String userTimezone;
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
        private String langSpeaks;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state_name")
        @Expose
        private String stateName;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("profession")
        @Expose
        private String profession;
        @SerializedName("profession_name")
        @Expose
        private String professionName;
        @SerializedName("contact")
        @Expose
        private String contact;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("user_profile_image")
        @Expose
        private String userProfileImage;
        @SerializedName("user_thumb_image")
        @Expose
        private String userThumbImage;

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

        public String getUserTimezone() {
            return userTimezone;
        }

        public void setUserTimezone(String userTimezone) {
            this.userTimezone = userTimezone;
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

        public String getLangSpeaks() {
            return langSpeaks;
        }

        public void setLangSpeaks(String langSpeaks) {
            this.langSpeaks = langSpeaks;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
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

        public String getProfessionName() {
            return professionName;
        }

        public void setProfessionName(String professionName) {
            this.professionName = professionName;
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

        public String getUserProfileImage() {
            return userProfileImage;
        }

        public void setUserProfileImage(String userProfileImage) {
            this.userProfileImage = userProfileImage;
        }

        public String getUserThumbImage() {
            return userThumbImage;
        }

        public void setUserThumbImage(String userThumbImage) {
            this.userThumbImage = userThumbImage;
        }

    }

}
