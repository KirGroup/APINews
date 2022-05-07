package com.example.newsapi23.favourite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.newsapi23.domen.NewsHeadlines;

@Database(entities = {NewsHeadlines.class}, version = 1)
public abstract class DaoFactory extends RoomDatabase{
    public abstract FavoriteDao getFavorite();
}
