package com.wgfxer.pryanikytest.data.models.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataItem {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("data")
    @Expose
    private Data data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}