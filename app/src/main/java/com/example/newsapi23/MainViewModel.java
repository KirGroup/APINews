package com.example.newsapi23;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.newsapi23.domen.Headlines;
import com.example.newsapi23.domen.ListNewsHeadlines;
import com.example.newsapi23.domen.NewsHeadlines;
import com.example.newsapi23.favourite.DaoFactory;
import com.example.newsapi23.favourite.FavoriteDao;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public final MutableLiveData<List<ListNewsHeadlines>> newsData = new MutableLiveData<>();
    private final RequestManagerListener<Headlines> listener = new RequestManagerListener<Headlines>() {
        @Override
        public void onSuccess(List<NewsHeadlines> list, String message) {
            List <ListNewsHeadlines> listNews = new ArrayList<>();
            for(NewsHeadlines item : list) {
                listNews.add(new ListNewsHeadlines(item, false));
            }
            new Thread(new Runnable(){
                public void run() {
                    boolean currFav = false;
                    for(int i = 0; i < list.size(); i++){
                        if(favorite.isFavorite(listNews.get(i).getUrl())){
                            currFav = true;
                        }
                        listNews.add(new ListNewsHeadlines(listNews.get(i), currFav));
                    }
                    newsData.postValue(listNews);
                }
            }).start();
        }

        @Override
        public void onError(String message) {
// что прописать!!!
        }
    };  //передаёт сетевой ответ в лайфдату
    private final RequestManager manager = new RequestManager();
    private final FavoriteDao favorite = Room.databaseBuilder(getApplication(), DaoFactory.class, "DataBase.db").build().getFavorite();

    public MainViewModel(@NonNull Application application) {
        super(application);
        manager.getNewsHeadlines(listener, "general", null);
    }

    public void search (String query){
        manager.getNewsHeadlines(listener, "general", query);
    }

    public void searchCategory(String category) {
        manager.getNewsHeadlines(listener, category, null);
    }

    public void setFavorite(ListNewsHeadlines headlines, boolean isFavorite){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(isFavorite){
                    favorite.insert(headlines);
                    headlines.setFavorite(true);
                } else {
                    favorite.delete(headlines);
                    headlines.setFavorite(false);
                }
            }
        }).start();
    }
}