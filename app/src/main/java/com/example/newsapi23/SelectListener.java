package com.example.newsapi23;

import com.example.newsapi23.domen.ListNewsHeadlines;

public interface SelectListener {
    void onNewsClicked(ListNewsHeadlines headlines);
    void setFavorite(ListNewsHeadlines newsHeadlines, boolean isFavorite);
}
