package com.example.myapp.dataclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class University {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("alpha_two_code")
    @Expose
    private String alpha_two_code;
    @SerializedName("state-province")
    @Expose
    private String state_province;
    @SerializedName("domains")
    @Expose
    private List<String> domains;
    @SerializedName("web_pages")
    @Expose
    private List<String> web_pages;

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public List<String> getWebPages() {
        return web_pages;
    }


}