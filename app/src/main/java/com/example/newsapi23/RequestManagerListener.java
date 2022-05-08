package com.example.newsapi23;

import com.example.newsapi23.domen.NewsHeadlines;

import java.util.List;

public interface RequestManagerListener<NewsApiResponce> {
    void onSuccess(List<NewsHeadlines> list, String message);
    void onError(String message);
}