package com.example.newsapi23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapi23.domen.Headlines;
import com.example.newsapi23.domen.ListNewsHeadlines;
import com.example.newsapi23.domen.NewsHeadlines;
import com.example.newsapi23.ui.NewsRecycleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    NewsRecycleAdapter adapter; // создает превью, строчку новости.
    Button btnBusinessField, btnEntertainmentField, btnGeneralField, btnHealthField, btnScienceField, btnSportField, btnTechnologyField;
    SearchView searchView; // вьюшка для поика
    MainViewModel viewModel; // контэйнер состояния, всех данных
    private static List<ListNewsHeadlines> mFavoriteNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //слушатель поля ввода поиска
            @Override
            public boolean onQueryTextSubmit(String query) {  //слушатель кнопки поиска
                viewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //слушатель изменение в поле ввода текста поиска
                return false;
            }
        });

        btnBusinessField = findViewById(R.id.btn_business);
        btnBusinessField.setOnClickListener(this);
        btnEntertainmentField = findViewById(R.id.btn_entertainment);
        btnEntertainmentField.setOnClickListener(this);
        btnGeneralField = findViewById(R.id.btn_general);
        btnGeneralField.setOnClickListener(this);
        btnHealthField = findViewById(R.id.btn_health);
        btnHealthField.setOnClickListener(this);
        btnScienceField = findViewById(R.id.btn_science);
        btnScienceField.setOnClickListener(this);
        btnSportField = findViewById(R.id.btn_sport);
        btnSportField.setOnClickListener(this);
        btnTechnologyField = findViewById(R.id.btn_technology);
        btnTechnologyField.setOnClickListener(this);

        setupViewModel();

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        adapter = new NewsRecycleAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class); //создает менеджер для получения вьюмодел с данными
        viewModel.newsData.observe(this, this::showNews);/*new Observer<List<ListNewsHeadlines>>() { //все запросы беруться из viewModel

            @Override
            public void onChanged (List <ListNewsHeadlines> newsEntries) {
                mFavoriteNews = newsEntries;
                for (int i = 0; i < mFavoriteNews.size(); i++) {
                    mFavoriteNews.get(i).setFavoriteStatus(true);
                }
                adapter.setNews(mFavoriteNews);
            }
        });*/
    }

        private void showNews (List < ListNewsHeadlines > list) {
            if (list.isEmpty()) {
                Toast.makeText(this, "News is empty", Toast.LENGTH_SHORT).show();
            } else {
                adapter.setNews(list);
            }
        }


    @Override
    public void onNewsClicked(ListNewsHeadlines headlines) {
        Intent main = new Intent(MainActivity.this, DetailsActivity.class);
        main.putExtra("data", headlines.getUrl());
        startActivity(main);
    }

    @Override
    public void setFavorite(ListNewsHeadlines newsHeadlines, boolean isFavorite) {
        viewModel.setFavorite(newsHeadlines, isFavorite);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String category = btn.getText().toString();
        viewModel.searchCategory(category); // беру текст  кнопки и вставляю её в поик категорий
    }
}