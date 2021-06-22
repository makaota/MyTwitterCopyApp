package com.samakaota.mytwittercopyapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jgI9asOwPzq1XGdx5aDbpAS2kCz60ltEdkR0fP8F")
                // if defined
                .clientKey("IcQDwrxKCSwP5vnGkhybAgDJQtGWF94sVHMYhdSr")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
