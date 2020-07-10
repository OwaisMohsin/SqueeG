package com.example.co.squeeg.Model.user_type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserTypeList {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<UserType> userType;

    public UserTypeList() {
    }

    public UserTypeList(Integer code, List<UserType> userType) {
        this.code = code;
        this.userType = userType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<UserType> getUserType() {
        return userType;
    }

    public void setUserType(List<UserType> userType) {
        this.userType = userType;
    }
}