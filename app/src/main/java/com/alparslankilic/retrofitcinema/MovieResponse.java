package com.alparslankilic.retrofitcinema;

import com.google.gson.annotations.SerializedName;


public class MovieResponse {

    @SerializedName("data")
    private Movies data;

    public Movies getData() {
        return data;
    }

    public void setData(Movies data) {
        this.data = data;
    }
}

