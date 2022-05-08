package com.example.newsapi23.favourite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsapi23.domen.NewsHeadlines;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insert(NewsHeadlines news);

    @Delete
    void delete(NewsHeadlines news);

    @Query("SELECT EXISTS(SELECT * FROM newsHeadlines WHERE url = :newsUrl)")
    boolean isFavorite(String newsUrl);

    @Query("SELECT * FROM newsheadlines")
    List<NewsHeadlines> getAll();
}
