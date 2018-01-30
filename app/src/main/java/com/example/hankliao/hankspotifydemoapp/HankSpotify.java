package com.example.hankliao.hankspotifydemoapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by hankliao on 2018/1/30.
 */

public class HankSpotify extends Application {

    private static Context context;

    // Request code will be used to verify if result comes from the login activity. Can be set to any integer.
    public static final int REQUEST_CODE = 1337;
    @SuppressWarnings("SpellCheckingInspection")
    public static final String CLIENT_ID = "8daaccdc1aa645e994a0cf5b5f4c0443";
    @SuppressWarnings("SpellCheckingInspection")
    public static final String REDIRECT_URI = "testschema://callback";

    public void onCreate() {
        super.onCreate();
        HankSpotify.context = getApplicationContext();
    }
}
