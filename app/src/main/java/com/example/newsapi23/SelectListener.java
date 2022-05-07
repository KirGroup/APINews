package com.example.newsapi23;

import com.example.newsapi23.domen.ListNewsHeadlines;
import com.example.newsapi23.domen.NewsHeadlines;

public interface SelectListener {
    void onNewsClicked(ListNewsHeadlines headlines);
    void setFavorite(ListNewsHeadlines newsHeadlines, boolean isFavorite);
}
