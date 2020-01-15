package com.wgfxer.pryanikytest.data.models.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonRoot {

    @SerializedName("data")
    @Expose
    private List<DataItem> dataList = null;

    @SerializedName("view")
    @Expose
    private List<String> viewList = null;

    public List<DataItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataItem> dataList) {
        this.dataList = dataList;
    }

    public List<String> getViewList() {
        return viewList;
    }

    public void setViewList(List<String> viewList) {
        this.viewList = viewList;
    }

}