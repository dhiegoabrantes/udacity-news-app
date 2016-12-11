package com.udacity.education.newsapp.processor;

import com.google.gson.Gson;
import com.udacity.education.newsapp.domain.FeedDTO;
import com.udacity.education.newsapp.domain.ResponseData;

/**
 * Created by dhiegoabrantes on 20/09/16.
 */
public class FeedProcessor {

    public static ResponseData process(String input) {
            if(input != null && input.startsWith("{")){
                Gson gson = new Gson();
                FeedDTO feedDTO = gson.fromJson(input, FeedDTO.class);
                return feedDTO.getResponseData();
            }
        return null;
    }

}
