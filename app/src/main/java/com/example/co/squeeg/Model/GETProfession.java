package com.example.co.squeeg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GETProfession {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("profession_name")
    @Expose
    private String profession_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }

}
