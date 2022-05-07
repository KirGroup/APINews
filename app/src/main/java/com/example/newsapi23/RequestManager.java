package com.example.newsapi23;

import android.content.Context;

import com.example.newsapi23.domen.Headlines;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class RequestManager {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();

    public void getNewsHeadlines(RequestManagerListener listener, String category, String query) {
        NewsApi callNewsApi = retrofit.create(NewsApi.class);

        Call<Headlines> call = callNewsApi.getHeadlines("us", category, query, BuildConfig.API_KEY);

        try {
            call.enqueue(new Callback<Headlines>() {
                @Override
                public void onResponse(Call<Headlines> call, Response<Headlines> response) {
//                    if (!response.isSuccessful()){
//                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show(); сделать эту проверкку при получении, снаружи
//
//                    }
                    listener.onSuccess(response.body().getArticles(), response.message());

                }
                @Override
                public void onFailure(Call<Headlines> call, Throwable t) {
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
