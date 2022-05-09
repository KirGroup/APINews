package com.example.newsapi23;

import androidx.annotation.NonNull;

import com.example.newsapi23.domen.Headlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RequestManager { //creates a network request handler and processes requests

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();

    public void getNewsHeadlines(RequestManagerListener listener, String category, String query) {
        NewsApi callNewsApi = retrofit.create(NewsApi.class);

        Call<Headlines> call = callNewsApi.getHeadlines("us", category, query, BuildConfig.API_KEY);

        try {
            call.enqueue(new Callback<Headlines>() {
                @Override
                public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                    if(response.body()!=null) {
                        listener.onSuccess(response.body().getArticles(), response.message()); //the result of the work, gives the listener a response from the server
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                    listener.onError("Request Failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface NewsApi {
        @GET ("top-headlines")
        Call<Headlines> getHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey);
    }
}
