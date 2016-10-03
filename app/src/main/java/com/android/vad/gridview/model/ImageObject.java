package com.android.vad.gridview.model;

import org.json.JSONObject;

/**
 * Created by liemvo on 10/3/16.
 */
public class ImageObject {
    private int id;
    private String imageUrl;

    public ImageObject(){

    }

    public  ImageObject(JSONObject jsonObject){
        if(jsonObject == null) return;
        this.id = jsonObject.optInt("id");
        this.imageUrl = jsonObject.optString("imageUrl");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
