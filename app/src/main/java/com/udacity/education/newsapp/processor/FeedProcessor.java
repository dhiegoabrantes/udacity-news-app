package com.udacity.education.newsapp.processor;

import android.util.Log;

import com.google.gson.Gson;
import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhiegoabrantes on 20/09/16.
 */
public class FeedProcessor {

    public static List<Feed> process(String input) {
        List<Feed> feeds = new ArrayList<>();
            if(input != null && input.startsWith("{")){

                try {
                    JSONObject json = new JSONObject(input);

                    JSONObject response = json.getJSONObject("response");
                    JSONArray results = response.getJSONArray("results");
                    if(results.length() > 0){
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject jsonObject = results.getJSONObject(i);

                            String mTitle = jsonObject.optString("webTitle").toString();
                            String mSectionName = jsonObject.optString("sectionName").toString();

                            JSONObject fields = jsonObject.getJSONObject("fields");
                            String mThumbnail = fields.optString("thumbnail").toString();

                            String mUrl = jsonObject.optString("webUrl").toString();
                            Feed feed = new Feed(mTitle, mSectionName, mThumbnail, mUrl);
                            feeds.add(feed);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(Utils.LOG_TAG, e.getMessage(), e);
                }
            }
        return feeds;
    }
}
