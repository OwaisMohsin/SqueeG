package com.example.co.squeeg.Model.buyer_requests_for_seller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BuyerRequestForSeller implements Serializable {

    @SerializedName("USERID")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String userName;
    @SerializedName("profileurl")
    @Expose
    private String profileURL;
    @SerializedName("user_profile_image")
    @Expose
    private String userProfileImage;
    @SerializedName("sno")
    @Expose
    private String requestId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("budget")
    @Expose
    private String budget;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("no_of_offers")
    @Expose
    private String noOfOffers;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoOfOffers() {
        return noOfOffers;
    }

    public void setNoOfOffers(String noOfOffers) {
        this.noOfOffers = noOfOffers;
    }
}