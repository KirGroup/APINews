package com.example.newsapi23;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapi23.domen.Headlines;
import com.example.newsapi23.domen.NewsHeadlines;

import java.util.List;

public class MainViewModel extends ViewModel {
    public final MutableLiveData<List<NewsHeadlines>> newsData = new MutableLiveData<>();
    private final RequestManagerListener<Headlines> listener = new RequestManagerListener<Headlines>() {
        @Override
        public void onSuccess(List<NewsHeadlines> list, String message) {
            newsData.postValue(list);
        }

        @Override
        public void onError(String message) {

        }
    };  //передаёт сетевой ответ в лайфдату
    private final RequestManager manager = new RequestManager();
    
    public MainViewModel() {
        manager.getNewsHeadlines(listener, "general", null);
    }
    
    public void search (String query){
        manager.getNewsHeadlines(listener, "general", query);
    }

    public void searchCategory(String category) {
        manager.getNewsHeadlines(listener, category, null);
    }
}
