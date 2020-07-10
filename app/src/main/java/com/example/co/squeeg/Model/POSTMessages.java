package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prasad on 11/14/2017.
 */

public class POSTMessages {


    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("last_chat_user_id")
    @Expose
    private String last_chat_user_id;
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

    public String getLast_chat_user_id() {
        return last_chat_user_id;
    }

    public void setLast_chat_user_id(String last_chat_user_id) {
        this.last_chat_user_id = last_chat_user_id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("total_pages")
        @Expose
        private Integer total_pages;
        @SerializedName("chat_details")
        @Expose
        private List<Chat_detail> chat_details = null;

        public Integer getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(Integer total_pages) {
            this.total_pages = total_pages;
        }

        public List<Chat_detail> getChat_details() {
            return chat_details;
        }

        public void setChat_details(List<Chat_detail> chat_details) {
            this.chat_details = chat_details;
        }

    }

    public class Chat_detail {

        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("profile_image")
        @Expose
        private String profile_image;
        @SerializedName("user_status")
        @Expose
        private String user_status;
        @SerializedName("chat_id")
        @Expose
        private String chat_id;
        @SerializedName("timezone")
        @Expose
        private String timezone;
        @SerializedName("last_message")
        @Expose
        private String last_message;
        @SerializedName("utc_time")
        @Expose
        private String utc_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }


        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getChat_id() {
            return chat_id;
        }

        public void setChat_id(String chat_id) {
            this.chat_id = chat_id;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getLast_message() {
            return last_message;
        }

        public void setLast_message(String last_message) {
            this.last_message = last_message;
        }

        public String getUtc_time() {
            return utc_time;
        }

        public void setUtc_time(String utc_time) {
            this.utc_time = utc_time;
        }

    }


}
