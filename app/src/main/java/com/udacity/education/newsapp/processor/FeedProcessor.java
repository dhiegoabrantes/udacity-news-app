package com.udacity.education.newsapp.processor;

import com.google.gson.Gson;
import com.udacity.education.newsapp.api.guardian.GuardianMain;
import com.udacity.education.newsapp.api.guardian.GuardianResult;
import com.udacity.education.newsapp.domain.Feed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhiegoabrantes on 20/09/16.
 */
public class FeedProcessor {

    public static List<Feed> process(String input) {
            if(input != null && input.startsWith("{")){
                Gson gson = new Gson();
                GuardianMain guardianMain = gson.fromJson(input, GuardianMain.class);
                List<Feed> feeds = new ArrayList<>();
                for (GuardianResult result : guardianMain.getResponse().getResults()) {
                    Feed feed = new Feed(result.getWebTitle(), result.getSectionName(), result.getFields().getThumbnail(), result.getWebUrl());
                    feeds.add(feed);
                }
                return feeds;
            }
        return null;
    }

}
