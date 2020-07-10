package com.example.co.squeeg.Model.buyer_requests_for_buyer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyerRequestsForBuyer {

    @SerializedName("sno")
    @Expose
    private String requestId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("description")
    @Expose
    private String Description;
    @SerializedName("no_of_offers")
    @Expose
    private String noOfOffers;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNoOfOffers() {
        return noOfOffers;
    }

    public void setNoOfOffers(String noOfOffers) {
        this.noOfOffers = noOfOffers;
    }
}
