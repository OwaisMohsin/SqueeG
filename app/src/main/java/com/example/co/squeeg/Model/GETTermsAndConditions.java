package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hari on 24-03-2018.
 */

public class GETTermsAndConditions {

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

        @SerializedName("user_terms_and_conditions")
        @Expose
        private String user_terms_and_conditions;
        @SerializedName("gigs_terms_and_conditions")
        @Expose
        private String gigs_terms_and_conditions;

        public String getUser_terms_and_conditions() {
            return user_terms_and_conditions;
        }

        public void setUser_terms_and_conditions(String user_terms_and_conditions) {
            this.user_terms_and_conditions = user_terms_and_conditions;
        }

        public String getGigs_terms_and_conditions() {
            return gigs_terms_and_conditions;
        }

        public void setGigs_terms_and_conditions(String gigs_terms_and_conditions) {
            this.gigs_terms_and_conditions = gigs_terms_and_conditions;
        }

    }

}
