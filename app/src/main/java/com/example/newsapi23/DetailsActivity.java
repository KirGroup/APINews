package com.example.newsapi23;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import android.annotation.SuppressLint;import android.content.ClipData;import android.content.Intent;import android.content.res.Resources;import android.graphics.drawable.Drawable;import android.opengl.Visibility;import android.os.Bundle;import android.util.Log;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.webkit.WebView;import android.webkit.WebViewClient;import android.view.Menu;import com.example.newsapi23.Models.NewsHeadlines;public class DetailsActivity extends AppCompatActivity {    WebView webView;    String noteID;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_details);        Intent intent = getIntent();        noteID = intent.getStringExtra("data");        Log.i("Detail", noteID.toString());        webView = findViewById(R.id.webView);        webView.getSettings().setJavaScriptEnabled(true);        webView.setWebViewClient(new WebViewClient());        webView.loadUrl(noteID);    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        getMenuInflater().inflate(R.menu.main_menu, menu);//        MenuItem favorite = menu.findItem(R.id.btn_favorite);//        favorite.setVisible(false);//        View favorite = findViewById(R.id.btn_favorite);//        View unfavorite = findViewById(R.id.btn_unfavorite);//        favorite.setVisibility(View.INVISIBLE);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item){//        View favorite = findViewById(R.id.btn_favorite);        View unfavorite = findViewById(R.id.btn_unfavorite);//        MenuItem favorite = findViewById(R.id.btn_favorite);//        MenuItem unfavorite = findViewById(R.id.btn_unfavorite);//        unfavorite.setVisibility(View.VISIBLE);//        favorite.setVisibility(View.GONE);        if(item.getItemId()==R.id.btn_unfavorite){                unfavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24);            }//            favorite.setVisibility(View.GONE);//            unfavorite.setVisibility(View.VISIBLE);//            favorite.setVisible(true);////        if(item.getItemId()==R.id.btn_unfavorite){//            unfavorite.setBackgroundResource(R.id.btn_favorite);//        }////            unfavorite.setVisible(true);//            unfavorite.setVisibility(View.GONE);//            favorite.setVisibility(View.VISIBLE);//        }        return true;    }}