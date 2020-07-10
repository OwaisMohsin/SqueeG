package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user5 on 12-01-2018.
 */

public class GETFooterInformation {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("primary")
    @Expose
    private List<Primary> primary = null;

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

    public List<Primary> getPrimary() {
        return primary;
    }

    public void setPrimary(List<Primary> primary) {
        this.primary = primary;
    }

    public class Primary {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("footer_submenu")
        @Expose
        private String footerSubmenu;
        @SerializedName("page_desc")
        @Expose
        private String pageDesc;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFooterSubmenu() {
            return footerSubmenu;
        }

        public void setFooterSubmenu(String footerSubmenu) {
            this.footerSubmenu = footerSubmenu;
        }

        public String getPageDesc() {
            return pageDesc;
        }

        public void setPageDesc(String pageDesc) {
            this.pageDesc = pageDesc;
        }

    }

}
