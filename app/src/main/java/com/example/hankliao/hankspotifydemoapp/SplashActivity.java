package com.example.hankliao.hankspotifydemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Login Spotify，並取得認證碼(token) SDK Activity登入認證-----(S)
        /*
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        // Login UI 是 Spotify SDK自己定義實作，執行Login動作後不管成功或失敗皆會呼叫onActivityResult
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        */
        // Login Spotify，並取得認證碼(token) -----(E)

        // Login Spotify，並取得認證碼(token) 走Browser登入認證-----(S)
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(HankSpotify.CLIENT_ID, AuthenticationResponse.Type.TOKEN, HankSpotify.REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        builder.setShowDialog(true);
        AuthenticationRequest request = builder.build();

        // Login UI 是 Spotify SDK自己定義實作，執行Login動作後不管成功或失敗皆會呼叫onActivityResult
        AuthenticationClient.openLoginInBrowser(this, request);
        // Login Spotify，並取得認證碼(token) -----(E)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if result comes from the correct activity
        if (requestCode == HankSpotify.REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    Log.d(TAG, "Login Success");

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.d(TAG, "Login Error");
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}
