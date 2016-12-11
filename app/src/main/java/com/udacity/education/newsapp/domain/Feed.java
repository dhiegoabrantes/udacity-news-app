package com.udacity.education.newsapp.domain;

import java.util.List;

/**
 * Created by dhiegoabrantes on 13/11/16.
 */

public class Feed {

    private String feedUrl;
    private String title;
    private String link;
    private String author;
    private String description;
    private String type;
    private List<FeedEntry> entries;

    public Feed() {
    }

    public Feed(String feedUrl, String title, String link, String author, String description, String type, List<FeedEntry> entries) {
        this.feedUrl = feedUrl;
        this.title = title;
        this.link = link;
        this.author = author;
        this.description = description;
        this.type = type;
        this.entries = entries;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FeedEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<FeedEntry> entries) {
        this.entries = entries;
    }
}
