package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hari on 23-03-2018.
 */

public class GETFooterMenu {
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

    public static class Primary {

        @SerializedName("main_menu")
        @Expose
        private String main_menu;
        @SerializedName("is_expand")
        @Expose
        private Integer is_expand;
        @SerializedName("sub_menu")
        @Expose
        private List<Sub_menu> sub_menu = null;

        public String getMain_menu() {
            return main_menu;
        }

        public void setMain_menu(String main_menu) {
            this.main_menu = main_menu;
        }

        public Integer getIs_expand() {
            return is_expand;
        }

        public void setIs_expand(Integer is_expand) {
            this.is_expand = is_expand;
        }

        public List<Sub_menu> getSub_menu() {
            return sub_menu;
        }

        public void setSub_menu(List<Sub_menu> sub_menu) {
            this.sub_menu = sub_menu;
        }

    }

    public static class Sub_menu {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("page_desc")
        @Expose
        private String page_desc;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPage_desc() {
            return page_desc;
        }

        public void setPage_desc(String page_desc) {
            this.page_desc = page_desc;
        }

    }
}
