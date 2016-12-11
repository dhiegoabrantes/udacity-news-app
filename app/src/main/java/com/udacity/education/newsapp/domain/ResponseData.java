package com.udacity.education.newsapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhiegoabrantes on 13/11/16.
 */

public class ResponseData{

    private Feed feed;

    public ResponseData() {
        this.feed = new Feed();
    }

    public ResponseData(Feed feed) {
        this.feed = feed;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
