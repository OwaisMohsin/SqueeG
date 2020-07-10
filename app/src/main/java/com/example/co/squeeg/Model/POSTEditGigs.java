package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prasad on 11/6/2017.
 */

public class POSTEditGigs {
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
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("gig_details")
        @Expose
        private Gig_details gig_details;
        @SerializedName("extra_gigs")
        @Expose
        private List<Extra_gig> extra_gigs = null;
        @SerializedName("gig_images")
        @Expose
        private List<Gig_image> gig_images = null;

        public Gig_details getGig_details() {
            return gig_details;
        }

        public void setGig_details(Gig_details gig_details) {
            this.gig_details = gig_details;
        }

        public List<Extra_gig> getExtra_gigs() {
            return extra_gigs;
        }

        public void setExtra_gigs(List<Extra_gig> extra_gigs) {
            this.extra_gigs = extra_gigs;
        }

        public List<Gig_image> getGig_images() {
            return gig_images;
        }

        public void setGig_images(List<Gig_image> gig_images) {
            this.gig_images = gig_images;
        }

    }

    public class Extra_gig {

        @SerializedName("extra_gigs")
        @Expose
        private String extra_gigs;
        @SerializedName("extra_gigs_amount")
        @Expose
        private String extra_gigs_amount;
        @SerializedName("currency_type")
        @Expose
        private String currency_type;
        @SerializedName("currency_sign")
        @Expose
        private String currency_sign;
        @SerializedName("extra_gigs_delivery")
        @Expose
        private String extra_gigs_delivery;

        public String getExtra_gigs() {
            return extra_gigs;
        }

        public void setExtra_gigs(String extra_gigs) {
            this.extra_gigs = extra_gigs;
        }

        public String getExtra_gigs_amount() {
            return extra_gigs_amount;
        }

        public void setExtra_gigs_amount(String extra_gigs_amount) {
            this.extra_gigs_amount = extra_gigs_amount;
        }

        public String getCurrency_type() {
            return currency_type;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }

        public String getCurrency_sign() {
            return currency_sign;
        }

        public void setCurrency_sign(String currency_sign) {
            this.currency_sign = currency_sign;
        }

        public String getExtra_gigs_delivery() {
            return extra_gigs_delivery;
        }

        public void setExtra_gigs_delivery(String extra_gigs_delivery) {
            this.extra_gigs_delivery = extra_gigs_delivery;
        }

    }

    public class Gig_details {

        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("cost_type")
        @Expose
        private String cost_type;
        @SerializedName("currency_type")
        @Expose
        private String currency_type;
        @SerializedName("currency_sign")
        @Expose
        private String currency_sign;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("gig_price")
        @Expose
        private String gig_price;
        @SerializedName("delivering_time")
        @Expose
        private String delivering_time;
        @SerializedName("category_id")
        @Expose
        private String category_id;
        @SerializedName("category_name")
        @Expose
        private String category_name;
        @SerializedName("sub_categoryid")
        @Expose
        private String sub_categoryid;
        @SerializedName("gig_tags")
        @Expose
        private String gig_tags;
        @SerializedName("gig_details")
        @Expose
        private String gig_details;
        @SerializedName("super_fast_delivery")
        @Expose
        private String super_fast_delivery;
        @SerializedName("super_fast_delivery_desc")
        @Expose
        private String super_fast_delivery_desc;
        @SerializedName("super_fast_delivery_date")
        @Expose
        private String super_fast_delivery_date;
        @SerializedName("super_fast_charges")
        @Expose
        private String super_fast_charges;
        @SerializedName("requirements")
        @Expose
        private String requirements;
        @SerializedName("work_option")
        @Expose
        private String work_option;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getCost_type() {
            return cost_type;
        }

        public void setCost_type(String cost_type) {
            this.cost_type = cost_type;
        }

        public String getCurrency_type() {
            return currency_type;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }

        public String getCurrency_sign() {
            return currency_sign;
        }

        public void setCurrency_sign(String currency_sign) {
            this.currency_sign = currency_sign;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGig_price() {
            return gig_price;
        }

        public void setGig_price(String gig_price) {
            this.gig_price = gig_price;
        }

        public String getDelivering_time() {
            return delivering_time;
        }

        public void setDelivering_time(String delivering_time) {
            this.delivering_time = delivering_time;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getSub_categoryid() {
            return sub_categoryid;
        }

        public void setSub_categoryid(String sub_categoryid) {
            this.sub_categoryid = sub_categoryid;
        }

        public String getGig_tags() {
            return gig_tags;
        }

        public void setGig_tags(String gig_tags) {
            this.gig_tags = gig_tags;
        }

        public String getGig_details() {
            return gig_details;
        }

        public void setGig_details(String gig_details) {
            this.gig_details = gig_details;
        }

        public String getSuper_fast_delivery() {
            return super_fast_delivery;
        }

        public void setSuper_fast_delivery(String super_fast_delivery) {
            this.super_fast_delivery = super_fast_delivery;
        }

        public String getSuper_fast_delivery_desc() {
            return super_fast_delivery_desc;
        }

        public void setSuper_fast_delivery_desc(String super_fast_delivery_desc) {
            this.super_fast_delivery_desc = super_fast_delivery_desc;
        }

        public String getSuper_fast_delivery_date() {
            return super_fast_delivery_date;
        }

        public void setSuper_fast_delivery_date(String super_fast_delivery_date) {
            this.super_fast_delivery_date = super_fast_delivery_date;
        }

        public String getSuper_fast_charges() {
            return super_fast_charges;
        }

        public void setSuper_fast_charges(String super_fast_charges) {
            this.super_fast_charges = super_fast_charges;
        }

        public String getRequirements() {
            return requirements;
        }

        public void setRequirements(String requirements) {
            this.requirements = requirements;
        }

        public String getWork_option() {
            return work_option;
        }

        public void setWork_option(String work_option) {
            this.work_option = work_option;
        }

    }

    public class Gig_image {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("gig_id")
        @Expose
        private String gig_id;
        @SerializedName("image_path")
        @Expose
        private String image_path;
        @SerializedName("gig_image_thumb")
        @Expose
        private String gig_image_thumb;
        @SerializedName("gig_image_tile")
        @Expose
        private String gig_image_tile;
        @SerializedName("gig_image_medium")
        @Expose
        private String gig_image_medium;
        @SerializedName("created_date")
        @Expose
        private String created_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGig_id() {
            return gig_id;
        }

        public void setGig_id(String gig_id) {
            this.gig_id = gig_id;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getGig_image_thumb() {
            return gig_image_thumb;
        }

        public void setGig_image_thumb(String gig_image_thumb) {
            this.gig_image_thumb = gig_image_thumb;
        }

        public String getGig_image_tile() {
            return gig_image_tile;
        }

        public void setGig_image_tile(String gig_image_tile) {
            this.gig_image_tile = gig_image_tile;
        }

        public String getGig_image_medium() {
            return gig_image_medium;
        }

        public void setGig_image_medium(String gig_image_medium) {
            this.gig_image_medium = gig_image_medium;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

    }

}
