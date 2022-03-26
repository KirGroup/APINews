package com.example.newsapi23;

import com.example.newsapi23.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponce> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}