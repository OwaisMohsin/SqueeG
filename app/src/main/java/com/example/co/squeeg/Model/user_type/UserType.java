package com.example.co.squeeg.Model.user_type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserType {

    @SerializedName("lang_key")
    @Expose
    private String lang_key;
    @SerializedName("lang_value")
    @Expose
    private String lang_value;

    public UserType() {
    }

    public UserType(String lang_key, String lang_value) {
        this.lang_key = lang_key;
        this.lang_value = lang_value;
    }

    public String getLang_key() {
        return lang_key;
    }

    public void setLang_key(String lang_key) {
        this.lang_key = lang_key;
    }

    public String getLang_value() {
        return lang_value;
    }

    public void setLang_value(String lang_value) {
        this.lang_value = lang_value;
    }
}