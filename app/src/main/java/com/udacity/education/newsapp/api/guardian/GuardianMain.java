package com.udacity.education.newsapp.api.guardian;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhiegoabrantes on 12/12/16.
 */

public class GuardianMain {
    @SerializedName("response")
    @Expose
    private GuardianResponse response;

    /**
     *
     * @return
     * The response
     */
    public GuardianResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(GuardianResponse response) {
        this.response = response;
    }
}