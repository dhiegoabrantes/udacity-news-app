package com.udacity.education.newsapp.api.guardian;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhiegoabrantes on 12/12/16.
 */

public class GuardianFields {
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    /**
     *
     * @return
     * The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     * The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
