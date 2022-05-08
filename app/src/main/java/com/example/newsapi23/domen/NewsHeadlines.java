package com.example.newsapi23.domen;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class NewsHeadlines implements Serializable {
    @Ignore
    private Source source = null;
    private String autor = "";
    private String title = "";
    private String description = "";
    @NonNull @PrimaryKey
    private String url = "";
    private String urlToImage = "";
    private String publishedAt = "";
    private String content = "";
    @Ignore
    private boolean favoriteStatus = false;

    public NewsHeadlines(Source source, String autor, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.source = source;
        this.autor = autor;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public NewsHeadlines(String autor, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.autor = autor;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getFavoriteStatus() {return favoriteStatus;}

    public void setFavoriteStatus(boolean status) {this.favoriteStatus = status;}

    @Override
    public String toString() {
        return "NewsHeadlines{" +
                "source=" + source +
                ", autor='" + autor + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
