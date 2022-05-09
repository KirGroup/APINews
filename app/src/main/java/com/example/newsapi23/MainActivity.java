package com.example.newsapi23;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapi23.domen.ListNewsHeadlines;
import com.example.newsapi23.ui.NewsRecycleAdapter;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    NewsRecycleAdapter adapter; // creates a preview, a line of news.
    Button btnFavoriteField, btnBusinessField, btnEntertainmentField, btnGeneralField, btnHealthField, btnScienceField, btnSportField, btnTechnologyField;
    SearchView searchView; // View for search
    MainViewModel viewModel; // State maintainer, all data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //search input field listener
            @Override
            public boolean onQueryTextSubmit(String query) {  //search button listener
                viewModel.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //слушатель изменение в поле ввода текста поиска
                return false;
            }
        });

        btnFavoriteField = findViewById(R.id.btn_favorite);
        btnFavoriteField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOut();
            }
        });

        initRequest();
         authClient = Identity.getSignInClient(this);

//        btnFavoriteField.setOnClickListener(this);
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
        viewModel = new ViewModelProvider(this).get(MainViewModel.class); //creates a manager to retrieve data models
        viewModel.newsData.observe(this, this::showNews);
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
        viewModel.searchCategory(category); // I take the text of the button and paste it into the categories
    }

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private SignInClient authClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                        .setSupported(true)
//                        .build())
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
////                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                // Automatically sign in when exactly one credential is retrieved.
//                .setAutoSelectEnabled(true)
//                .build();
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId("478075276794-11eu2gubdn7dlln64ul5ff1bltmjgod8.apps.googleusercontent.com")
                        .setServerClientId("478075276794-q9obku43otgg27lqm2nehltrc6go3679.apps.googleusercontent.com")
//                        .setServerClientId("478075276794-q9obku43otgg27lqm2nehltrc6go3679.apps.googleusercontent.com")
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
    }

    private void initRequest(){
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId("478075276794-11eu2gubdn7dlln64ul5ff1bltmjgod8.apps.googleusercontent.com")
                        .setServerClientId("478075276794-q9obku43otgg27lqm2nehltrc6go3679.apps.googleusercontent.com")
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
    }

    public void checkOut(){
        authClient.beginSignIn(signInRequest)
                .addOnSuccessListener(beginSignInResult -> {
                    Log.i("Auth", "Success");
                    Toast.makeText(MainActivity.this, "Auth success", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.i("Auth", "Fail " + e.getLocalizedMessage());
                    Toast.makeText(MainActivity.this, "Auth fail", Toast.LENGTH_SHORT).show();
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        switch (requestCode) {
//            case REQ_ONE_TAP:
//                try {
//                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
//                    String idToken = credential.getGoogleIdToken();
//                    if (idToken !=  null) {
//                        // Got an ID token from Google. Use it to authenticate
//                        // with Firebase.
//                        Log.d(TAG, "Got ID token.");
//                    }
//                } catch (ApiException e) {
//                }
//                break;
//        }
    }

}