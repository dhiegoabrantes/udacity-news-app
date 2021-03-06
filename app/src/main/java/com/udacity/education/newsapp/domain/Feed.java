package com.udacity.education.newsapp.domain;

import java.util.List;

/**
 * Created by dhiegoabrantes on 13/11/16.
 */

public class Feed {

    private String mTitle;
    private String mSectionName;
    private String mThumbnail;
    private String mUrl;

    public Feed() {
    }

    public Feed(String mTitle, String mSectionName, String mThumbnail, String mUrl) {
        this.mTitle = mTitle;
        this.mSectionName = mSectionName;
        this.mThumbnail = mThumbnail;
        this.mUrl = mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public void setSectionName(String mSectionName) {
        this.mSectionName = mSectionName;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
