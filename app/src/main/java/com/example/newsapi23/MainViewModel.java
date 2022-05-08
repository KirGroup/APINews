package com.example.newsapi23;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import com.example.newsapi23.domen.ListNewsHeadlines;
import com.example.newsapi23.domen.NewsHeadlines;
import com.example.newsapi23.favourite.DaoFactory;
import com.example.newsapi23.favourite.FavoriteDao;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public final MutableLiveData<List<ListNewsHeadlines>> newsData = new MutableLiveData<>();
    private final RequestManagerListener listener = new RequestManagerListener() {
        @Override
        public void onSuccess(List<NewsHeadlines> list, String message) {
            List<ListNewsHeadlines> listNews = new ArrayList<>();
            new Thread(() -> {
                for (int i = 0; i < list.size(); i++) {
                    boolean currFav = favorite.isFavorite(list.get(i).getUrl());
                    listNews.add(new ListNewsHeadlines(list.get(i), currFav));
                }
                newsData.postValue(listNews);
            }).start();
        }

        @Override
        public void onError(String message) {
        }
    };  //transmits a network response to a livedata

    private final RequestManager manager = new RequestManager();
    private final FavoriteDao favorite = Room.databaseBuilder(getApplication(), DaoFactory.class, "DataBase.db").build().getFavorite();

    public MainViewModel(@NonNull Application application) {
        super(application);
        manager.getNewsHeadlines(listener, "general", null);
    }

    public void search(String query) {
        manager.getNewsHeadlines(listener, "general", query);
    }

    public void searchCategory(String category) {
        if (category.equals("favorite")) {
            new Thread(() -> {
                List<ListNewsHeadlines> favoriteList = new ArrayList<>();
                for (NewsHeadlines item : favorite.getAll()) {
                    favoriteList.add(new ListNewsHeadlines(item, true));
                }
                newsData.postValue(favoriteList);
            }).start();
        } else {
            manager.getNewsHeadlines(listener, category, null);
        }
    }

    public void setFavorite(ListNewsHeadlines headlines, boolean isFavorite) {
        new Thread(() -> {
            if (isFavorite) {
                favorite.insert(headlines);
                headlines.setFavorite(true);
            } else {
                favorite.delete(headlines);
                headlines.setFavorite(false);
            }
        }).start();
    }
}