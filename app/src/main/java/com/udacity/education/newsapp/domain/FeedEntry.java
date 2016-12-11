package com.udacity.education.newsapp.domain;

import java.util.List;

/**
 * Created by dhiegoabrantes on 13/11/16.
 */

public class FeedEntry {

    private String title;
    private String link;
    private String author;
    private String publishedDate;
    private String contentSnippet;
    private String content;
    private List<String> categories;

    public FeedEntry() {
    }

    public FeedEntry(String title, String link, String author, String publishedDate, String contentSnippet, String content, List<String> categories) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.publishedDate = publishedDate;
        this.contentSnippet = contentSnippet;
        this.content = content;
        this.categories = categories;
    }

    private String scapeString(String toScape){
        if(toScape != null){
            return toScape.replace("/u/", "").replace("/r", "").replace("\r", "").replace("\n", "").replace("\\", "");
        }
        return "";
    }

    public String getTitle() {
        return scapeString(this.title);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return scapeString(this.link);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return scapeString(this.author);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getContentSnippet() {
        return scapeString(this.contentSnippet);
    }

    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }

    public String getContent() {
        return scapeString(this.content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
