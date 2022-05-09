package com.example.newsapi23.googleblock;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;

public class GoogleAuth {

    private final BeginSignInRequest signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build();

    public void check(){

    }
}